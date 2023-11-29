package use_case.enterMedicine;

public interface EnterInputBoundary {
    /**
     * An interface for passing the data to the interactor.
     */

    /**
     * Passes the data the user entered to the interactor.
     * @param enterInputData the data to be passed.
     */
    void execute(EnterInputData enterInputData);
}
