package data_access;

import entity.Dose;
import entity.Medicine;
import entity.MedicineFactory;
import entity.Today;

import java.io.*;
import java.util.HashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class MedicineDAO implements MedicineDataAccessInterface{
    private final File jsonFile;
    private HashMap<String, Medicine> userMedicines = new HashMap<>();
    private Today today;
    private final MedicineFactory medicineFactory;
    public MedicineDAO(String jsonPath, Today today1, MedicineFactory medicineFactory) throws IOException {
        this.today = today1;
        this.medicineFactory = medicineFactory;
        this.jsonFile = new File(jsonPath);

        if (jsonFile.length() == 0) {
            save();
        } else {
            JSONParser jsonParser= new JSONParser();
            try (FileReader fileReader = new FileReader(jsonFile)) {
                Object object = jsonParser.parse(fileReader);
                JSONArray jsonArray = (JSONArray) object;
                JSONObject today = (JSONObject) jsonArray.get(0);
                JSONArray todayArray = (JSONArray) today.get("todayArray") ;
                JSONArray medArray = (JSONArray) jsonArray.get(1);
                for (Object object1:medArray) {
                    JSONObject med = (JSONObject) object1;
                    Dose dose = medicineFactory.createDose((Integer) med.get("doseSize"), (Integer) med.get("doseInventory"), (String) med.get("doseUnit"));
                    Medicine medicine = medicineFactory.createMedicine((String) med.get("name"), dose, (Integer[]) med.get("weeklySchedule"), (String) med.get("description"));
                    userMedicines.put(medicine.getName(), medicine);
                } if (today.get("dayInt") == this.today.getDay()) {
                    for (Object object1:todayArray) {
                        JSONObject med = (JSONObject) object1;
                        this.today.add((String) med.get("name"), (Integer) med.get("taken"));
                    }
                } else {
                    for (Medicine medicine:userMedicines.values()) {
                        if (medicine.getWeeklySchedule()[(int) today.get("dayInt")] != 0) {
                            this.today.add(medicine.getName(), 0);
                        }
                    }
                }


            } catch (ParseException e) {
                throw new IOException();
            }
        }
    }
    private void save() {
        try (FileWriter fileWriter = new FileWriter(jsonFile)) {
            JSONArray dataArray = new JSONArray();

            JSONObject today = new JSONObject();
            today.put("dayInt", String.valueOf(this.today.getDay()));
            JSONArray dayArray = new JSONArray();
            HashMap<String, Integer> todayChecklist = this.today.getTodayChecklist();
            for (String medicine:todayChecklist.keySet()) {
                JSONObject med = new JSONObject();
                med.put("name", medicine);
                med.put("taken", todayChecklist.get(medicine));
                dayArray.add(med);
            } today.put("dayArray", dayArray);

            JSONArray medArray = new JSONArray();
            for (Medicine medicine:userMedicines.values()) {
                JSONObject med = new JSONObject();
                med.put("name", medicine.getName());
                med.put("doseSize", medicine.getDose().getSize());
                med.put("doseInventory", medicine.getDose().getInventory());
                med.put("doseUnit", medicine.getDose().getUnit());
                med.put("weeklySchedule", medicine.getWeeklySchedule());
                med.put("description", medicine.getDescription());
                medArray.add(med);
            }

            dataArray.add(today);
            dataArray.add(medArray);
            fileWriter.write(dataArray.toJSONString());
            fileWriter.flush();
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }
    public boolean exists(String name){
        return userMedicines.containsKey(name);
    }
    public void saveMedicine(Medicine medicine){
        userMedicines.put(medicine.getName(), medicine);
        if (medicine.getWeeklySchedule()[today.getDay()] != 0) {
            today.add(medicine.getName(), 0);
        }
        save();
    }
    public void  removeMedicine(String medicine){
        userMedicines.remove(medicine);
        today.remove(medicine);
        save();
    }
    public void takeMedicine(String medicine) {
        userMedicines.get(medicine).getDose().takeDose();
        today.take(medicine);
        save();
    }
    public void undoTakeMedicine(String medicine){
        userMedicines.get(medicine).getDose().undoTakeDose();
        today.untake(medicine);
        save();
    }
    public Today getToday() {return today;}

    public HashMap<String, Medicine> getUserMedicines() {return userMedicines;}
}
