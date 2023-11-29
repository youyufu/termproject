package interface_adapter.switchView;

import org.junit.jupiter.api.Test;
import use_case.switchView.SwitchViewInputBoundary;
import use_case.switchView.SwitchViewInputData;

import static org.junit.jupiter.api.Assertions.*;

class SwitchViewControllerTest {

    @Test
    void execute() {
        SwitchViewController switchViewController = new SwitchViewController(new SwitchViewInputBoundary() {
            @Override
            public void execute(SwitchViewInputData switchViewInputData) {
                assertEquals("main", switchViewInputData.getViewName());
            }
        });
        switchViewController.execute("main");
    }
}