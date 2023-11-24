package use_case.enterMedicine;

import data_access.MedicineDataAccessInterface;
import entity.Dose;
import entity.Medicine;
import entity.MedicineFactory;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;

public class EnterInteractor implements EnterInputBoundary {
    final MedicineDataAccessInterface medicineDataAccessObject;
    final EnterOutputBoundary enterPresenter;
    final MedicineFactory medicineFactory;

    public EnterInteractor(MedicineDataAccessInterface medicineDataAccessObject,
                           EnterOutputBoundary enterPresenter,
                           MedicineFactory medicineFactory) {
        this.medicineDataAccessObject = medicineDataAccessObject;
        this.enterPresenter = enterPresenter;
        this.medicineFactory = medicineFactory;
    }

    @Override
    public void execute(EnterInputData enterInputData) {
        String name = enterInputData.getMedicine();
        if (medicineDataAccessObject.exists(name)) {
            enterPresenter.preparePopUp(name + " already exists as a medication");
        }
        else {
            // API call to get id
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://rxnav.nlm.nih.gov/REST/rxcui.json?name=" + name + "&allsrc=0&srclist=ALL&search=2"))
                    .method("GET", HttpRequest.BodyPublishers.noBody()).build();
            HttpResponse<String> response = null;
            String id = "";
            try {
                response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
                JSONObject jsonResponse = new JSONObject(response.body());
                JSONObject idGroup = jsonResponse.getJSONObject("idGroup");
                JSONArray rxnormId = idGroup.getJSONArray("rxnormId");
                id = rxnormId.getString(0);
            } catch (IOException e) {
            } catch (InterruptedException e) {
            }
            // API call to drug interaction checker
            String allId = medicineDataAccessObject.getIdListString() + "+" + id;
            HttpRequest requestInteraction = HttpRequest.newBuilder()
                    .uri(URI.create("https://rxnav.nlm.nih.gov/REST/interaction/list.json?rxcuis=" + allId))
                    .method("GET", HttpRequest.BodyPublishers.noBody()).build();
            HttpResponse<String> responseInteraction = null;
            ArrayList<String> warnings = new ArrayList<String>();
            try {
                responseInteraction = HttpClient.newHttpClient().send(requestInteraction, HttpResponse.BodyHandlers.ofString());
                JSONObject jsonResponseInteraction = new JSONObject(responseInteraction.body());
                JSONArray fullInteractionTypeGroup = jsonResponseInteraction.getJSONArray("fullInteractionTypeGroup");
                // TODO check if there is element at index 0
                JSONArray fullInteractionType = fullInteractionTypeGroup.getJSONObject(0).getJSONArray("fullInteractionType");
                for (int i = 0; i < fullInteractionType.length(); i++) {
                    JSONObject eachInteraction = fullInteractionType.getJSONObject(i);
                    if (eachInteraction.getString("comment").contains(id)) {
                        // TODO check if there is element at index 0
                        String description = eachInteraction
                                .getJSONArray("interactionPair")
                                .getJSONObject(0).
                                getString("description");
                        warnings.add(description);
                    }
                }
                if (!warnings.isEmpty()) {
                    StringBuilder popUpMessage = new StringBuilder();
                    for (String description:warnings) {
                        popUpMessage.append("Warning - drug interaction detected: ").append(description).append("\n");
                    }
                    enterPresenter.preparePopUp(popUpMessage.toString());
                }
            } catch (IOException e) {
            } catch (InterruptedException e) {
            }
            if (1 == 2) {}
            else {
                Dose dose = medicineFactory.createDose(enterInputData.getDoseSize(),
                        enterInputData.getInventory(),
                        enterInputData.getUnit());
                Medicine medicine = medicineFactory.createMedicine(enterInputData.getMedicine(),
                        dose,
                        enterInputData.getDay(),
                        enterInputData.getDescription(), id);
                if (medicine.getDose().getDosesRemaining() == 0) {
                    enterPresenter.preparePopUp("Cannot enter medicine with 0 doses remaining.");
                } else {
                    medicineDataAccessObject.saveMedicine(medicine);
                    EnterOutputData enterOutputData = new EnterOutputData(enterInputData.getMedicine(), medicine.getDoseString(),
                            medicine.getInventoryString(),
                            medicine.getWeeklySchedule()[0],
                            medicine.getWeeklySchedule()[1],
                            medicine.getWeeklySchedule()[2],
                            medicine.getWeeklySchedule()[3],
                            medicine.getWeeklySchedule()[4],
                            medicine.getWeeklySchedule()[5],
                            medicine.getWeeklySchedule()[6],
                            medicine.getDescription(),
                            medicine.getDose().getDosesRemaining());
                    enterPresenter.prepareSuccessView(enterOutputData);
                    Integer today = medicineDataAccessObject.getTodayDay();
                    for (int i = 0; i < Math.min(medicine.getWeeklySchedule()[today], medicine.getDose().getDosesRemaining()); i++) {
                        enterPresenter.updateChecklistView(enterOutputData);
                    }
                    if (medicine.getDose().getDosesRemaining() < 14) {
                        enterPresenter.updateLowView(enterOutputData);
                    }
                }
            }
        }
    }
}
