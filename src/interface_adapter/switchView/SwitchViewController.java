package interface_adapter.switchView;

import use_case.switchView.SwitchViewInputBoundary;
import use_case.switchView.SwitchViewInputData;

public class SwitchViewController {
    private SwitchViewInputBoundary switchViewInteractor;
    public SwitchViewController(SwitchViewInputBoundary switchViewInteractor) {this.switchViewInteractor = switchViewInteractor;}
    public void execute(String viewName) {
        SwitchViewInputData switchViewInputData = new SwitchViewInputData(viewName);
        switchViewInteractor.execute(switchViewInputData);
    }
}
