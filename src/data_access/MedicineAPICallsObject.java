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
    public MedicineAPICallsObject() {}
    @Override
    public String findId(String name) {
        String id = DEFAULT_ID;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://rxnav.nlm.nih.gov/REST/rxcui.json?name=" + name + "&allsrc=0&srclist=ALL&search=2"))
                .method("GET", HttpRequest.BodyPublishers.noBody()).build();
        HttpResponse<String> response = null;
        try {
            response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
            JSONObject jsonResponse = new JSONObject(response.body());
            JSONObject idGroup = jsonResponse.getJSONObject("idGroup");
            if (idGroup.has("rxnormId")) {
                JSONArray rxnormId = idGroup.getJSONArray("rxnormId");
                id = rxnormId.getString(0);
            }
        } catch (IOException e) {
        } catch (InterruptedException e) {
        }
        return id;
    }

    @Override
    public ArrayList<String> findDrugInteractions(MedicineDataAccessInterface medicineDataAccessObject, String id) throws IOException, InterruptedException {
        String allId = medicineDataAccessObject.getIdListString() + "+" + id;
        HttpRequest requestInteraction = HttpRequest.newBuilder()
                .uri(URI.create("https://rxnav.nlm.nih.gov/REST/interaction/list.json?rxcuis=" + allId))
                .method("GET", HttpRequest.BodyPublishers.noBody()).build();
        HttpResponse<String> responseInteraction = null;
        ArrayList<String> warnings = new ArrayList<String>();
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
}
