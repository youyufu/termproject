package interface_adapter.checklistChecked;

import interface_adapter.TableState;
import interface_adapter.TableViewModel;
import use_case.checklistChecked.ChecklistOutputBoundary;
import use_case.checklistChecked.ChecklistOutputData;

import javax.swing.*;

import static java.lang.Thread.sleep;

public class ChecklistPresenter implements ChecklistOutputBoundary {
    private final ChecklistViewModel checklistViewModel;
    private final TableViewModel tableViewModel;
    public ChecklistPresenter(ChecklistViewModel checklistViewModel, TableViewModel tableViewModel) {
        this.checklistViewModel = checklistViewModel;
        this.tableViewModel = tableViewModel;
    }
    @Override
    public void take(ChecklistOutputData checklistOutputData) {
        String name = checklistOutputData.getName();
        String inventory = checklistOutputData.getInventory();
        String dosesLeft = checklistOutputData.getDosesLeft();

        TableState tableState = tableViewModel.getState();
        tableState.editInventory(name, inventory);
        tableViewModel.setState(tableState);
        tableViewModel.firePropertyChanged();

        checklistViewModel.firePropertyChangedUpdateLow(new String[] {name, dosesLeft});
        if (Integer.valueOf(dosesLeft) == 0) {
            checklistViewModel.addRestock(name);
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    checklistViewModel.firePropertyChanged();
                }
            });
        }
    }

    @Override
    public void untake(ChecklistOutputData checklistOutputData) {
        String name = checklistOutputData.getName();
        String inventory = checklistOutputData.getInventory();
        String dosesLeft = checklistOutputData.getDosesLeft();

        TableState tableState = tableViewModel.getState();
        tableState.editInventory(name, inventory);
        tableViewModel.setState(tableState);
        tableViewModel.firePropertyChanged();

        checklistViewModel.firePropertyChangedUpdateLow(new String[] {name, dosesLeft});
        if (checklistViewModel.getRestock().contains(name)) {
            checklistViewModel.removeRestock(name);
        }
    }
}
