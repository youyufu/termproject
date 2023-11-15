package use_case.enterMedicine;

import data_access.MedicineDataAccessInterface;
import entity.Medicine;
import entity.Dose;
import entity.MedicineFactory;
import org.json.JSONObject;
import org.json.JSONArray;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpClient;
import java.io.IOException;

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
            enterPresenter.prepareFailView(name + " already exists as a medication");
        }
        else {
            // API call to get id
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://rxnav.nlm.nih.gov/REST/rxcui.json?name=" + name + "&allsrc=0&srclist=ALL&search=2"))
                    .method("GET", HttpRequest.BodyPublishers.noBody()).build();
            HttpResponse<String> response = null;
            try {
                response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
                JSONObject jsonResponse = new JSONObject(response.body());
                JSONObject idGroup = (JSONObject) jsonResponse.get("idGroup");
                JSONArray rxnormId = (JSONArray) idGroup.get("rxnormId");
                String id = rxnormId.getString(0);
            } catch (IOException e) {

            } catch (InterruptedException e) {

            }

            // API call to drug interaction checker
            if () {}
            else {
                Dose dose = medicineFactory.createDose(enterInputData.getDoseSize(),
                        enterInputData.getInventory(),
                        enterInputData.getUnit());
                Medicine medicine = medicineFactory.createMedicine(enterInputData.getMedicine(),
                        dose,
                        enterInputData.getDay(),
                        enterInputData.getDescription(),
                        id);
                medicineDataAccessObject.saveMedicine(medicine);
                EnterOutputData enterOutputData = new EnterOutputData(enterInputData.getMedicine(),
                        medicine.getDoseString(),
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
                Integer today = medicineDataAccessObject.getToday().getDay();
                if (!medicine.getWeeklySchedule()[today].equals(0)) {
                    medicineDataAccessObject.updateToday(medicine);
                    for (int i = 0; i < medicine.getWeeklySchedule()[today]; i++)
                        enterPresenter.updateChecklistView(enterOutputData);
                } if (medicine.getDose().getDosesRemaining() < 14) {
                    enterPresenter.updateLowView(enterOutputData);
                }
            }
        }
    }
}
