package use_case.showInteractions;

public interface ShowInteractionsOutputBoundary {
    /**
     * An interface to prepare data to be passed to the presenter.
     */

    /**
     * Prepares a popup message.
     * @param message the message to be displayed.
     */
    void preparePopUp(String message);
}
