package use_case.enterMedicine;

public class EnterOutputData {
    private final String medicine;
    public EnterOutputData(String medicine) {
        this.medicine = medicine;
    }

    public String getMedicine() {return medicine;}
}
