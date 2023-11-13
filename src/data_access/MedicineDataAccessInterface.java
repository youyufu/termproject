package data_access;

import entity.Medicine;
import entity.Today;

public interface MedicineDataAccessInterface {
    boolean exists(String name);
    void saveMedicine(Medicine medicine);
    void removeMedicine(String medicine);
    void saveToday(Today today);
    void updateToday(Medicine medicine);
    Today getToday();
    void takeMedicine(String medicine);
}
