package entity;

public class MedicineFactory {

    public Medicine createMedicine(String medicine, Dose dose, int[] weeklySchedule, String description){
        return new Medicine(medicine, dose, weeklySchedule, description);
    }

    public Dose createDose(int doseSize, int inventory, String unit){
        return new Dose(doseSize, inventory, unit);
    }
}
