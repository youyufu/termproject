package interface_adapter.deleteMedicine;

import use_case.deleteMedicine.DeleteInputBoundary;
import use_case.deleteMedicine.DeleteInputData;

public class DeleteController {

    final DeleteInputBoundary deleteInputBoundary;

    public DeleteController(DeleteInputBoundary deleteInputBoundary1){this.deleteInputBoundary = deleteInputBoundary1;}
    public void execute(String medicine) {

        DeleteInputData deleteInputData = new DeleteInputData(medicine);

        deleteInputBoundary.execute(deleteInputData);
    }
}
