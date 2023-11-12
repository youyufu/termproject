package interface_adapter.enterMedicine;

import use_case.enterMedicine.EnterInputBoundary;
import use_case.enterMedicine.EnterInputData;

public class EnterController {

    final EnterInputBoundary enterInputBoundary;

    public EnterController(EnterInputBoundary enterInputBoundary1) {
        this.enterInputBoundary = enterInputBoundary1;
    }
    public void execute(String medicineName, String doseSize, String doseUnit, String doseInventory,
            Integer[] day, String description)
        {EnterInputData enterInputData = new EnterInputData(medicineName, doseSize, doseUnit,
            doseInventory, day, description);

        enterInputBoundary.execute(enterInputData);}
}
