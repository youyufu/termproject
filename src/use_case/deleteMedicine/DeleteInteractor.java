package use_case.deleteMedicine;

import data_access.MedicineDataAccessInterface;
import entity.MedicineFactory;


public class DeleteInteractor implements DeleteInputBoundary{

    final MedicineDataAccessInterface medicineDataAccessObject;
    final DeleteOutputBoundary deletePresenter;

    final MedicineFactory medicineFactory;

    public DeleteInteractor(MedicineDataAccessInterface medicineDataAccessObject, DeleteOutputBoundary deletePresenter,
    MedicineFactory medicineFactory) {
        this.medicineDataAccessObject = medicineDataAccessObject;
        this.deletePresenter = deletePresenter;
        this.medicineFactory = medicineFactory;
    }
    @Override
    public void execute(DeleteInputData input) {
        String name = input.getMedicineName();
        if (!medicineDataAccessObject.exists(name)) {
        DeleteOutputData deleteOutputData = new DeleteOutputData(input.getMedicineName());
        deletePresenter.prepareSuccessView(deleteOutputData);
        deletePresenter.updateChecklistState(deleteOutputData);
    }
        else{ medicineDataAccessObject.removeMedicine(name);
            DeleteOutputData deleteOutputData = new DeleteOutputData(name);
            deletePresenter.prepareSuccessView(deleteOutputData);
            deletePresenter.updateChecklistState(deleteOutputData);}
            }
}
