package entity;

public class MedicineFactory {
    public Medicine createMedicine(String medicine, Integer doseSize, Integer inventory, String unit, Integer[] weeklySchedule, String description, String id){
        Dose dose = createDose(doseSize, inventory, unit);
        return new Medicine(medicine, dose, weeklySchedule, description, id);
    }
    private Dose createDose(Integer doseSize, Integer inventory, String unit){return new Dose(doseSize, inventory, unit);}
}
