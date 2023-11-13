package data_access;

import entity.Medicine;
import entity.MedicineFactory;
import entity.Today;

import java.util.ArrayList;
import java.util.List;

public class MedicineDAO implements MedicineDataAccessInterface{
    private ArrayList<Medicine> userMedicines = new ArrayList<>();
    private Today today;
    private MedicineFactory medicineFactory;
    public MedicineDAO(Today today1, MedicineFactory medicineFactory) {
        this.today = today1;
        this.medicineFactory = medicineFactory;
    }
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
    public void  removeMedicine(String medicine){
        ArrayList<Medicine> medicineList = new ArrayList<>();
        for(Medicine m : userMedicines){
            if(m.getName().equals(medicine)){
                continue;
            }
            medicineList.add(m);
        }
        userMedicines = medicineList;
    }
    public void takeMedicine(String medicine) {
        for (Medicine m:userMedicines) {
            if (m.getName().equals(medicine)) {
                m.getDose().takeDose();
            }
        }
    }
    public void saveToday(Today today){}
    public void updateToday(Medicine medicine){
        if (!today.getTodayChecklist().containsKey(medicine)) {today.add(medicine);}
        else {if (today.getTodayChecklist().get(medicine)) {today.add(medicine);}
            else {today.take(medicine);}
        }
    }
    public Today getToday() {return today;}
}
