package interface_adapter.enterMedicine;

import interface_adapter.TableViewModel;
import interface_adapter.checklistChecked.ChecklistViewModel;
import use_case.enterMedicine.EnterOutputData;
import view.ViewManager;

public class EnterPresenter {

    private EnterViewModel enterViewModel;
    private ChecklistViewModel checklistViewModel;
    private TableViewModel tableViewModel;
    private ViewManager viewManager;

    public  EnterPresenter(ViewManager viewManager, EnterViewModel enterViewModel, ChecklistViewModel
            checklistViewModel, TableViewModel tableViewModel){
        this.viewManager = viewManager;
        this.enterViewModel = enterViewModel;
        this.checklistViewModel = checklistViewModel;
        this.tableViewModel = tableViewModel;
    }

    public void prepareSuccessView(EnterOutputData entry){

        EnterState enterState = enterViewModel.getState();
        enterState.setMedicineName(entry.getMedicineName());
        enterState.setDoseSize(entry.getDoseSize());
        enterState.setDoseUnit(entry.getDoseUnit());
        enterState.setDoseInventory(entry.getDoseInventory());
        enterState.setSundayDoses(entry.getSundayDoses());
        enterState.setMondayDoses(entry.getMondayDoses());
        enterState.setTuesdayDoses(entry.getTuesdayDoses());
        enterState.setWednesdayDoses(entry.getWednesdayDoses());
        enterState.setThursdayDoses(entry.getThursdayDoses());
        enterState.setFridayDoses(entry.getFridayDoses());
        enterState.setSaturdayDoses(entry.getSaturdayDoses);
        enterState.setDescription(entry.getDescription());

        this.enterViewModel.setState(enterState);
        enterViewModel.firePropertyChanged();

        viewManager.propertyChange();

    }

    public void prepareFailView(String error){
        EnterState enterState = enterViewModel.getState();
        enterState.setEnterError(error);
        enterViewModel.firePropertyChanged();
    }
}
