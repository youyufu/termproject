package interface_adapter.switchView;

import interface_adapter.ViewManagerModel;
import use_case.switchView.SwitchViewOutputBoundary;
import use_case.switchView.SwitchViewOutputData;

public class SwitchViewPresenter implements SwitchViewOutputBoundary {
    private final ViewManagerModel viewManagerModel;
    public SwitchViewPresenter(ViewManagerModel viewManagerModel) {this.viewManagerModel = viewManagerModel;}
    @Override
    public void switchViewTo(SwitchViewOutputData viewName) {
        viewManagerModel.setActiveView(viewName.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
