package use_case.deleteMedicine;

import data_access.MedicineDataAccessInterface;


public class DeleteInteractor implements DeleteInputBoundary {
    final MedicineDataAccessInterface medicineDataAccessObject;
    final DeleteOutputBoundary deletePresenter;
    public DeleteInteractor(MedicineDataAccessInterface medicineDataAccessObject, DeleteOutputBoundary deletePresenter) {
        this.medicineDataAccessObject = medicineDataAccessObject;
        this.deletePresenter = deletePresenter;
    }
    @Override
    public void execute(DeleteInputData input) {
        String name = input.getMedicineName();
        if (medicineDataAccessObject.exists(name)) {
            medicineDataAccessObject.removeMedicine(name);
            DeleteOutputData deleteOutputData = new DeleteOutputData(name);
            deletePresenter.prepareSuccessView(deleteOutputData);
        }
        else {
            deletePresenter.prepareFailView(name + " does not exist.");
        }
    }
}