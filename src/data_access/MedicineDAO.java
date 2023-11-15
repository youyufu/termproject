package data_access;

import entity.Dose;
import entity.Medicine;
import entity.MedicineFactory;
import entity.Today;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;

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
            try {
                String content = new String(Files.readAllBytes(Paths.get(jsonFile.toURI())));
                JSONObject file = new JSONObject(content);
                JSONObject today = (JSONObject) file.get("today");
                JSONArray todayArray = (JSONArray) today.get("todayArray") ;
                JSONArray medArray = (JSONArray) file.get("medicines");
                for (Object object1:medArray) {
                    JSONObject med = (JSONObject) object1;
                    Integer dS = (Integer) med.get("doseSize");
                    Integer dI = (Integer) med.get("doseInventory");
                    Dose dose = medicineFactory.createDose(dS, dI, (String) med.get("doseUnit"));
                    Integer su = (Integer) med.get("sun");
                    Integer mo = (Integer) med.get("mon");
                    Integer tu = (Integer) med.get("tue");
                    Integer we = (Integer) med.get("wed");
                    Integer th = (Integer) med.get("thu");
                    Integer fr = (Integer) med.get("fri");
                    Integer sa = (Integer) med.get("sat");
                    Integer[] weeklySchedule = {su, mo, tu, we, th, fr, sa};
                    Medicine medicine = medicineFactory.createMedicine((String) med.get("name"), dose, weeklySchedule, (String) med.get("description"), (String) med.get("id"));
                    userMedicines.put(medicine.getName(), medicine);
                } if (today.get("dayInt") == this.today.getDay()) {
                    for (Object object1:todayArray) {
                        JSONObject med = (JSONObject) object1;
                        Long taken = (Long) med.get("taken");
                        Integer t = taken.intValue();
                        this.today.add((String) med.get("name"), t);
                    }
                } else {
                    for (Medicine medicine:userMedicines.values()) {
                        if (medicine.getWeeklySchedule()[this.today.getDay()] != 0) {
                            this.today.add(medicine.getName(), 0);
                        }
                    }
                }
            } catch (IOException e) {
                throw new IOException();
            }
        }
    }
    private void save() {
        try (FileWriter fileWriter = new FileWriter(jsonFile)) {
            JSONObject file = new JSONObject();
            JSONObject today = new JSONObject();
            today.put("dayInt", String.valueOf(this.today.getDay()));
            JSONArray dayArray = new JSONArray();
            HashMap<String, Integer> todayChecklist = this.today.getTodayChecklist();
            for (String medicine:todayChecklist.keySet()) {
                JSONObject med = new JSONObject();
                med.put("name", medicine);
                med.put("taken", todayChecklist.get(medicine));
                dayArray.put(med);
            } today.put("todayArray", dayArray);

            JSONArray medArray = new JSONArray();
            for (Medicine medicine:userMedicines.values()) {
                JSONObject med = new JSONObject();
                med.put("name", medicine.getName());
                med.put("doseSize", medicine.getDose().getSize());
                med.put("doseInventory", medicine.getDose().getInventory());
                med.put("doseUnit", medicine.getDose().getUnit());
                med.put("sun", medicine.getWeeklySchedule()[0]);
                med.put("mon", medicine.getWeeklySchedule()[1]);
                med.put("tue", medicine.getWeeklySchedule()[2]);
                med.put("wed", medicine.getWeeklySchedule()[3]);
                med.put("thu", medicine.getWeeklySchedule()[4]);
                med.put("fri", medicine.getWeeklySchedule()[5]);
                med.put("sat", medicine.getWeeklySchedule()[6]);
                med.put("description", medicine.getDescription());
                med.put("id", medicine.getId());
                medArray.put(med);
            }
            file.put("today", today);
            file.put("medicines", medArray);
            fileWriter.write(file.toString());
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
