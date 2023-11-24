package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.checklistChecked.ChecklistViewModel;
import interface_adapter.switchView.SwitchViewController;
import interface_adapter.switchView.SwitchViewPresenter;
import use_case.switchView.SwitchViewInputBoundary;
import use_case.switchView.SwitchViewInteractor;
import use_case.switchView.SwitchViewOutputBoundary;

public class SwitchViewUseCaseFactory {
    /**
     * Generates the SwitchViewUseCase.
     */
    private SwitchViewUseCaseFactory() {}

    /**
     * Creates and integrates all modules of the SwitchViewUseCase using necessary view models.
     * @param viewManagerModel, the view manager model used to switch the current view in the view manager.
     * @param checklistViewModel, the checklist view model used to raise a JOptionPane in the case when the view
     *                            is switched to the checklist.
     * @return a controller for switching between current views.
     */
    public static SwitchViewController create(ViewManagerModel viewManagerModel, ChecklistViewModel checklistViewModel) {
        SwitchViewOutputBoundary switchViewPresenter = new SwitchViewPresenter(viewManagerModel, checklistViewModel);
        SwitchViewInputBoundary switchViewInteractor = new SwitchViewInteractor(switchViewPresenter);
        return new SwitchViewController(switchViewInteractor);
    }
}
