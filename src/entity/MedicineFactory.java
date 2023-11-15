package entity;

public class MedicineFactory {

    public Medicine createMedicine(String medicine, Dose dose, Integer[] weeklySchedule, String description, String id){
        return new Medicine(medicine, dose, weeklySchedule, description, id);
    }

    public Dose createDose(int doseSize, int inventory, String unit){
        return new Dose(doseSize, inventory, unit);
    }
}
