package use_case.enterMedicine;

public interface EnterInputBoundary {
    /**
     * An interface for passing the data the user entered to the interactor.
     * @param enterInputData
     */
    void execute(EnterInputData enterInputData);
}
