package data_access;

import entity.Medicine;
import entity.Today;

import java.util.HashMap;

public interface MedicineDataAccessInterface {
    boolean exists(String name);
    void saveMedicine(Medicine medicine);
    void removeMedicine(String medicine);
    Integer getTodayDay();
    HashMap<String, Integer> getTodayChecklist();
    HashMap<String, Medicine> getUserMedicines();
    void takeMedicine(String medicine);
    void undoTakeMedicine(String medicine);
    String getIdListString();
}
