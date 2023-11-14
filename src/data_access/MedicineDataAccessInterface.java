package data_access;

import entity.Medicine;
import entity.Today;

public interface MedicineDataAccessInterface {
    boolean exists(String name);
    void saveMedicine(Medicine medicine);
    void removeMedicine(String medicine);
    Today getToday();
    void takeMedicine(String medicine);
    void undoTakeMedicine(String medicine);
}
