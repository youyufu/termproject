package use_case.deleteMedicine;

public class DeleteOutputData {
    private final String medication;
    public DeleteOutputData(String medication) {
        this.medication = medication;
    }

    public String getMedication() {
        return medication;
    }

}