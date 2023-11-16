package interface_adapter.switchView;

import interface_adapter.ViewManagerModel;
import interface_adapter.checklistChecked.ChecklistViewModel;
import use_case.switchView.SwitchViewOutputBoundary;
import use_case.switchView.SwitchViewOutputData;

public class SwitchViewPresenter implements SwitchViewOutputBoundary {
    private final ViewManagerModel viewManagerModel;
    private final ChecklistViewModel checklistViewModel;
    public SwitchViewPresenter(ViewManagerModel viewManagerModel, ChecklistViewModel checklistViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.checklistViewModel = checklistViewModel;
    }
    @Override
    public void switchViewTo(SwitchViewOutputData viewName) {
        viewManagerModel.setActiveView(viewName.getViewName());
        viewManagerModel.firePropertyChanged();
        if (viewName.getViewName().equals("checklist")) {
            checklistViewModel.firePropertyChanged();
        }
    }
}
