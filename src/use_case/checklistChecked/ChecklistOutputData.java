package use_case.checklistChecked;

public class ChecklistOutputData {
    private String name;
    private String inventory;
    private String dosesLeft;
    public ChecklistOutputData(String name, String inventory, String dosesLeft) {
        this.name = name;
        this.inventory = inventory;
        this.dosesLeft = dosesLeft;
    }

    public String getName() {return name;}
    public String getDosesLeft() {return dosesLeft;}
    public String getInventory() {return inventory;}
}
