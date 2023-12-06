package view;

import interface_adapter.deleteMedicine.DeleteController;
import interface_adapter.deleteMedicine.DeleteState;
import interface_adapter.deleteMedicine.DeleteViewModel;
import interface_adapter.switchView.SwitchViewController;
import org.junit.jupiter.api.Test;
import use_case.deleteMedicine.DeleteInputBoundary;
import use_case.deleteMedicine.DeleteInputData;
import use_case.switchView.SwitchViewInputBoundary;
import use_case.switchView.SwitchViewInputData;

import javax.swing.*;

import java.awt.event.KeyEvent;

import static java.lang.Thread.sleep;
import static org.junit.jupiter.api.Assertions.*;

class DeleteViewTest {

    @Test
    void testDeleteView() {
        DeleteInputBoundary deleteInputBoundary = new DeleteInputBoundary() {
            @Override
            public void execute(DeleteInputData deleteInputData) {
                assertEquals("advil", deleteInputData.getMedicineName());
            }
        };
        DeleteController deleteController = new DeleteController(deleteInputBoundary);
        DeleteViewModel deleteViewModel = new DeleteViewModel();
        SwitchViewInputBoundary switchViewInputBoundary = new SwitchViewInputBoundary() {
            @Override
            public void execute(SwitchViewInputData switchViewInputData) {
                assertEquals(MainView.viewName, switchViewInputData.getViewName());
            }
        };
        SwitchViewController switchViewController = new SwitchViewController(switchViewInputBoundary);
        JPanel deleteView = new DeleteView(switchViewController, deleteController, deleteViewModel);
        JFrame frame = new JFrame();
        frame.setContentPane(deleteView);
        frame.pack();
        frame.setVisible(true);

        LabelTextPanel medName = (LabelTextPanel) deleteView.getComponent(1);
        JTextField nameField = (JTextField) medName.getComponent(1);
        KeyEvent event = new KeyEvent(nameField, KeyEvent.KEY_TYPED, System.currentTimeMillis(),
                0, KeyEvent.VK_UNDEFINED, 'a');
        medName.dispatchEvent(event);
        try {
            sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        KeyEvent eventRight = new KeyEvent(nameField, KeyEvent.KEY_PRESSED, System.currentTimeMillis(),
                0, KeyEvent.VK_RIGHT, KeyEvent.CHAR_UNDEFINED);
        medName.dispatchEvent(eventRight);
        try {
            sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        KeyEvent event1 = new KeyEvent(nameField, KeyEvent.KEY_TYPED, System.currentTimeMillis(),
                0, KeyEvent.VK_UNDEFINED, 'd');
        medName.dispatchEvent(event1);
        try {
            sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        KeyEvent eventRight1 = new KeyEvent(nameField, KeyEvent.KEY_PRESSED, System.currentTimeMillis(),
                0, KeyEvent.VK_RIGHT, KeyEvent.CHAR_UNDEFINED);
        medName.dispatchEvent(eventRight1);
        try {
            sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        KeyEvent event2 = new KeyEvent(nameField, KeyEvent.KEY_TYPED, System.currentTimeMillis(),
                0, KeyEvent.VK_UNDEFINED, 'v');
        medName.dispatchEvent(event2);
        try {
            sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        KeyEvent eventRight2 = new KeyEvent(nameField, KeyEvent.KEY_PRESSED, System.currentTimeMillis(),
                0, KeyEvent.VK_RIGHT, KeyEvent.CHAR_UNDEFINED);
        medName.dispatchEvent(eventRight2);
        try {
            sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        KeyEvent event3 = new KeyEvent(nameField, KeyEvent.KEY_TYPED, System.currentTimeMillis(),
                0, KeyEvent.VK_UNDEFINED, 'i');
        medName.dispatchEvent(event3);
        try {
            sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        KeyEvent eventRight3 = new KeyEvent(nameField, KeyEvent.KEY_PRESSED, System.currentTimeMillis(),
                0, KeyEvent.VK_RIGHT, KeyEvent.CHAR_UNDEFINED);
        medName.dispatchEvent(eventRight3);
        try {
            sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        KeyEvent event4 = new KeyEvent(nameField, KeyEvent.KEY_TYPED, System.currentTimeMillis(),
                0, KeyEvent.VK_UNDEFINED, 'l');
        medName.dispatchEvent(event4);

        JPanel buttons = (JPanel) deleteView.getComponent(2);
        JButton back = (JButton) buttons.getComponent(0);
        JButton delete = (JButton) buttons.getComponent(1);
        back.doClick();
        delete.doClick();

        DeleteState state = deleteViewModel.getState();
        assertEquals("advil", nameField.getText());
        assertEquals("advil", state.getMedicineName());

        try {
            sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        deleteViewModel.setState(new DeleteState());
        deleteViewModel.firePropertyChanged();
        state = deleteViewModel.getState();
        assertEquals("", nameField.getText());
        assertEquals("", state.getMedicineName());
    }
}