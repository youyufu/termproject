package use_case.deleteMedicine;

public interface DeleteInputBoundary {
    /**
     * An interface for passing the data to the delete interactor.
     */

    /**
     * Passes the data the user entered to the delete interactor.
     * @param deleteInputData the data to be passed.
     */
    void execute(DeleteInputData deleteInputData);
}
