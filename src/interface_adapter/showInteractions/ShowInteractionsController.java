package interface_adapter.showInteractions;

import use_case.showInteractions.ShowInteractionsInputBoundary;

public class ShowInteractionsController {
    /**
     * A controller responsible for letting the input boundary know to execute.
     */

    final ShowInteractionsInputBoundary showInteractionsInputBoundary;

    /**
     * Creates a ShowInteractionsController.
     * @param showInteractionsInputBoundary the associated input boundary.
     */
    public ShowInteractionsController(ShowInteractionsInputBoundary showInteractionsInputBoundary) {
        this.showInteractionsInputBoundary = showInteractionsInputBoundary;
    }

    /**
     * Calls the input boundary.
     */
    public void execute() {
        showInteractionsInputBoundary.execute();
    }
}