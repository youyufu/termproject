package use_case.checklistChecked;

public interface ChecklistOutputBoundary {
    void take(ChecklistOutputData checklistOutputData);
    void untake(ChecklistOutputData checklistOutputData);
}
