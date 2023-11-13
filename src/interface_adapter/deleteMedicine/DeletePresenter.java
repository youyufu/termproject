package interface_adapter.deleteMedicine;

import interface_adapter.TableState;
import interface_adapter.TableViewModel;
import interface_adapter.checklistChecked.ChecklistState;
import interface_adapter.checklistChecked.ChecklistViewModel;
import use_case.deleteMedicine.DeleteOutputData;

public class DeletePresenter {
    private final DeleteViewModel deleteViewModel;
    private final ChecklistViewModel checklistViewModel;
    private final TableViewModel tableViewModel;

    public DeletePresenter(DeleteViewModel deleteViewModel, ChecklistViewModel
            checklistViewModel, TableViewModel tableViewModel){
        this.deleteViewModel = deleteViewModel;
        this.checklistViewModel = checklistViewModel;
        this.tableViewModel = tableViewModel;
    }

    public void prepareSuccessView(DeleteOutputData entry){

        DeleteState deleteState = deleteViewModel.getState();
        TableState tableState = tableViewModel.getState();
        String medicineName = entry.getMedication();
        tableState.removeData(medicineName);

        this.deleteViewModel.setState(deleteState);
        deleteViewModel.firePropertyChanged();
        this.tableViewModel.setState(tableState);
        tableViewModel.firePropertyChanged();

    }

    public void updateChecklistState(DeleteOutputData entry){

        ChecklistState checklistState = checklistViewModel.getState();
        String checklistRemoval = entry.getMedication();
        checklistState.removeTakeToday(checklistRemoval);

        this.checklistViewModel.setState(checklistState);
        checklistViewModel.firePropertyChanged();
    }

    public void prepareFailView(String error){
        DeleteState deleteState = deleteViewModel.getState();
        deleteState.setDeleteError(error);
        deleteViewModel.firePropertyChanged();
    }
}
