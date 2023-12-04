package use_case.checklistChecked;

public interface ChecklistOutputBoundary {
    /**
     * ChecklistOutputBoundary is an interface in the use case layer.
     */

    /**
     * Takes the output data for take method on a medication.
     *
     * @param checklistOutputData The output data for checklist.
     */
    void take(ChecklistOutputData checklistOutputData);

    /**
     * Takes the output data for 'untake' operation on a medication.
     *
     * @param checklistOutputData The output data for checklist.
     */
    void untake(ChecklistOutputData checklistOutputData);
}
