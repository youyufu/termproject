package use_case.switchView;

public interface SwitchViewOutputBoundary {
    /**
     * An interface for handling output data relating to the SwitchViewUseCase
     */

    /**
     * Uses the output data to update the current view via the view model.
     * @param viewName, the name of the view to switch to.
     */
    void switchViewTo(SwitchViewOutputData viewName);
}
