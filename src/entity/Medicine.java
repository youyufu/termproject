package entity;

public class Medicine {
    /**
     * The medicine entity responsible for containing
     * all information pertaining to the medicine that is essential for the user.
     */
    private String name;

    private Dose doses;

    private Integer[] weeklySchedule;

    private String description;
    private String id;

    /**
     * A constructor for the medicine entity.
     * @param medicine THe medicine name.
     * @param doses the dosage entity pertaining the quantity and type of dosage.
     * @param weeklySchedule The weekly schedule of dosages to be taken.
     * @param description A description pertaining any essential information
     *                   for the usage and/or function of medicine.
     * @param id the id associated with the medicine to be storedd.
     */
    public Medicine(String medicine, Dose doses, Integer[] weeklySchedule, String description, String id) {
        this.name = medicine;
        this.doses = doses;
        this.weeklySchedule = weeklySchedule;
        this.description = description;
        this.id = id;
    }

    /**
     * A getter for the medicine name
     * @return the medicine name
     */
    public String getName() {return this.name;}

    /**
     * A getter for the medicine's weekly schedule.
     * @return the medicine's weekly schedule.
     */
    public Integer[] getWeeklySchedule() {return this.weeklySchedule;}

    /**
     * A getter for the dosage quantity and unit type.
     * @return the dosage quantity and unit type.
     */
    public String getDoseString() {return String.valueOf(doses.getSize()) + " " + doses.getUnit();}

    /**
     * A getter for the dosage inventory and unit type.
     * @return the dosage inventory and unit type.
     */
    public String getInventoryString() {return String.valueOf(doses.getInventory()) + " " + doses.getUnit();}

    /**
     * A getter for the description pertaining to the usage/function of the medicine.
     * @return the description pertaining to the usage/function of the medicine.
     */
    public String getDescription() {return this.description;}

    /**
     * A getter that returns the Dose entity param in the medicine.
     * @return the Dose entity param in the medicine.
     */
    public Dose getDose() {return this.doses;}

    /**
     * A getter for the id associated with the medicine.
     * @return the id associated with the medicine.
     */
    public String getId() {return this.id;}
}
