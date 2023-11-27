package use_case.switchView;

public interface SwitchViewInputBoundary {
    /**
     * An interface for handling input data relating to the SwitchViewUseCase.
     */

    /**
     * Uses the input data to make calls to an output boundary to update the current view.
     * @param switchViewInputData, the name of the view to switch to.
     */
    void execute(SwitchViewInputData switchViewInputData);
}
