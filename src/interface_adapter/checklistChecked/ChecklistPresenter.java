package interface_adapter.checklistChecked;

import interface_adapter.table.TableState;
import interface_adapter.table.TableViewModel;
import use_case.checklistChecked.ChecklistOutputBoundary;
import use_case.checklistChecked.ChecklistOutputData;
import javax.swing.*;

public class ChecklistPresenter implements ChecklistOutputBoundary {
    /**
     * ChecklistPresenter is a presenter in the interface adapter layer,
     * handling the presentation for checklist.
     */

    private final ChecklistViewModel checklistViewModel;
    private final TableViewModel tableViewModel;

    /**
     * Constructs a new ChecklistPresenter with given view models.
     *
     * @param checklistViewModel The view model for checklist.
     * @param tableViewModel The view model for the table view.
     */
    public ChecklistPresenter(ChecklistViewModel checklistViewModel, TableViewModel tableViewModel) {
        this.checklistViewModel = checklistViewModel;
        this.tableViewModel = tableViewModel;
    }

    /**
     * ChecklistOutputData takes the logic to be executed when a medication is taken.
     *
     * @param checklistOutputData The data object containing information about the
     *                            medication and its current state.
     */
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
        if (Integer.parseInt(dosesLeft) < 14) {
            checklistViewModel.firePropertyChangedAddLow(new String[]{name, dosesLeft});
        }
        if (Integer.parseInt(dosesLeft) == 0) {
            checklistViewModel.addRestock(name);
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    checklistViewModel.firePropertyChanged();
                }
            });
        }
    }

    /**
     * ChecklistOutputData takes the logic to be executed when a medication's taken status is reversed,
     *
     * @param checklistOutputData The data object containing information about the
     *                            medication and its updated state.
     */
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
        if (Integer.parseInt(dosesLeft) >= 14) {
            checklistViewModel.firePropertyChangedRemoveLow(name);
        }
        if (checklistViewModel.getRestock().contains(name)) {
            checklistViewModel.removeRestock(name);
        }
    }
}
