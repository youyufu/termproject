package interface_adapter.enterMedicine;

public class EnterState {
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
