package use_case.switchView;

import org.junit.jupiter.api.Test;
import view.MainView;

import static org.junit.jupiter.api.Assertions.*;

class SwitchViewInteractorTest {

    @Test
    void execute() {
        SwitchViewInputData switchViewInputData = new SwitchViewInputData(MainView.viewName);
        SwitchViewOutputBoundary switchViewOutputBoundary = new SwitchViewOutputBoundary() {
            @Override
            public void switchViewTo(SwitchViewOutputData viewName) {
                assertEquals(viewName.getViewName(), MainView.viewName);
            }
        };
        SwitchViewInputBoundary switchViewInputBoundary = new SwitchViewInteractor(switchViewOutputBoundary);
        switchViewInputBoundary.execute(switchViewInputData);
    }
}