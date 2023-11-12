package data_access;

import entity.Medicine;
import entity.Today;

public interface MedicineDataAccessInterface {
    public boolean exists(String name);
    public void saveMedicine(Medicine medicine);
    public void removeMedicine(String medicine);
    public void saveToday(Today today);
    public void updateToday(Medicine medicine);
}
