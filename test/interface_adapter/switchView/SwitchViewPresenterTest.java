package interface_adapter.switchView;

import interface_adapter.ViewManagerModel;
import interface_adapter.checklistChecked.ChecklistViewModel;
import org.junit.jupiter.api.Test;
import use_case.switchView.SwitchViewOutputData;
import view.ChecklistView;


import static org.junit.jupiter.api.Assertions.*;

class SwitchViewPresenterTest {

    @Test
    void switchViewTo() {

        ViewManagerModel viewManagerModel = new ViewManagerModel();
        ChecklistViewModel checklistViewModel = new ChecklistViewModel();
        SwitchViewPresenter switchViewPresenter = new SwitchViewPresenter(viewManagerModel, checklistViewModel);
        SwitchViewOutputData switchViewOutputData = new SwitchViewOutputData("checklist");
        switchViewPresenter.switchViewTo(switchViewOutputData);
        assertEquals(viewManagerModel.getActiveView(), ChecklistView.viewName);
    }
}