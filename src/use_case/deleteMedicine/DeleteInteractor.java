package use_case.deleteMedicine;

import data_access.MedicineDataAccessInterface;
import use_case.enterMedicine.EnterOutputBoundary;

public class DeleteInteractor implements DeleteInputBoundary{

    final MedicineDataAccessInterface medicineDataAccessObject;
    final DeleteOutputBoundary deletePresenter;

    public DeleteInteractor(MedicineDataAccessInterface medicineDataAccessObject, DeleteOutputBoundary deletePresenter) {
        this.medicineDataAccessObject = medicineDataAccessObject;
        this.deletePresenter = deletePresenter;
    }
    @Override
    public void execute(DeleteInputData input) {
        DeleteOutputData deleteOutputData = new DeleteOutputData(input.getMedicineName());
        deletePresenter.prepareSuccessView(deleteOutputData);
    }
}
