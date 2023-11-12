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
