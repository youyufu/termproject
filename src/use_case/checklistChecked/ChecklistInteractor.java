package use_case.checklistChecked;

import data_access.MedicineDataAccessInterface;
import entity.Medicine;

public class ChecklistInteractor implements  ChecklistInputBoundary{
    private final ChecklistOutputBoundary checklistOutputBoundary;
    private final MedicineDataAccessInterface medicineDataAccessInterface;
    public ChecklistInteractor (ChecklistOutputBoundary checklistOutputBoundary, MedicineDataAccessInterface medicineDataAccessInterface) {
        this.checklistOutputBoundary = checklistOutputBoundary;
        this.medicineDataAccessInterface = medicineDataAccessInterface;
    }
    @Override
    public void execute(ChecklistInputData checklistInputData) {
        String name = checklistInputData.getName();
        Integer change = checklistInputData.getChange();
        Medicine medicine = medicineDataAccessInterface.getUserMedicines().get(name);
        if (change == 1) {
            medicineDataAccessInterface.undoTakeMedicine(name);
            String inventory = medicine.getInventoryString();
            String dosesLeft = String.valueOf(medicine.getDose().getDosesRemaining());
            ChecklistOutputData checklistOutputData = new ChecklistOutputData(name, inventory, dosesLeft);
            checklistOutputBoundary.untake(checklistOutputData);
        } else if (change == -1) {
            medicineDataAccessInterface.takeMedicine(name);
            String inventory = medicine.getInventoryString();
            String dosesLeft = String.valueOf(medicine.getDose().getDosesRemaining());
            ChecklistOutputData checklistOutputData = new ChecklistOutputData(name, inventory, dosesLeft);
            checklistOutputBoundary.take(checklistOutputData);
        }
    }
}
