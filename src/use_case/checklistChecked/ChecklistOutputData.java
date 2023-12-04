package use_case.checklistChecked;

/**
 * ChecklistOutputData is a data class that holds medicine.
 */
public class ChecklistOutputData {
    private String name;
    private String inventory;
    private String dosesLeft;

    /**
     * Constructs a new instance of ChecklistOutputData.
     *
     * @param name The name of the medication.
     * @param inventory The inventory of the medication.
     * @param dosesLeft The number of doses left for the medication.
     */
    public ChecklistOutputData(String name, String inventory, String dosesLeft) {
        this.name = name;
        this.inventory = inventory;
        this.dosesLeft = dosesLeft;
    }

    /**
     * Get the name of the medication.
     *
     * @return The name of the medication.
     */
    public String getName() {return name;}

    /**
     * Get the number of doses left for the medication.
     *
     * @return The number of doses left.
     */
    public String getDosesLeft() {return dosesLeft;}

    /**
     * Get the inventory status of the medication.
     *
     * @return The inventory status.
     */
    public String getInventory() {return inventory;}
}
