package interface_adapter.deleteMedicine;

import interface_adapter.TableState;
import interface_adapter.TableViewModel;
import interface_adapter.ViewManagerModel;
import interface_adapter.checklistChecked.ChecklistViewModel;
import use_case.deleteMedicine.DeleteOutputBoundary;
import use_case.deleteMedicine.DeleteOutputData;
import view.MainView;

public class DeletePresenter implements DeleteOutputBoundary {
    private final DeleteViewModel deleteViewModel;
    private final ChecklistViewModel checklistViewModel;
    private final TableViewModel tableViewModel;
    private final ViewManagerModel viewManagerModel;
    public DeletePresenter(DeleteViewModel deleteViewModel, ChecklistViewModel
            checklistViewModel, TableViewModel tableViewModel, ViewManagerModel viewManagerModel){
        this.deleteViewModel = deleteViewModel;
        this.checklistViewModel = checklistViewModel;
        this.tableViewModel = tableViewModel;
        this.viewManagerModel = viewManagerModel;
    }
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
    public void prepareFailView(String error){
        DeleteState deleteState = deleteViewModel.getState();
        deleteState.setDeleteError(error);
        deleteViewModel.firePropertyChanged();
    }
}
