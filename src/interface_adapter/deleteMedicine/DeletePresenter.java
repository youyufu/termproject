package interface_adapter.deleteMedicine;

import interface_adapter.table.TableState;
import interface_adapter.table.TableViewModel;
import interface_adapter.ViewManagerModel;
import interface_adapter.checklistChecked.ChecklistViewModel;
import use_case.deleteMedicine.DeleteOutputBoundary;
import use_case.deleteMedicine.DeleteOutputData;
import view.MainView;

public class DeletePresenter implements DeleteOutputBoundary {
    /**
     * A presenter that handles the aftermath of the medicine deletion attempt
     */
    private final DeleteViewModel deleteViewModel;
    private final ChecklistViewModel checklistViewModel;
    private final TableViewModel tableViewModel;
    private final ViewManagerModel viewManagerModel;

    /**
     * A constructor for the
     * presenter that handles the aftermath of the medicine deletion attempt
     * @param deleteViewModel The view model responsible to update the delete screen
     * @param checklistViewModel The view model responsible to update the checklist screen
     * @param tableViewModel The view model responsible to update the table screen
     * @param viewManagerModel The view model responsible to update other View Models
     */
    public DeletePresenter(DeleteViewModel deleteViewModel, ChecklistViewModel
            checklistViewModel, TableViewModel tableViewModel, ViewManagerModel viewManagerModel){
        this.deleteViewModel = deleteViewModel;
        this.checklistViewModel = checklistViewModel;
        this.tableViewModel = tableViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    /**
     * Responsible for preparing the view after the medicine was succesfully deleted
     * @param entry the medicine information data to be passed after deleting medicine.
     */
    public void prepareSuccessView(DeleteOutputData entry){
        TableState tableState = tableViewModel.getState();
        String medicineName = entry.getMedication();
        tableState.removeData(medicineName);
        checklistViewModel.firePropertyChangedRemoveMed(medicineName);
        this.tableViewModel.setState(tableState);
        tableViewModel.firePropertyChanged();
        viewManagerModel.setActiveView(MainView.viewName);
        viewManagerModel.firePropertyChanged();
        this.deleteViewModel.setState(new DeleteState());
        deleteViewModel.firePropertyChanged();
    }

    /**
     * Responsible for preparing the view after the medicine was un-successfully deleted
     * @param error the message that will be passed to the popup.
     */
    public void prepareFailView(String error){
        DeleteState deleteState = deleteViewModel.getState();
        deleteState.setDeleteError(error);
        deleteViewModel.firePropertyChanged();
    }
}
