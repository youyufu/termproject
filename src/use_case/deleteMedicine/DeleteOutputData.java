package use_case.deleteMedicine;

public class DeleteOutputData {
    private final String medication;

    /**
     * Creates the output data object.
     * @param medication the name of the medicine.
     */
    public DeleteOutputData(String medication) {
        this.medication = medication;
    }

    /**
     * Gets the medicine attribute.
     * @return the name of the medicine.
     */
    public String getMedication() {
        return medication;
    }

}