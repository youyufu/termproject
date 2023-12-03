package interface_adapter.showInteractions;

import interface_adapter.table.TableState;
import interface_adapter.table.TableViewModel;
import use_case.showInteractions.ShowInteractionsOutputBoundary;

public class ShowInteractionsPresenter implements ShowInteractionsOutputBoundary {
    final private TableViewModel tableViewModel;

    public ShowInteractionsPresenter(TableViewModel tableViewModel) {
        this.tableViewModel = tableViewModel;
    }

    public void preparePopUp(String message){
        TableState tableState = tableViewModel.getState();
        tableState.setMessage(message);
        tableViewModel.setState(tableState);
        tableViewModel.firePopUp(message);
     }
}
