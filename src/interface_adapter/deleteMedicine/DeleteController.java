package interface_adapter.deleteMedicine;

import use_case.deleteMedicine.DeleteInputBoundary;
import use_case.deleteMedicine.DeleteInputData;

public class DeleteController {
    /**
     * A controller for deleting a medicine from the user's account.
     */
    final DeleteInputBoundary deleteInputBoundary;

    /**
     * Creates the Delete Controller object.
     * @param deleteInputBoundary is the boundary responsible for sending the medicine required to be deleted.
     *                           
     */
    public DeleteController(DeleteInputBoundary deleteInputBoundary){this.deleteInputBoundary = deleteInputBoundary;}

    /**
     * Executes the delete operation based on the given medicine name.
     * @param medicine The medicine name.
     */
    public void execute(String medicine) {

        DeleteInputData deleteInputData = new DeleteInputData(medicine);

        deleteInputBoundary.execute(deleteInputData);
    }
}
