package use_case.deleteMedicine;


public class DeleteInputData {

    final private String medicineName;
    /**
     * A constructor for DeleteInputData which takes the medicine name and initializes it.
     * @param medicine the name of the medicine.
     */
    public DeleteInputData(String medicine) {this.medicineName = medicine;}

    /**
     * Gets the medicine attribute.
     * @return the medicine name.
     */
    public String getMedicineName() {return medicineName;}
}
