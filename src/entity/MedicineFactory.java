package entity;

public class MedicineFactory {
    /**
     * Constructor class that composes the Medicine object with the Dosage object as a param.
     * @param medicine The medicine name.
     * @param doseSize The dosage size.
     * @param inventory The dosage inventory size.
     * @param unit The unit type of the dosage.
     * @param weeklySchedule The weekly schedule of taking the medicine.
     * @param description A description pertaining any essential information
     *                     for the usage and/or function of medicine.
     * @param id the id associated with the Medicine object.
     * @return A Medicine object.
     */
    public Medicine createMedicine(String medicine, Integer doseSize, Integer inventory, String unit,
                                   Integer[] weeklySchedule, String description, String id){
        Dose dose = createDose(doseSize, inventory, unit);
        return new Medicine(medicine, dose, weeklySchedule, description, id);
    }

    /**
     * A constructor that creates the Dosage object.
     * @param doseSize The dosage size.
     * @param inventory The dosage inventory size.
     * @param unit The unit type of the dosage.
     * @return A dosage object.
     */
    private Dose createDose(Integer doseSize, Integer inventory, String unit){return new Dose(doseSize, inventory, unit);}
}
