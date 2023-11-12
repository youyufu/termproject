package use_case.enterMedicine;

public interface EnterOutputBoundary {
    void prepareSuccessView(EnterOutputData enterOutputData);

    void prepareFailView(String error);
}
