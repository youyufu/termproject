package interface_adapter.enterMedicine;

import use_case.enterMedicine.EnterInputBoundary;
import use_case.enterMedicine.EnterInputData;

public class EnterController {
    /**
     * A controller for entering a new medicine.
     */
    final EnterInputBoundary enterInputBoundary;

    /**
     * Creates an EnterController.
     * @param enterInputBoundary1 the input boundary responsible for passing the medicine's data to the interactor.
     */
    public EnterController(EnterInputBoundary enterInputBoundary1) {
        this.enterInputBoundary = enterInputBoundary1;
    }

    /**
     * Calls the input boundary to pass the needed data.
     * @param medicineName the name of the medicine
     * @param doseSize the size of each dose
     * @param doseUnit the unit of each dose
     * @param doseInventory the amount of medicine in inventory
     * @param day the number of doses needed each day
     * @param description a description for the medicine
     */
    public void execute(String medicineName, Integer doseSize, String doseUnit, Integer doseInventory,
            Integer[] day, String description)
        {EnterInputData enterInputData = new EnterInputData(medicineName, doseSize, doseUnit,
            doseInventory, day, description);

        enterInputBoundary.execute(enterInputData);}
}
