package interface_adapter.deleteMedicine;

public class DeleteState {
    /**
     * A class indicating the state of the delete view model.
     */
    private String deleteError = null;
    private String medicineName = "";

    /**
     * Constructor to create the DeleteState object.
     */
    public DeleteState() {}

    /**
     * Signifies a possible problem with the deletion of the medicine.
     * @return the deletion error.
     */
    public String getDeleteError() {return deleteError;}

    /**
     * Get the name of the entered medicine that will be deleted.
     * @return the name of the medicine.
     */
    public String getMedicineName() {return medicineName;}

    /**
     * Sets a deletion error.
     * @param deleteError The error that will be set for the delete error.
     */
    public void setDeleteError(String deleteError) {this.deleteError = deleteError;}

    /**
     * Sets the medicine name.
     * @param medicineName the name that will be set for the medicine name.
     */
    public void setMedicineName(String medicineName) {this.medicineName = medicineName;}
}
