package interface_adapter.deleteMedicine;

import interface_adapter.TableState;
import interface_adapter.TableViewModel;
import interface_adapter.checklistChecked.ChecklistState;
import interface_adapter.checklistChecked.ChecklistViewModel;
import interface_adapter.enterMedicine.EnterState;
import interface_adapter.enterMedicine.EnterViewModel;
import use_case.deleteMedicine.DeleteOutputData;
import use_case.enterMedicine.EnterOutputData;

public class DeletePresenter {
    private DeleteViewModel deleteViewModel;
    private ChecklistViewModel checklistViewModel;
    private TableViewModel tableViewModel;

    public DeletePresenter(DeleteViewModel deleteViewModel, ChecklistViewModel
            checklistViewModel, TableViewModel tableViewModel){
        this.deleteViewModel = deleteViewModel;
        this.checklistViewModel = checklistViewModel;
        this.tableViewModel = tableViewModel;
    }

    public void prepareSuccessView(DeleteOutputData entry){

        DeleteState deleteState = deleteViewModel.getState();
        TableState tableState = tableViewModel.getState();
        String[] tableData = new String[]{entry.toString()};
        tableState.removeData(tableData);

        this.deleteViewModel.setState(deleteState);
        deleteViewModel.firePropertyChanged();
        this.tableViewModel.setState(tableState);
        tableViewModel.firePropertyChanged();

    }

    public void updateChecklistState(EnterOutputData entry){

        ChecklistState checklistState = checklistViewModel.getState();
        String[] checklistAddition = new String[]{entry.getMedication(), entry.getDose()};
        checklistState.addTakeToday(checklistAddition);

        this.checklistViewModel.setState(checklistState);
        checklistViewModel.firePropertyChanged();
    }

    public void prepareFailView(String error){
        EnterState enterState = enterViewModel.getState();
        enterState.setEnterError(error);
        enterViewModel.firePropertyChanged();
    }
}
