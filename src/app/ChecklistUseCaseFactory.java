package app;

import data_access.MedicineDataAccessInterface;
import entity.Medicine;
import interface_adapter.TableViewModel;
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
    private ChecklistUseCaseFactory() {}
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
    public static ChecklistController createChecklistUseCase(ChecklistViewModel checklistViewModel,
                                                             TableViewModel tableViewModel,
                                                             MedicineDataAccessInterface medicineDAO) {
        ChecklistOutputBoundary checklistOutputBoundary = new ChecklistPresenter(checklistViewModel, tableViewModel);
        ChecklistInputBoundary checklistInputBoundary = new ChecklistInteractor(checklistOutputBoundary, medicineDAO);
        return new ChecklistController(checklistInputBoundary);
    }
}
