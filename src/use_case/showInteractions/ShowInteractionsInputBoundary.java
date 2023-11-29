package use_case.showInteractions;

public interface ShowInteractionsInputBoundary {
    /**
     * An interface for passing the data to the interactor.
     */

    /**
     * Passes the data to the interactor.
     * @param showInteractionsInputData the data to be passed
     */
    void execute(ShowInteractionsInputData showInteractionsInputData);
}
