package interface_adapter.deleteMedicine;

public class DeleteState {
    private String deleteError = null;
    private String medicineName = "";
    public DeleteState() {}
    public String getDeleteError() {return deleteError;}
    public String getMedicineName() {return medicineName;}
    public void setDeleteError(String deleteError) {this.deleteError = deleteError;}
    public void setMedicineName(String medicineName) {this.medicineName = medicineName;}
}
