package interface_adapter.showInteractions;

import interface_adapter.TableViewModel;
import interface_adapter.ViewManagerModel;
import use_case.showInteractions.ShowInteractionsOutputBoundary;
import view.MainView;

public class ShowInteractionsPresenter implements ShowInteractionsOutputBoundary {
    final private TableViewModel tableViewModel;

    public ShowInteractionsPresenter(TableViewModel tableViewModel) {
        this.tableViewModel = tableViewModel;
    }

    public void preparePopUp(){
        tableViewModel.firePopUp();
     }
}
