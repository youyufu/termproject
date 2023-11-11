package use_case.enterMedicine;

import data_access.MedicineDataAccessInterface;

public class EnterInteractor implements EnterInputBoundary {
    final MedicineDataAccessInterface medicineDataAccessObject;
    final EnterOutputBoundary enterPresenter;

    public EnterInteractor(MedicineDataAccessInterface medicineDataAccessObject, EnterOutputBoundary enterPresenter) {
        this.medicineDataAccessObject = medicineDataAccessObject;
        this.enterPresenter = enterPresenter;
    }

    @Override
    public void execute(EnterInputData enterInputData) {
        EnterOutputData enterOutputData = new EnterOutputData(enterInputData.getMedicine());
        enterPresenter.prepareView(enterOutputData);
    }
}
