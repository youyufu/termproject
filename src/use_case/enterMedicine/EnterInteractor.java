package use_case.enterMedicine;

import data_access.MedicineDataAccessInterface;
import entity.Medicine;
import entity.Dose;
import entity.MedicineFactory;

public class EnterInteractor implements EnterInputBoundary {
    final MedicineDataAccessInterface medicineDataAccessObject;
    final EnterOutputBoundary enterPresenter;
    final MedicineFactory medicineFactory;

    public EnterInteractor(MedicineDataAccessInterface medicineDataAccessObject,
                           EnterOutputBoundary enterPresenter,
                           MedicineFactory medicineFactory) {
        this.medicineDataAccessObject = medicineDataAccessObject;
        this.enterPresenter = enterPresenter;
        this.medicineFactory = medicineFactory;
    }

    @Override
    public void execute(EnterInputData enterInputData) {
        String name = enterInputData.getMedicine();
        if (medicineDataAccessObject.exists(name)) {
            enterPresenter.prepareFailView(name + " already exists as a medication");
        }
        // add the case where a drug interaction is detected
        else {
            Dose dose = medicineFactory.createDose(enterInputData.getDoseSize(),
                    enterInputData.getInventory(),
                    enterInputData.getUnit());
            Medicine medicine = medicineFactory.createMedicine(enterInputData.getMedicine(),
                    dose,
                    enterInputData.getDay(),
                    enterInputData.getDescription());
            medicineDataAccessObject.saveMedicine(medicine);
            EnterOutputData enterOutputData = new EnterOutputData(enterInputData.getMedicine(),
                    medicine.getDoseString(),
                    medicine.getInventoryString(),
                    medicine.getWeeklySchedule()[0],
                    medicine.getWeeklySchedule()[1],
                    medicine.getWeeklySchedule()[2],
                    medicine.getWeeklySchedule()[3],
                    medicine.getWeeklySchedule()[4],
                    medicine.getWeeklySchedule()[5],
                    medicine.getWeeklySchedule()[6],
                    medicine.getDescription(),
                    medicine.getDose().getDosesRemaining());
            enterPresenter.prepareSuccessView(enterOutputData);
            Integer today = medicineDataAccessObject.getToday().getDay();
            if (!medicine.getWeeklySchedule()[today].equals(0)) {
                medicineDataAccessObject.updateToday(medicine);
                for (int i = 0; i < medicine.getWeeklySchedule()[today]; i++)
                    enterPresenter.updateChecklistView(enterOutputData);
            } if (medicine.getDose().getDosesRemaining() < 14) {
                enterPresenter.updateLowView(enterOutputData);
            }

        }
    }
}
