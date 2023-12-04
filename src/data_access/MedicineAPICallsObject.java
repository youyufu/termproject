package data_access;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;

import static use_case.enterMedicine.EnterInteractor.DEFAULT_ID;

public class MedicineAPICallsObject implements MedicineAPICallsInterface{
    /**
     * Implementation of the interface for the API calls.
     */

    /**
     * Creates a medicine API calls object.
     */
    public MedicineAPICallsObject() {}

    /**
     * Finds the RxCUI associated with the entered name. Assigns DEFAULT_ID if it is not found.
     * @return the RxCUI associated with the entered name, DEFAULT_ID if it is not found.
     */
    @Override
    public String findId(String name) {
        String id = DEFAULT_ID;
        String formattedName = name.replaceAll(" ", "+");
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://rxnav.nlm.nih.gov/REST/rxcui.json?name=" + formattedName + "&allsrc=0&srclist=ALL&search=2"))
                .method("GET", HttpRequest.BodyPublishers.noBody()).build();
        HttpResponse<String> response;
        try {
            response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
            JSONObject jsonResponse = new JSONObject(response.body());
            JSONObject idGroup = jsonResponse.getJSONObject("idGroup");
            if (idGroup.has("rxnormId")) {
                JSONArray rxnormId = idGroup.getJSONArray("rxnormId");
                id = rxnormId.getString(0);
            }
        } catch (IOException | InterruptedException ignored) {
        }
        return id;
    }

    /**
     * Finds the drug interactions between the id of the medicine entered and all existing medicines.
     * @param medicineDataAccessObject the data access object to find all existing medicines.
     * @param id the id of the medicine to search for.
     * @return the drug interactions that exists between the medicine associated with the id and the existing medicines.
     * @throws IOException if there is an issue with the API calls.
     * @throws InterruptedException if there is an issue with the API calls.
     */
    @Override
    public ArrayList<String> findDrugInteractions(MedicineDataAccessInterface medicineDataAccessObject, String id) throws IOException, InterruptedException {
        String allId = medicineDataAccessObject.getIdListString() + "+" + id;
        HttpRequest requestInteraction = HttpRequest.newBuilder()
                .uri(URI.create("https://rxnav.nlm.nih.gov/REST/interaction/list.json?rxcuis=" + allId))
                .method("GET", HttpRequest.BodyPublishers.noBody()).build();
        HttpResponse<String> responseInteraction;
        ArrayList<String> warnings = new ArrayList<>();
        responseInteraction = HttpClient.newHttpClient().send(requestInteraction, HttpResponse.BodyHandlers.ofString());
        JSONObject jsonResponseInteraction = new JSONObject(responseInteraction.body());
        if (jsonResponseInteraction.has("fullInteractionTypeGroup")) {
            JSONArray fullInteractionTypeGroup = jsonResponseInteraction.getJSONArray("fullInteractionTypeGroup");
            JSONArray fullInteractionType = fullInteractionTypeGroup.getJSONObject(0).getJSONArray("fullInteractionType");
            for (int i = 0; i < fullInteractionType.length(); i++) {
                JSONObject eachInteraction = fullInteractionType.getJSONObject(i);
                if (eachInteraction.getString("comment").contains(id)) {
                    String description = eachInteraction
                            .getJSONArray("interactionPair")
                            .getJSONObject(0).
                            getString("description");
                    warnings.add(description);
                }
            }
        }
        return warnings;
    }

    /**
     * Find all drug interactions between the existing medicines.
     *
     * @param medicineDataAccessObject the data access object to find all existing medicines.
     * @return all drug interactions between the existing medicines.
     * @throws IOException          if there is an issue with the API calls.
     * @throws InterruptedException if there is an issue with the API calls.
     */
    @Override
    public ArrayList<String> findAllInteractions(MedicineDataAccessInterface medicineDataAccessObject) throws IOException, InterruptedException {
        ArrayList<String> warnings = new ArrayList<>();
        String allId = medicineDataAccessObject.getIdListString();
        if (!allId.isEmpty()) {
            HttpRequest requestInteraction = HttpRequest.newBuilder()
                    .uri(URI.create("https://rxnav.nlm.nih.gov/REST/interaction/list.json?rxcuis=" + allId))
                    .method("GET", HttpRequest.BodyPublishers.noBody()).build();
            HttpResponse<String> responseInteraction;
            responseInteraction = HttpClient.newHttpClient().send(requestInteraction, HttpResponse.BodyHandlers.ofString());
            JSONObject jsonResponseInteraction = new JSONObject(responseInteraction.body());
            if (jsonResponseInteraction.has("fullInteractionTypeGroup")) {
                JSONArray fullInteractionTypeGroup = jsonResponseInteraction.getJSONArray("fullInteractionTypeGroup");
                JSONArray fullInteractionType = fullInteractionTypeGroup.getJSONObject(0).getJSONArray("fullInteractionType");
                for (int i = 0; i < fullInteractionType.length(); i++) {
                    JSONObject eachInteraction = fullInteractionType.getJSONObject(i);
                    String description = eachInteraction
                            .getJSONArray("interactionPair")
                            .getJSONObject(0).
                            getString("description");
                    warnings.add(description);
                }
            }
        }
        return warnings;
    }
}
