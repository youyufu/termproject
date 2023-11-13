package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.switchView.SwitchViewController;
import interface_adapter.switchView.SwitchViewPresenter;
import use_case.switchView.SwitchViewInputBoundary;
import use_case.switchView.SwitchViewInteractor;
import use_case.switchView.SwitchViewOutputBoundary;

public class SwitchViewUseCaseFactory {
    private SwitchViewUseCaseFactory() {}
    public static SwitchViewController create(ViewManagerModel viewManagerModel) {
        SwitchViewOutputBoundary switchViewPresenter = new SwitchViewPresenter(viewManagerModel);
        SwitchViewInputBoundary switchViewInteractor = new SwitchViewInteractor(switchViewPresenter);
        return new SwitchViewController(switchViewInteractor);
    }
}
