package view;

import interface_adapter.checklistChecked.ChecklistController;
import interface_adapter.checklistChecked.ChecklistViewModel;
import interface_adapter.switchView.SwitchViewController;
import org.junit.jupiter.api.Test;
import use_case.checklistChecked.ChecklistInputBoundary;
import use_case.checklistChecked.ChecklistInputData;
import use_case.switchView.SwitchViewInputBoundary;
import use_case.switchView.SwitchViewInputData;

import javax.swing.*;

import static java.lang.Thread.sleep;
import static org.junit.jupiter.api.Assertions.*;

class ChecklistViewTest {

    @Test
    void testChecklistView() {
        SwitchViewInputBoundary switchViewInputBoundary = new SwitchViewInputBoundary() {
            @Override
            public void execute(SwitchViewInputData switchViewInputData) {
                assertEquals(MainView.viewName, switchViewInputData.getViewName());
            }
        };
        SwitchViewController switchViewController = new SwitchViewController(switchViewInputBoundary);
        ChecklistViewModel checklistViewModel = new ChecklistViewModel();
        ChecklistInputBoundary checklistInputBoundary = new ChecklistInputBoundary() {
            @Override
            public void execute(ChecklistInputData checklistInputData) {
                assertEquals(checklistInputData.getName(), "advil");
            }
        };
        JPanel checklistView = new ChecklistView(switchViewController, new ChecklistController(checklistInputBoundary),checklistViewModel);
        JFrame frame = new JFrame();
        frame.setContentPane(checklistView);
        frame.pack();
        frame.setVisible(true);
        try {
            sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        JPanel buttons = (JPanel) checklistView.getComponent(4);
        JButton back = (JButton) buttons.getComponent(0);
        back.doClick();
        try {
            sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        checklistViewModel.firePropertyChangedAddTake(new String[]{"advil", "2 mg"});
        JPanel checklist = (JPanel) checklistView.getComponent(1);
        JCheckBox checkBox = (JCheckBox) checklist.getComponent(0);
        assertEquals("Take 2 mg of advil", checkBox.getText());

        checkBox.setSelected(true);
        try {
            sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        checkBox.setSelected(false);
        try {
            sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        checklistViewModel.firePropertyChangedAddLow(new String[]{"advil", "1"});
        JPanel lowStock = (JPanel) checklistView.getComponent(3);
        JLabel label = (JLabel) lowStock.getComponent(0);
        assertEquals("advil (1 doses remaining)", label.getText());
        try {
            sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        checklistViewModel.firePropertyChangedUpdateLow(new String[]{"advil", "0"});
        checklistViewModel.addRestock("advil");
        checklistViewModel.firePropertyChanged();
        assertFalse(checklistViewModel.getRestock().isEmpty());
        assertEquals("advil (0 doses remaining)", label.getText());
        try {
            sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        checklistViewModel.firePropertyChangedRemoveMed("advil");
        checklistViewModel.removeRestock("advil");
        assertTrue(checklistViewModel.getRestock().isEmpty());
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class, () -> {checklist.getComponent(0);});
        String expected = "No such child:";
        String actual = exception.getMessage();
        assertTrue(actual.contains(expected));
        Exception exception1 = assertThrows(ArrayIndexOutOfBoundsException.class, () -> {lowStock.getComponent(0);});
        String expected1 = "No such child:";
        String actual1 = exception1.getMessage();
        assertTrue(actual1.contains(expected1));
    }
}