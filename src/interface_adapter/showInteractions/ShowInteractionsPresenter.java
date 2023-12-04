package interface_adapter.showInteractions;

import interface_adapter.table.TableState;
import interface_adapter.table.TableViewModel;
import use_case.showInteractions.ShowInteractionsOutputBoundary;

public class ShowInteractionsPresenter implements ShowInteractionsOutputBoundary {
    /**
     * A presenter for handling data related to showing medicine interactions.
     */
    final private TableViewModel tableViewModel;

    /**
     * Creates a ShowInteractionsPresenter.
     * @param tableViewModel the view model used to update the medicine table screen.
     */
    public ShowInteractionsPresenter(TableViewModel tableViewModel) {
        this.tableViewModel = tableViewModel;
    }

    /**
     * Prepares a pop-up with a message.
     * @param message the message to be displayed.
     */
    public void preparePopUp(String message){
        TableState tableState = tableViewModel.getState();
        tableState.setMessage(message);
        tableViewModel.setState(tableState);
        tableViewModel.firePopUp();
     }
}
