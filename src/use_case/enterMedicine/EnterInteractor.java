package use_case.enterMedicine;

import data_access.MedicineAPICallsInterface;
import data_access.MedicineDataAccessInterface;
import entity.Medicine;
import entity.MedicineFactory;

import java.io.IOException;
import java.util.ArrayList;

public class EnterInteractor implements EnterInputBoundary {
    /**
     * An interactor implementing the logic for deciding whether the entered medicine gets created.
     */
    private final MedicineDataAccessInterface medicineDataAccessObject;
    private final EnterOutputBoundary enterPresenter;
    private final MedicineFactory medicineFactory;
    private final MedicineAPICallsInterface medicineAPICallsObject;
    /**
     * A default id to be assigned to medicines for which a RxCUI cannot be found.
     */
    public static final String DEFAULT_ID = "";

    /**
     * Creates an EnterInteractor
     * @param medicineDataAccessObject the interface to get existing medicines and to store the newly created medicine.
     * @param enterPresenter the interface responsible for preparing the presenter with the needed information.
     * @param medicineFactory the class responsible for creating the medicine and its associated dose object.
     * @param medicineAPICallsObject the class responsible for the API calls.
     */
    public EnterInteractor(MedicineDataAccessInterface medicineDataAccessObject,
                           EnterOutputBoundary enterPresenter,
                           MedicineFactory medicineFactory,
                           MedicineAPICallsInterface medicineAPICallsObject) {
        this.medicineDataAccessObject = medicineDataAccessObject;
        this.enterPresenter = enterPresenter;
        this.medicineFactory = medicineFactory;
        this.medicineAPICallsObject = medicineAPICallsObject;
    }

    /**
     * Checks whether the entered medicine should be created or not. Creates the medicine and updates the relevent information
     * if the medicine fulfills the requirements or calls on the presenter to create a popup if something fails.
     * @param enterInputData data related to the medicine to be created.
     */
    @Override
    public void execute(EnterInputData enterInputData) {
        String name = enterInputData.getMedicine();
        if (medicineDataAccessObject.exists(name)) {
            enterPresenter.preparePopUp(name + " already exists as a medication");
        }
        else {
            String id = medicineAPICallsObject.findId(name);
            if (!id.equals(DEFAULT_ID)) {
                try {
                    ArrayList<String> warnings = medicineAPICallsObject.findDrugInteractions(medicineDataAccessObject, id);
                    if (!warnings.isEmpty()) {
                        StringBuilder popUpMessage = new StringBuilder();
                        for (String description:warnings) {
                            popUpMessage.append("Warning - drug interaction detected: ").append(description).append("\n");
                        }
                        enterPresenter.preparePopUp(popUpMessage.toString());
                    }
                } catch (IOException | InterruptedException e) {
                    enterPresenter.preparePopUp("Error retrieving data about the medication. Please try again later.");
                    return;
                }
            } else {
                enterPresenter.preparePopUp(name + " could not be found in the database. The drug will still be entered but drug interactions with " + name + " cannot be confirmed.");
            }
            if (enterInputData.getDoseSize() == 0) {
                enterPresenter.preparePopUp("Cannot enter medicine with a dose size of 0.");
            } else {
                Medicine medicine = medicineFactory.createMedicine(enterInputData.getMedicine(),
                        enterInputData.getDoseSize(),
                        enterInputData.getInventory(),
                        enterInputData.getUnit(),
                        enterInputData.getDay(),
                        enterInputData.getDescription(), id);
                if (medicine.getDose().getDosesRemaining() == 0) {
                    enterPresenter.preparePopUp("Cannot enter medicine with 0 doses remaining.");
                } else {
                    medicineDataAccessObject.saveMedicine(medicine);
                    EnterOutputData enterOutputData = new EnterOutputData(enterInputData.getMedicine(), medicine.getDoseString(),
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
                    Integer today = medicineDataAccessObject.getTodayDay();
                    for (int i = 0; i < Math.min(medicine.getWeeklySchedule()[today], medicine.getDose().getDosesRemaining()); i++) {
                        enterPresenter.updateChecklistView(enterOutputData);
                    }
                    if (medicine.getDose().getDosesRemaining() < 14) {
                        enterPresenter.updateLowView(enterOutputData);
                    }
                }
            }
        }
    }
}
