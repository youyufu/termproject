package entity;

public class MedicineFactory {
    public Medicine createMedicine(String medicine, Dose dose, Integer[] weeklySchedule, String description, String id){
        return new Medicine(medicine, dose, weeklySchedule, description, id);
    }
    public Dose createDose(Integer doseSize, Integer inventory, String unit){return new Dose(doseSize, inventory, unit);}
}
