package data_access;

import entity.Medicine;
import entity.Today;

import java.util.ArrayList;

public class MedicineDAO implements MedicineDataAccessInterface{
    private ArrayList<Medicine> userMedicines = new ArrayList<>();
    private Today today;
    public boolean exists(String name){
        for(Medicine medicine : userMedicines){
            if(medicine.getName().equals(name)){
                return true;
            }
        }
        return false;
    }
    public void saveMedicine(Medicine medicine){
        userMedicines.add(medicine);

    }
    public void saveToday(Today today){
        this.today = today;
    }
    public void updateToday(Medicine medicine){
        today.add(medicine);
    }

    public ArrayList<Medicine> getUserMedicines() {
        return userMedicines;
    }

    public void setUserMedicines(ArrayList<Medicine> userMedicines) {
        this.userMedicines = userMedicines;
    }

    public Today getToday() {
        return today;
    }

    public void setToday(Today today) {
        this.today = today;
    }
}
