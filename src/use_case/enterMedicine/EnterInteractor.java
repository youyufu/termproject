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
            enterPresenter.prepareFailView(name + "already exists as a medication");
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
                    enterInputData.getDoseSize().toString(),
                    enterInputData.getInventory().toString(),
                    enterInputData.getDay()[0],
                    enterInputData.getDay()[1],
                    enterInputData.getDay()[2],
                    enterInputData.getDay()[3],
                    enterInputData.getDay()[4],
                    enterInputData.getDay()[5],
                    enterInputData.getDay()[6],
                    enterInputData.getDescription());
            enterPresenter.prepareSuccessView(enterOutputData);
        }
    }
}
