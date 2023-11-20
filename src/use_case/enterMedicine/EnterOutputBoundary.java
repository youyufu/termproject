package use_case.enterMedicine;

public interface EnterOutputBoundary {
    void prepareSuccessView(EnterOutputData enterOutputData);

    void preparePopUp(String message);
    void updateChecklistView(EnterOutputData enterOutputData);
    void updateLowView(EnterOutputData enterOutputData);
}
