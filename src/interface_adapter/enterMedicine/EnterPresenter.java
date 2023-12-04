package interface_adapter.enterMedicine;

import interface_adapter.table.TableState;
import interface_adapter.table.TableViewModel;
import interface_adapter.ViewManagerModel;
import interface_adapter.checklistChecked.ChecklistViewModel;
import use_case.enterMedicine.EnterOutputBoundary;
import use_case.enterMedicine.EnterOutputData;
import view.MainView;

public class EnterPresenter implements EnterOutputBoundary {
    /**
     * A presenter for handling data related to entering a medicine.
     */
    private final EnterViewModel enterViewModel;
    private final ChecklistViewModel checklistViewModel;
    private final TableViewModel tableViewModel;
    private final ViewManagerModel viewManagerModel;

    /**
     * Creates an EnterPresenter.
     * @param enterViewModel The view model used to update the enter medicine screen.
     * @param checklistViewModel the view model used to update the checklist screen.
     * @param tableViewModel the view model used to update the medicine table screen.
     * @param viewManagerModel the view manager model used to switch the current view in the view manager.
     */
    public EnterPresenter(EnterViewModel enterViewModel, ChecklistViewModel
            checklistViewModel, TableViewModel tableViewModel, ViewManagerModel viewManagerModel){
        this.enterViewModel = enterViewModel;
        this.checklistViewModel = checklistViewModel;
        this.tableViewModel = tableViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    /**
     * Updates the view models then changes the active screen to the main screen.
     * @param entry the data to be passed.
     */
    @Override
    public void prepareSuccessView(EnterOutputData entry){
        TableState tableState = tableViewModel.getState();
        String[] tableData = new String[]{entry.getMedication(),
                entry.getDose(), entry.getInventory(), String.valueOf(entry.getSu()), String.valueOf(entry.getM()),
                String.valueOf(entry.getTu()), String.valueOf(entry.getW()), String.valueOf(entry.getTh()),
                String.valueOf(entry.getF()), String.valueOf(entry.getSa()), entry.getDescription()};
        tableState.addData(tableData);
        this.tableViewModel.setState(tableState);
        tableViewModel.firePropertyChanged();
        viewManagerModel.setActiveView(MainView.viewName);
        viewManagerModel.firePropertyChanged();
        this.enterViewModel.setState(new EnterState());
        this.enterViewModel.firePropertyChanged();
    }

    /**
     * Updates the checklist screen
     * @param entry the data to be passed.
     */
    public void updateChecklistView(EnterOutputData entry){
        String[] checklistAddition = new String[]{entry.getMedication(), entry.getDose()};
        checklistViewModel.firePropertyChangedAddTake(checklistAddition);
    }

    /**
     * Updates the screen indicating a low number of doses remaining.
     * @param enterOutputData the data to be passed.
     */
    @Override
    public void updateLowView(EnterOutputData enterOutputData) {
        String[] lowAddition = new String[]{enterOutputData.getMedication(), String.valueOf(enterOutputData.getDosesRemaining())};
        if (enterOutputData.getDosesRemaining() == 0) {
            checklistViewModel.addRestock(enterOutputData.getMedication());
        } checklistViewModel.firePropertyChangedAddLow(lowAddition);
    }

    /**
     * Prepares a pop-up with a message.
     * @param message the message to be displayed.
     */
    @Override
    public void preparePopUp(String message){
        EnterState enterState = enterViewModel.getState();
        enterState.setMessage(message);
        enterViewModel.firePropertyChanged();
    }
}
