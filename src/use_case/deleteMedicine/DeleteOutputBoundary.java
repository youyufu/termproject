package use_case.deleteMedicine;

public interface DeleteOutputBoundary {
    void prepareSuccessView(DeleteOutputData deleteOutputData);

    void prepareFailView(String error);

    void updateChecklistState(DeleteOutputData deleteOutputData);
}
