package app;

import data_access.MedicineDataAccessInterface;
import entity.Medicine;
import interface_adapter.table.TableViewModel;
import interface_adapter.checklistChecked.ChecklistController;
import interface_adapter.checklistChecked.ChecklistPresenter;
import interface_adapter.checklistChecked.ChecklistViewModel;
import interface_adapter.switchView.SwitchViewController;
import use_case.checklistChecked.ChecklistInputBoundary;
import use_case.checklistChecked.ChecklistInteractor;
import use_case.checklistChecked.ChecklistOutputBoundary;
import view.ChecklistView;

import java.util.HashMap;

public class ChecklistUseCaseFactory {
    /**
     * ChecklistUseCaseFactory is a factory class responsible for the creation and configuration
     * of components related to the checklist use case in the application.
     */

    private ChecklistUseCaseFactory() {}

    /**
     * Creates and configures the ChecklistView for the checklist use case.
     *
     * @param switchViewController The controller for switching views.
     * @param checklistViewModel The view model for checklist use case.
     * @param tableViewModel The view model for table view.
     * @param medicineDAO The DAO for medicine data.
     * @return ChecklistView The configured view for checklist.
     */
    public static ChecklistView create(SwitchViewController switchViewController, ChecklistViewModel checklistViewModel,
                                       TableViewModel tableViewModel, MedicineDataAccessInterface medicineDAO) {
        ChecklistController checklistController = ChecklistUseCaseFactory.createChecklistUseCase(checklistViewModel, tableViewModel, medicineDAO);
        HashMap<String, Medicine> userMedicines = medicineDAO.getUserMedicines();
        ChecklistView checklistView = new ChecklistView(switchViewController, checklistController, checklistViewModel);
        for (Medicine medicine: userMedicines.values()) {
            if (medicine.getDose().getDosesRemaining() < 14) {
                String[] checklistData = new String[]{medicine.getName(), String.valueOf(medicine.getDose().getDosesRemaining())};
                checklistViewModel.firePropertyChangedAddLow(checklistData);
            }
        }
        HashMap<String, Integer> todayChecklist = medicineDAO.getTodayChecklist();
        for (String medicine:todayChecklist.keySet()) {
            String[] checklistData = new String[]{medicine, userMedicines.get(medicine).getDoseString()};
            for (int i = 0; i < Math.min(userMedicines.get(medicine).getWeeklySchedule()[medicineDAO.getTodayDay()]
                            - todayChecklist.get(medicine), userMedicines.get(medicine).getDose().getDosesRemaining()); i++) {
                checklistViewModel.firePropertyChangedAddTake(checklistData);
            } if (userMedicines.get(medicine).getDose().getDosesRemaining() == 0) {
                checklistViewModel.addRestock(medicine);
            }
        }
        return checklistView;
    }

    /**
     * Creates and configures the ChecklistController for the checklist use case.
     *
     * @param checklistViewModel The view model for checklist.
     * @param tableViewModel The view model for the table view.
     * @param medicineDAO The data access object for interacting with medicine data.
     * @return ChecklistController The configured controller for the checklist use case.
     */
    public static ChecklistController createChecklistUseCase(ChecklistViewModel checklistViewModel,
                                                             TableViewModel tableViewModel,
                                                             MedicineDataAccessInterface medicineDAO) {
        ChecklistOutputBoundary checklistOutputBoundary = new ChecklistPresenter(checklistViewModel, tableViewModel);
        ChecklistInputBoundary checklistInputBoundary = new ChecklistInteractor(checklistOutputBoundary, medicineDAO);
        return new ChecklistController(checklistInputBoundary);
    }
}
