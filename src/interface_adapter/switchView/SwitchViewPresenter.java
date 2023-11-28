package interface_adapter.switchView;

import interface_adapter.ViewManagerModel;
import interface_adapter.checklistChecked.ChecklistViewModel;
import use_case.switchView.SwitchViewOutputBoundary;
import use_case.switchView.SwitchViewOutputData;
import view.ChecklistView;

public class SwitchViewPresenter implements SwitchViewOutputBoundary {
    /**
     * An presenter to handle output data relating to the SwitchViewUseCase.
     */
    private final ViewManagerModel viewManagerModel;
    private final ChecklistViewModel checklistViewModel;

    /**
     * Creates a SwitchViewPresenter
     * @param viewManagerModel, the view manager model used to switch the current view in the view manager.
     * @param checklistViewModel the checklist view model used to raise a JOptionPane in the case when the view
     *                           is switched to the checklist.
     */
    public SwitchViewPresenter(ViewManagerModel viewManagerModel, ChecklistViewModel checklistViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.checklistViewModel = checklistViewModel;
    }

    /**
     * Updates the view models to display the correct current view model accordingly.
     * @param viewName, the name of the view to switch to.
     */
    @Override
    public void switchViewTo(SwitchViewOutputData viewName) {
        viewManagerModel.setActiveView(viewName.getViewName());
        viewManagerModel.firePropertyChanged();
        if (viewName.getViewName().equals(ChecklistView.viewName)) {
            checklistViewModel.firePropertyChanged();
        }
    }
}
