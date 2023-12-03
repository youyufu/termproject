package view;

import interface_adapter.switchView.SwitchViewController;
import org.junit.jupiter.api.Test;
import use_case.switchView.SwitchViewInputBoundary;
import use_case.switchView.SwitchViewInputData;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.*;

class MainViewTest {

    @Test
    void testToEnter() {
        SwitchViewInputBoundary switchViewInputBoundary = new SwitchViewInputBoundary() {
            @Override
            public void execute(SwitchViewInputData switchViewInputData) {
                assertEquals(EnterView.viewName, switchViewInputData.getViewName());
            }
        };
        SwitchViewController switchViewController = new SwitchViewController(switchViewInputBoundary);
        JPanel mainView = new MainView(switchViewController);
        JPanel buttons = (JPanel) mainView.getComponent(1);
        JButton enter = (JButton) buttons.getComponent(0);
        enter.doClick();
    }
    @Test
    void testToDelete() {
        SwitchViewInputBoundary switchViewInputBoundary = new SwitchViewInputBoundary() {
            @Override
            public void execute(SwitchViewInputData switchViewInputData) {
                assertEquals(DeleteView.viewName, switchViewInputData.getViewName());
            }
        };
        SwitchViewController switchViewController = new SwitchViewController(switchViewInputBoundary);
        JPanel mainView = new MainView(switchViewController);
        JPanel buttons = (JPanel) mainView.getComponent(1);
        JButton delete = (JButton) buttons.getComponent(1);
        delete.doClick();
    }
    @Test
    void testToTable() {
        SwitchViewInputBoundary switchViewInputBoundary = new SwitchViewInputBoundary() {
            @Override
            public void execute(SwitchViewInputData switchViewInputData) {
                assertEquals(TableView.viewName, switchViewInputData.getViewName());
            }
        };
        SwitchViewController switchViewController = new SwitchViewController(switchViewInputBoundary);
        JPanel mainView = new MainView(switchViewController);
        JPanel buttons = (JPanel) mainView.getComponent(1);
        JButton table = (JButton) buttons.getComponent(2);
        table.doClick();
    }
    @Test
    void testToChecklist() {
        SwitchViewInputBoundary switchViewInputBoundary = new SwitchViewInputBoundary() {
            @Override
            public void execute(SwitchViewInputData switchViewInputData) {
                assertEquals(ChecklistView.viewName, switchViewInputData.getViewName());
            }
        };
        SwitchViewController switchViewController = new SwitchViewController(switchViewInputBoundary);
        JPanel mainView = new MainView(switchViewController);
        JPanel buttons = (JPanel) mainView.getComponent(1);
        JButton checklist = (JButton) buttons.getComponent(3);
        checklist.doClick();
    }
}