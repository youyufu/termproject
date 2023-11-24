package interface_adapter.switchView;

import use_case.switchView.SwitchViewInputBoundary;
import use_case.switchView.SwitchViewInputData;

public class SwitchViewController {
    /**
     * A controller for switching between current views.
     */
    private final SwitchViewInputBoundary switchViewInteractor;

    /**
     * Creates a SwitchViewController.
     * @param switchViewInteractor, the input boundary responsible for processing which view to switch to using input
     *                              data from the controller.
     */
    public SwitchViewController(SwitchViewInputBoundary switchViewInteractor) {this.switchViewInteractor = switchViewInteractor;}

    /**
     * Calls an input boundary to switch the current view to the viewName.
     * @param viewName, the name of the view to switch to
     */
    public void execute(String viewName) {
        SwitchViewInputData switchViewInputData = new SwitchViewInputData(viewName);
        switchViewInteractor.execute(switchViewInputData);
    }
}
