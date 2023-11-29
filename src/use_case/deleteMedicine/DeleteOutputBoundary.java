package use_case.deleteMedicine;

public interface DeleteOutputBoundary {
    /**
     * An interface to prepare data to be passed to the delete presenter.
     */

    /**
     * Prepares the view for a medicine being successfully deleted
     * Prepares the view for a medicine unsuccessfully deleted.
     * @param deleteOutputData the data to be passed.
     */
    void prepareSuccessView(DeleteOutputData deleteOutputData);

    /**
     * prepares the view when the medicine is unsuccessfully deleted.
     * @param error the message that will be passed to the popup.
     */
    void prepareFailView(String error);

}
