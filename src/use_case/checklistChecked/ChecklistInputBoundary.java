package use_case.checklistChecked;

public interface ChecklistInputBoundary {
    /**
     * An interface for passing the data to the interactor.
     */

    /**
     * Passes the data the user entered to the interactor.
     * @param checklistInputData the data to be passed.
     */
    void execute(ChecklistInputData checklistInputData);
}
