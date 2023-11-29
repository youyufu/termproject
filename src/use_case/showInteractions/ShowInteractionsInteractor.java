package use_case.showInteractions;

import data_access.MedicineAPICallsInterface;
import data_access.MedicineDataAccessInterface;
import use_case.enterMedicine.EnterOutputBoundary;

import java.io.IOException;
import java.util.ArrayList;

public class ShowInteractionsInteractor implements ShowInteractionsInputBoundary {
    /**
     * An interactor implementing the logic of showing existing drug interactions.
     */
    private final MedicineDataAccessInterface medicineDataAccessInterface;
    private final ShowInteractionsOutputBoundary showInteractionsOutputBoundary;
    private final MedicineAPICallsInterface medicineAPICallsInterface;

    /**
     * Creates a ShowInteractionsInteractor.
     * @param medicineDataAccessInterface the interface to get existing medicines.
     * @param showInteractionsOutputBoundary the interface responsible for preparing the presenter with the needed information.
     * @param medicineAPICallsInterface the class responsible for the API calls.
     */
    public ShowInteractionsInteractor(MedicineDataAccessInterface medicineDataAccessInterface,
                                      ShowInteractionsOutputBoundary showInteractionsOutputBoundary,
                                      MedicineAPICallsInterface medicineAPICallsInterface) {
        this.medicineDataAccessInterface = medicineDataAccessInterface;
        this.showInteractionsOutputBoundary = showInteractionsOutputBoundary;
        this.medicineAPICallsInterface = medicineAPICallsInterface;
    }

    /**
     * get the existing drug interactions and pass it to the output boundary.
     */
    @Override
    public void execute() {
        try {
            ArrayList<String> warnings = medicineAPICallsInterface.findAllInteractions(medicineDataAccessInterface);
            if (warnings.isEmpty()) {showInteractionsOutputBoundary.preparePopUp("No drug interaction found between the existing medicines.\n" +
                    " Please note that some medicine might not have been found in the database.");}
            else {
                StringBuilder popUpMessage = new StringBuilder();
                for (String description:warnings) {
                    popUpMessage.append("Warning - drug interaction detected: ").append(description).append("\n");
                }
                showInteractionsOutputBoundary.preparePopUp(popUpMessage.toString());
            }
        } catch (IOException | InterruptedException e) {
            showInteractionsOutputBoundary.preparePopUp("Error retrieving data about the medication. Please try again later.");
        }
    }
}
