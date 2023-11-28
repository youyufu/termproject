package use_case.enterMedicine;

public interface EnterOutputBoundary {
    /**
     * An interface to prepare data to be passed to the presenter.
     */

    /**
     * Prepares the view for a medicine being successfully entered.
     * @param enterOutputData the data to be passed.
     */
    void prepareSuccessView(EnterOutputData enterOutputData);

    /**
     * Prepares a popup message.
     * @param message the message to be displayed.
     */
    void preparePopUp(String message);

    /**
     * Prepares the ChecklistView to be updated
     * @param enterOutputData the data to be passed.
     */
    void updateChecklistView(EnterOutputData enterOutputData);

    /**
     * Prepares the low remaining doses view to be updated.
     * @param enterOutputData the data to be passed.
     */
    void updateLowView(EnterOutputData enterOutputData);
}
