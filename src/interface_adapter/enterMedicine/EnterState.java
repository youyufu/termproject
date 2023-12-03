package interface_adapter.enterMedicine;

public class EnterState {
    /**
     * A class indicating the state of the enter view model.
     */
    private String message = null;
    private String medicineName = "";
    private Integer doseSize = 0;
    private String doseUnit = "";
    private Integer doseInventory = 0;
    private Integer sundayDoses = 0;
    private Integer mondayDoses = 0;
    private Integer tuesdayDoses = 0;
    private Integer wednesdayDoses = 0;
    private Integer thursdayDoses = 0;
    private Integer fridayDoses = 0;
    private Integer saturdayDoses = 0;
    private String description = "";

    /**
     * Creates an EnterState.
     */
    public EnterState() {}

    /**
     * Returns the set message.
     * @return the message previously set.
     */
    public String getMessage() {return message;}

    /**
     * Set a message.
     * @param message the message to be set.
     */
    public void setMessage(String message) {this.message = message;}

    /**
     * Get the name of the entered medicine.
     * @return the name of the medicine.
     */
    public String getMedicineName() {return medicineName;}

    /**
     * Get the size of each dose for the entered medicine.
     * @return the dose size.
     */
    public Integer getDoseSize() {return doseSize;}

    /**
     * Get the unit of the dose.
     * @return the unit of the dose.
     */
    public String getDoseUnit() {return doseUnit;}

    /**
     * Get the amount of the entered medicine in inventory.
     * @return the dose inventory.
     */
    public Integer getDoseInventory() {return doseInventory;}

    /**
     * Get the number of doses to be taken on Sunday for the entered medicine.
     * @return the number of doses for Sunday.
     */
    public Integer getSundayDoses() {return sundayDoses;}
    /**
     * Get the number of doses to be taken on Monday for the entered medicine.
     * @return the number of doses for Monday.
     */
    public Integer getMondayDoses() {return mondayDoses;}
    /**
     * Get the number of doses to be taken on Tuesday for the entered medicine.
     * @return the number of doses for Tuesday.
     */
    public Integer getTuesdayDoses() {return tuesdayDoses;}
    /**
     * Get the number of doses to be taken on Wednesday for the entered medicine.
     * @return the number of doses for Wednesday.
     */
    public Integer getWednesdayDoses() {return wednesdayDoses;}
    /**
     * Get the number of doses to be taken on Thursday for the entered medicine.
     * @return the number of doses for Thursday.
     */
    public Integer getThursdayDoses() {return thursdayDoses;}
    /**
     * Get the number of doses to be taken on Friday for the entered medicine.
     * @return the number of doses for Friday.
     */
    public Integer getFridayDoses() {return fridayDoses;}
    /**
     * Get the number of doses to be taken on Saturday for the entered medicine.
     * @return the number of doses for Saturday.
     */
    public Integer getSaturdayDoses() {return saturdayDoses;}

    /**
     * Get the description for the entered medicine.
     * @return the description.
     */
    public String getDescription() {return description;}

    /**
     * Set medicineName to name.
     * @param name the name of the medicine.
     */
    public void setMedicineName(String name) {medicineName = name;}

    /**
     * Set doseSize.
     * @param doseSize the size of each dose.
     */
    public void setDoseSize(Integer doseSize) {this.doseSize = doseSize;}

    /**
     * Set doseUnit.
     * @param doseUnit the unit of the dose.
     */
    public void setDoseUnit(String doseUnit) {this.doseUnit = doseUnit;}

    /**
     * Set the number of doses for Friday.
     * @param fridayDoses the number of doses for Friday.
     */
    public void setFridayDoses(Integer fridayDoses) {this.fridayDoses = fridayDoses;}

    /**
     * Set the number of doses for Monday.
     * @param mondayDoses the number of doses for Monday.
     */
    public void setMondayDoses(Integer mondayDoses) {this.mondayDoses = mondayDoses;}

    /**
     * Set the number of doses for Saturday.
     * @param saturdayDoses the number of doses for Saturday.
     */
    public void setSaturdayDoses(Integer saturdayDoses) {this.saturdayDoses = saturdayDoses;}

    /**
     * Set the number of doses for Sunday.
     * @param sundayDoses the number of doses for Sunday.
     */
    public void setSundayDoses(Integer sundayDoses) {this.sundayDoses = sundayDoses;}

    /**
     * Set the number of doses for Thursday.
     * @param thursdayDoses the number of doses for Thursday.
     */
    public void setThursdayDoses(Integer thursdayDoses) {this.thursdayDoses = thursdayDoses;}

    /**
     * Set the number of doses for Tuesday.
     * @param tuesdayDoses the number of doses for Tuesday.
     */
    public void setTuesdayDoses(Integer tuesdayDoses) {this.tuesdayDoses = tuesdayDoses;}

    /**
     * Set the number of doses for Wednesday.
     * @param wednesdayDoses the number of doses for Wednesday.
     */
    public void setWednesdayDoses(Integer wednesdayDoses) {this.wednesdayDoses = wednesdayDoses;}

    /**
     * Set the amount of the medicine in inventory.
     * @param doseInventory the amount of the medicine in inventory.
     */
    public void setDoseInventory(Integer doseInventory) {this.doseInventory = doseInventory;}

    /**
     * Set the description for the medicine.
     * @param description the description to be set.
     */
    public void setDescription(String description) {this.description = description;}
}
