package app;

import data_access.MedicineDataAccessInterface;
import entity.Medicine;
import interface_adapter.TableViewModel;
import interface_adapter.checklistChecked.ChecklistController;
import interface_adapter.checklistChecked.ChecklistState;
import interface_adapter.checklistChecked.ChecklistViewModel;
import interface_adapter.switchView.SwitchViewController;
import view.ChecklistView;

import java.util.HashMap;

public class ChecklistUseCaseFactory {
    private ChecklistUseCaseFactory() {}
    public static ChecklistView create(SwitchViewController switchViewController, ChecklistViewModel checklistViewModel,
                                       TableViewModel tableViewModel, MedicineDataAccessInterface medicineDAO) {
        ChecklistController checklistController = ChecklistUseCaseFactory.createChecklistUseCase(checklistViewModel, tableViewModel, medicineDAO);
        HashMap<String, Medicine> userMedicines = medicineDAO.getUserMedicines();
        ChecklistView checklistView = new ChecklistView(switchViewController, checklistController, checklistViewModel);
        ChecklistState checklistState = checklistViewModel.getState();
        for (Medicine medicine: userMedicines.values()) {
            if (medicine.getDose().getDosesRemaining() < 14) {
                String[] checklistData = new String[]{medicine.getName(), String.valueOf(medicine.getDose().getDosesRemaining())};
                checklistState.addLow(checklistData);
                checklistViewModel.firePropertyChangedAddLow(checklistData);
            }
        }
        HashMap<String, Integer> todayChecklist = medicineDAO.getToday().getTodayChecklist();
        for (String medicine:todayChecklist.keySet()) {
            for (int i = 0; i < userMedicines.get(medicine).getWeeklySchedule()[medicineDAO.getToday().getDay()] - todayChecklist.get(medicine); i++) {
                String[] checklistData = new String[]{medicine, userMedicines.get(medicine).getDoseString()};
                checklistState.addTakeToday(checklistData);
                checklistViewModel.firePropertyChangedAddTake(checklistData);
            }
        }
        return checklistView;
    }

    //TODO: finish after Checklist CA is written
    public static ChecklistController createChecklistUseCase(ChecklistViewModel checklistViewModel,
                                                             TableViewModel tableViewModel,
                                                             MedicineDataAccessInterface medicineDAO) {
        return new ChecklistController();
    }
}
