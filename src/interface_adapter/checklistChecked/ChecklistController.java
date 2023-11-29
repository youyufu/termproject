package interface_adapter.checklistChecked;

import use_case.checklistChecked.ChecklistInputBoundary;
import use_case.checklistChecked.ChecklistInputData;

public class ChecklistController {
    private final ChecklistInputBoundary checklistInputBoundary;
    public ChecklistController(ChecklistInputBoundary checklistInputBoundary){
        this.checklistInputBoundary = checklistInputBoundary;
    }
    public void execute(String medicine, Integer change) {
        ChecklistInputData checklistInputData = new ChecklistInputData(medicine, change);
        checklistInputBoundary.execute(checklistInputData);
    }
}
