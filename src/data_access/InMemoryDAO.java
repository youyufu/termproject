package data_access;

import entity.Medicine;
import entity.MedicineFactory;
import entity.Today;

import java.util.HashMap;

public class InMemoryDAO implements MedicineDataAccessInterface {
    private HashMap<String, Medicine> userMedicine = new HashMap<>();
    private Today today;
    private final MedicineFactory medicineFactory;

    public InMemoryDAO(Today today, MedicineFactory medicineFactory) {
        this.today = today;
        this.medicineFactory = medicineFactory;
    }
    @Override
    public boolean exists(String name) {
        return userMedicine.containsKey(name);
    }

    @Override
    public void saveMedicine(Medicine medicine) {
        userMedicine.put(medicine.getName(), medicine);
        if (medicine.getWeeklySchedule()[today.getDay()] != 0) {
            today.add(medicine.getName(), 0);
        }
    }

    @Override
    public void removeMedicine(String medicine) {
        userMedicine.remove(medicine);
        today.remove(medicine);
    }

    @Override
    public Integer getTodayDay() {
        return today.getDay();
    }

    @Override
    public HashMap<String, Integer> getTodayChecklist() {return today.getTodayChecklist();}

    @Override
    public HashMap<String, Medicine> getUserMedicines() {
        return userMedicine;
    }

    @Override
    public void takeMedicine(String medicine) {
        userMedicine.get(medicine).getDose().takeDose();
        today.take(medicine);
    }

    @Override
    public void undoTakeMedicine(String medicine) {
        userMedicine.get(medicine).getDose().undoTakeDose();
        today.untake(medicine);
    }

    @Override
    public String getIdListString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Medicine medicine:userMedicine.values()) {
            stringBuilder.append(medicine.getId()).append("+");
        } stringBuilder.deleteCharAt(stringBuilder.length());
        return stringBuilder.toString();
    }
}
