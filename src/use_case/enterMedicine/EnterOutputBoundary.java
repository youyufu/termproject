package use_case.enterMedicine;

public interface EnterOutputBoundary {
    void prepareSuccessView(EnterOutputData enterOutputData);

    void prepareFailView(String error);
    void updateChecklistView(EnterOutputData enterOutputData);
    void updateLowView(EnterOutputData enterOutputData);
}
