package app;

import data_access.MedicineAPICallsInterface;
import data_access.MedicineDataAccessInterface;
import entity.MedicineFactory;
import interface_adapter.TableViewModel;
import interface_adapter.checklistChecked.ChecklistViewModel;
import interface_adapter.enterMedicine.EnterController;
import interface_adapter.enterMedicine.EnterPresenter;
import interface_adapter.enterMedicine.EnterViewModel;
import interface_adapter.switchView.SwitchViewController;
import use_case.enterMedicine.EnterInputBoundary;
import use_case.enterMedicine.EnterInteractor;
import use_case.enterMedicine.EnterOutputBoundary;
import view.EnterView;

public class EnterUseCaseFactory {
    private EnterUseCaseFactory(){}
    public static EnterView create(SwitchViewController switchViewController, EnterViewModel enterViewModel,
                                   ChecklistViewModel checklistViewModel, TableViewModel tableViewModel,
                                   MedicineDataAccessInterface medicineDAO, MedicineAPICallsInterface medicineAPICallsObject){
        EnterController enterController = createEnterUseCase(enterViewModel, checklistViewModel, tableViewModel, medicineDAO, medicineAPICallsObject);
        return new EnterView(switchViewController, enterController, enterViewModel);
    }
    public static EnterController createEnterUseCase(EnterViewModel enterViewModel, ChecklistViewModel checklistViewModel,
                                                     TableViewModel tableViewModel, MedicineDataAccessInterface medicineDAO, MedicineAPICallsInterface medicineAPICallsObject) {
        EnterOutputBoundary enterPresenter = new EnterPresenter(enterViewModel, checklistViewModel, tableViewModel);
        MedicineFactory medicineFactory = new MedicineFactory();
        EnterInputBoundary enterInteractor = new EnterInteractor(medicineDAO, enterPresenter, medicineFactory, medicineAPICallsObject);
        return new EnterController(enterInteractor);
    }
}
