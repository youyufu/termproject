package interface_adapter.enterMedicine;

import use_case.enterMedicine.EnterInputData;

public class EnterController {

    final EnterInputBoundary enterInputBoundary;

    public EnterController(EnterInputBoundary enterInputBoundary1) {
        this.enterInputBoundary = enterInputBoundary1;
    }
    public void execute(
            String medicineName,
            String doseSize,
            String doseUnit,
            String doseInventory,
            String sunday,
            String monday,
            String tuesday,
            String wednesday,
            String thursday,
            String friday,
            String saturday,
            String description
    ) {EnterInputData enterInputData = new EnterInputData(medicineName, doseSize, doseUnit,
            doseInventory, sunday, monday, tuesday, wednesday, thursday, friday, saturday, description);

        enterInputBoundary.execute(enterInputData);}
}
