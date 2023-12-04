package app;

import data_access.MedicineAPICallsInterface;
import data_access.MedicineDataAccessInterface;
import entity.MedicineFactory;
import interface_adapter.table.TableViewModel;
import interface_adapter.ViewManagerModel;
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
    /**
     * It helps the instantiation and configuration of the enter controllers,
     * view models, and other associated components.
     */

    private EnterUseCaseFactory(){}

    /**
     * Creates and returns an EnterView configured with all necessary components
     * for the enter use case.
     *
     * @param switchViewController The controller for switching views.
     * @param enterViewModel The view model for enter functionality.
     * @param checklistViewModel The view model for checklist functionality.
     * @param tableViewModel The view model for table view.
     * @param viewManagerModel The model managing different views.
     * @param medicineDAO The data access object for medicine data.
     * @param medicineAPICallsObject The interface for API calls related to medicine.
     * @return EnterView The configured view for enter functionality.
     */
    public static EnterView create(SwitchViewController switchViewController, EnterViewModel enterViewModel,
                                   ChecklistViewModel checklistViewModel, TableViewModel tableViewModel, ViewManagerModel viewManagerModel,
                                   MedicineDataAccessInterface medicineDAO, MedicineAPICallsInterface medicineAPICallsObject){
        EnterController enterController = createEnterUseCase(enterViewModel, checklistViewModel,viewManagerModel, tableViewModel, medicineDAO, medicineAPICallsObject);
        return new EnterView(switchViewController, enterController, enterViewModel);
    }

    /**
     * Creates and returns an EnterController for the enter use case.
     *
     * @param enterViewModel The view model for enter.
     * @param checklistViewModel The view model for checklist.
     * @param viewManagerModel The model managing different views.
     * @param tableViewModel The view model for table view.
     * @param medicineDAO The DAO for interacting with medicine data.
     * @param medicineAPICallsObject The interface for API calls related to medicine.
     * @return EnterController The configured controller for the enter use case.
     */
    public static EnterController createEnterUseCase(EnterViewModel enterViewModel, ChecklistViewModel checklistViewModel, ViewManagerModel viewManagerModel,
                                                     TableViewModel tableViewModel, MedicineDataAccessInterface medicineDAO, MedicineAPICallsInterface medicineAPICallsObject) {
        EnterOutputBoundary enterPresenter = new EnterPresenter(enterViewModel, checklistViewModel, tableViewModel, viewManagerModel);
        MedicineFactory medicineFactory = new MedicineFactory();
        EnterInputBoundary enterInteractor = new EnterInteractor(medicineDAO, enterPresenter, medicineFactory, medicineAPICallsObject);
        return new EnterController(enterInteractor);
    }
}
