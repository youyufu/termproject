package use_case.deleteMedicine;


public class DeleteInputData {

    final private String medicineName;
    public DeleteInputData(String medicine) {this.medicineName = medicine;}

    public String getMedicineName() {return medicineName;}
}
