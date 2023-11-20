package interface_adapter.enterMedicine;

public class EnterState {
    private String message = null;
    private String medicineName = "";
    private Integer doseSize;
    private String doseUnit = "";
    private Integer doseInventory;
    private Integer sundayDoses;
    private Integer mondayDoses;
    private Integer tuesdayDoses;
    private Integer wednesdayDoses;
    private Integer thursdayDoses;
    private Integer fridayDoses;
    private Integer saturdayDoses;
    private String description = "";
    public EnterState(EnterState copy) {
        message = copy.message;
        medicineName = copy.medicineName;
        doseSize = copy.doseSize;
        doseUnit = copy.doseUnit;
        doseInventory = copy.doseInventory;
        sundayDoses = copy.sundayDoses;
        mondayDoses = copy.mondayDoses;
        tuesdayDoses = copy.tuesdayDoses;
        wednesdayDoses = copy.wednesdayDoses;
        thursdayDoses = copy.thursdayDoses;
        fridayDoses = copy.fridayDoses;
        saturdayDoses = copy.saturdayDoses;
        description = copy.description;
    }
    public EnterState() {}
    public String getMessage() {return message;}

    public void setMessage(String message) {this.message = message;}

    public String getMedicineName() {return medicineName;}
    public Integer getDoseSize() {return doseSize;}
    public String getDoseUnit() {return doseUnit;}
    public Integer getDoseInventory() {return doseInventory;}
    public Integer getSundayDoses() {return sundayDoses;}
    public Integer getMondayDoses() {return mondayDoses;}
    public Integer getTuesdayDoses() {return tuesdayDoses;}
    public Integer getWednesdayDoses() {return wednesdayDoses;}
    public Integer getThursdayDoses() {return thursdayDoses;}
    public Integer getFridayDoses() {return fridayDoses;}
    public Integer getSaturdayDoses() {return saturdayDoses;}
    public String getDescription() {return description;}
    public void setMedicineName(String name) {medicineName = name;}
    public void setDoseSize(Integer doseSize) {this.doseSize = doseSize;}
    public void setDoseUnit(String doseUnit) {this.doseUnit = doseUnit;}
    public void setFridayDoses(Integer fridayDoses) {this.fridayDoses = fridayDoses;}
    public void setMondayDoses(Integer mondayDoses) {this.mondayDoses = mondayDoses;}
    public void setSaturdayDoses(Integer saturdayDoses) {this.saturdayDoses = saturdayDoses;}
    public void setSundayDoses(Integer sundayDoses) {this.sundayDoses = sundayDoses;}
    public void setThursdayDoses(Integer thursdayDoses) {this.thursdayDoses = thursdayDoses;}
    public void setTuesdayDoses(Integer tuesdayDoses) {this.tuesdayDoses = tuesdayDoses;}
    public void setWednesdayDoses(Integer wednesdayDoses) {this.wednesdayDoses = wednesdayDoses;}
    public void setDoseInventory(Integer doseInventory) {this.doseInventory = doseInventory;}
    public void setDescription(String description) {this.description = description;}
}
