package use_case.enterMedicine;

public class EnterInputData {
    /**
     * An object representing the data of the medicine to be created.
     */
    final private String medicine;
    final private Integer doseSize;
    final private Integer inventory;
    final private String unit;
    final private Integer[] day;
    final private String description;

    /**
     * Creates the input data object.
     * @param medicine the name of the medicine.
     * @param doseSize the size of each dose to be taken.
     * @param unit the unit of the medicine.
     * @param inventory the amount of medicine in inventory.
     * @param day the number of doses to be taken each day of the week.
     * @param description a description of the medicine.
     */
    public EnterInputData(String medicine,
                          Integer doseSize,
                          String unit,
                          Integer inventory,
                          Integer[] day,
                          String description) {
        this.medicine = medicine;
        this.doseSize = doseSize;
        this.inventory = inventory;
        this.unit = unit;
        this.day = day;
        this.description = description;
    }

    /**
     * Gets the medicine attribute.
     * @return the name of the medicine.
     */
    public String getMedicine() {return medicine;}

    /**
     * Gets the doseSize attribute.
     * @return the size of the dose.
     */
    public Integer getDoseSize() {return doseSize;}

    /**
     * Gets the inventory attribute.
     * @return the amount of the medicine remaining.
     */
    public Integer getInventory() {return inventory;}

    /**
     * Gets the unit attribute.
     * @return the unit of the medicine.
     */
    public String getUnit() {return unit;}

    /**
     * Gets the day attribute.
     * @return the number of doses to be taken each day.
     */
    public Integer[] getDay() {return day;}

    /**
     * Gets description attribute.
     * @return the description for the medicine.
     */
    public String getDescription() {return description;}

}
