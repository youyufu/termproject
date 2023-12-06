package use_case.deleteMedicine;

import data_access.MedicineDataAccessInterface;


public class DeleteInteractor implements DeleteInputBoundary {
    /**
     * An interactor implementing the logic for deciding whether the medicine gets deleted.
     */
    final MedicineDataAccessInterface medicineDataAccessObject;
    final DeleteOutputBoundary deletePresenter;

    /**
     * A constructor for the DeleteInteractor
     * @param medicineDataAccessObject the interface to get existing medicines and to store the newly created medicine.
     * @param deletePresenter The interface responsible for preparing the presenter with the needed information.
     */
    public DeleteInteractor(MedicineDataAccessInterface medicineDataAccessObject, DeleteOutputBoundary deletePresenter) {
        this.medicineDataAccessObject = medicineDataAccessObject;
        this.deletePresenter = deletePresenter;
    }

    /**
     * Checks whether the entered medicine should be deleted or not. Deletes the medicine and updates
     * the relevant information if the medicine deletion fulfills the requirements or calls on the presenter
     * to create a popup if something fails.
     * @param input the medicine data to be passed.
     */
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