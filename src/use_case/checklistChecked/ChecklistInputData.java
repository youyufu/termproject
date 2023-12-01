package use_case.checklistChecked;

public class ChecklistInputData {
    /**
     * An object representing the checklist to be checked.
     */
    private String name;
    private Integer change;

    /**
     * Creates a ChecklistInputData object.
     * @param name the name of the medicine.
     * @param change the change made.
     */
    public ChecklistInputData(String name, Integer change) {
        this.name = name;
        this.change = change;
    }

    /**
     * Get the name of medicine.
     * @return tne name of medicine.
     */
    public String getName() {return this.name;}

    /**
     * Get the change of the medicine
     * @return an integer either 1 or -1.
     */
    public Integer getChange() {return this.change;}
}
