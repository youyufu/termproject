package entity;

public class MedicineFactory {
    /**
     * Constructor class that
     * @param medicine
     * @param doseSize
     * @param inventory
     * @param unit
     * @param weeklySchedule
     * @param description
     * @param id
     * @return
     */
    public Medicine createMedicine(String medicine, Integer doseSize, Integer inventory, String unit, Integer[] weeklySchedule, String description, String id){
        Dose dose = createDose(doseSize, inventory, unit);
        return new Medicine(medicine, dose, weeklySchedule, description, id);
    }
    private Dose createDose(Integer doseSize, Integer inventory, String unit){return new Dose(doseSize, inventory, unit);}
}
