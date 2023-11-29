package interface_adapter.showInteractions;

import use_case.deleteMedicine.DeleteInputData;
import use_case.showInteractions.ShowInteractionsInputBoundary;

public class ShowInteractionsController {

    final ShowInteractionsInputBoundary showInteractionsInputBoundary;

    public ShowInteractionsController(ShowInteractionsInputBoundary showInteractionsInputBoundary) {
        this.showInteractionsInputBoundary = showInteractionsInputBoundary;
    }

    public void execute() {
        showInteractionsInputBoundary.execute();
    }
}