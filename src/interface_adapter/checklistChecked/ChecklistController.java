package interface_adapter.checklistChecked;

import use_case.checklistChecked.ChecklistInputBoundary;
import use_case.checklistChecked.ChecklistInputData;

public class ChecklistController {
    /**
     * ChecklistController is a controller in the interface adapter layer, managing
     * interactions related to checklist.
     */

    private final ChecklistInputBoundary checklistInputBoundary;

    /**
     * Constructs a new ChecklistController with a given ChecklistInputBoundary.
     *
     * @param checklistInputBoundary The input boundary for checklist.
     */
    public ChecklistController(ChecklistInputBoundary checklistInputBoundary){
        this.checklistInputBoundary = checklistInputBoundary;
    }

    /**
     * Executes the checklist operation based on the given medicine name and change value.
     *
     * @param medicine The name of the medicine.
     * @param change The change in the checklist.
     */
    public void execute(String medicine, Integer change) {
        ChecklistInputData checklistInputData = new ChecklistInputData(medicine, change);
        checklistInputBoundary.execute(checklistInputData);
    }
}
