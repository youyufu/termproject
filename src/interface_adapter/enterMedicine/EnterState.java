package interface_adapter.enterMedicine;

public class EnterState {
    private String enterError = null;
    private String medicineName = "";
    private String doseSize = "";
    private String doseUnit = "";
    private String doseInventory = "";
    private String sundayDoses = "";
    private String mondayDoses = "";
    private String tuesdayDoses = "";
    private String wednesdayDoses = "";
    private String thursdayDoses = "";
    private String fridayDoses = "";
    private String saturdayDoses = "";
    private String description = "";
    public EnterState(EnterState copy) {
        enterError = copy.enterError;
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
    public String getEnterError() {return enterError;}

    public void setEnterError(String enterError) {this.enterError = enterError;}

    public String getMedicineName() {return medicineName;}
    public String getDoseSize() {return doseSize;}
    public String getDoseUnit() {return doseUnit;}
    public String getDoseInventory() {return doseInventory;}
    public String getSundayDoses() {return sundayDoses;}
    public String getMondayDoses() {return mondayDoses;}
    public String getTuesdayDoses() {return tuesdayDoses;}
    public String getWednesdayDoses() {return wednesdayDoses;}
    public String getThursdayDoses() {return thursdayDoses;}
    public String getFridayDoses() {return fridayDoses;}
    public String getSaturdayDoses() {return saturdayDoses;}
    public String getDescription() {return description;}
    public void setMedicineName(String name) {medicineName = name;}
    public void setDoseSize(String doseSize) {this.doseSize = doseSize;}
    public void setDoseUnit(String doseUnit) {this.doseUnit = doseUnit;}
    public void setFridayDoses(String fridayDoses) {this.fridayDoses = fridayDoses;}
    public void setMondayDoses(String mondayDoses) {this.mondayDoses = mondayDoses;}
    public void setSaturdayDoses(String saturdayDoses) {this.saturdayDoses = saturdayDoses;}
    public void setSundayDoses(String sundayDoses) {this.sundayDoses = sundayDoses;}
    public void setThursdayDoses(String thursdayDoses) {this.thursdayDoses = thursdayDoses;}
    public void setTuesdayDoses(String tuesdayDoses) {this.tuesdayDoses = tuesdayDoses;}
    public void setWednesdayDoses(String wednesdayDoses) {this.wednesdayDoses = wednesdayDoses;}
    public void setDoseInventory(String doseInventory) {this.doseInventory = doseInventory;}
    public void setDescription(String description) {this.description = description;}
}
