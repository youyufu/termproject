package view;

import interface_adapter.enterMedicine.EnterController;
import interface_adapter.enterMedicine.EnterState;
import interface_adapter.enterMedicine.EnterViewModel;
import interface_adapter.switchView.SwitchViewController;
import org.junit.jupiter.api.Test;
import use_case.enterMedicine.EnterInputBoundary;
import use_case.enterMedicine.EnterInputData;
import use_case.switchView.SwitchViewInputBoundary;
import use_case.switchView.SwitchViewInputData;

import javax.swing.*;

import java.awt.event.KeyEvent;

import static java.lang.Thread.sleep;
import static org.junit.jupiter.api.Assertions.*;

public class EnterViewTest {
    @Test
    public void testEnterView() {
        EnterInputBoundary enterInputBoundary = new EnterInputBoundary() {
            @Override
            public void execute(EnterInputData enterInputData) {
                assertEquals(enterInputData.getMedicine(), "advil");
                assertEquals(enterInputData.getDoseSize(), 2);
                assertEquals(enterInputData.getUnit(), "mg");
                assertEquals(enterInputData.getInventory(), 20);
                assertArrayEquals(enterInputData.getDay(), new Integer[]{2, 2, 2, 2, 2, 2, 2});
                assertEquals(enterInputData.getDescription(), "");
            }
        };
        EnterController enterController = new EnterController(enterInputBoundary);
        EnterViewModel enterViewModel = new EnterViewModel();
        SwitchViewInputBoundary switchViewInputBoundary = new SwitchViewInputBoundary() {
            @Override
            public void execute(SwitchViewInputData switchViewInputData) {
                assertEquals(MainView.viewName, switchViewInputData.getViewName());
            }
        };
        SwitchViewController switchViewController = new SwitchViewController(switchViewInputBoundary);
        JPanel enterView = new EnterView(switchViewController, enterController, enterViewModel);
        JFrame frame = new JFrame();
        frame.setContentPane(enterView);
        frame.pack();
        frame.setVisible(true);

        LabelTextPanel medName = (LabelTextPanel) enterView.getComponent(1);
        JTextField nameField = (JTextField) medName.getComponent(1);
        KeyEvent event = new KeyEvent(nameField, KeyEvent.KEY_TYPED, System.currentTimeMillis(),
                0, KeyEvent.VK_UNDEFINED, 'a');
        medName.dispatchEvent(event);
        try {
            sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        KeyEvent eventRight = new KeyEvent(nameField,KeyEvent.KEY_PRESSED,System.currentTimeMillis(),
                0,KeyEvent.VK_RIGHT,KeyEvent.CHAR_UNDEFINED);
        medName.dispatchEvent(eventRight);
        try {
            sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        KeyEvent event1 = new KeyEvent(nameField,KeyEvent.KEY_TYPED,System.currentTimeMillis(),
                0,KeyEvent.VK_UNDEFINED,'d');
        medName.dispatchEvent(event1);
        try {
            sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        KeyEvent eventRight1 = new KeyEvent(nameField,KeyEvent.KEY_PRESSED,System.currentTimeMillis(),
                0,KeyEvent.VK_RIGHT,KeyEvent.CHAR_UNDEFINED);
        medName.dispatchEvent(eventRight1);
        try {
            sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        KeyEvent event2 = new KeyEvent(nameField,KeyEvent.KEY_TYPED,System.currentTimeMillis(),
                0,KeyEvent.VK_UNDEFINED,'v');
        medName.dispatchEvent(event2);
        try {
            sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        KeyEvent eventRight2 = new KeyEvent(nameField,KeyEvent.KEY_PRESSED,System.currentTimeMillis(),
                0,KeyEvent.VK_RIGHT,KeyEvent.CHAR_UNDEFINED);
        medName.dispatchEvent(eventRight2);
        try {
            sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        KeyEvent event3 = new KeyEvent(nameField,KeyEvent.KEY_TYPED,System.currentTimeMillis(),
                0,KeyEvent.VK_UNDEFINED,'i');
        medName.dispatchEvent(event3);
        try {
            sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        KeyEvent eventRight3 = new KeyEvent(nameField,KeyEvent.KEY_PRESSED,System.currentTimeMillis(),
                0,KeyEvent.VK_RIGHT,KeyEvent.CHAR_UNDEFINED);
        medName.dispatchEvent(eventRight3);
        try {
            sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        KeyEvent event4 = new KeyEvent(nameField,KeyEvent.KEY_TYPED,System.currentTimeMillis(),
                0,KeyEvent.VK_UNDEFINED,'l');
        medName.dispatchEvent(event4);

        JPanel doseSize = (JPanel) enterView.getComponent(2);
        JSpinner doseSpinner = (JSpinner) doseSize.getComponent(1);
        doseSpinner.setValue(2);
        try {
            sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        LabelTextPanel doseUnit = (LabelTextPanel) enterView.getComponent(3);
        JTextField unitField = (JTextField) doseUnit.getComponent(1);
        KeyEvent event5 = new KeyEvent(unitField,KeyEvent.KEY_TYPED,System.currentTimeMillis(),
                0,KeyEvent.VK_UNDEFINED,'m');
        doseUnit.dispatchEvent(event5);
        try {
            sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        KeyEvent eventRight4 = new KeyEvent(unitField,KeyEvent.KEY_PRESSED,System.currentTimeMillis(),
                0,KeyEvent.VK_RIGHT,KeyEvent.CHAR_UNDEFINED);
        unitField.dispatchEvent(eventRight4);
        try {
            sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        KeyEvent event6 = new KeyEvent(unitField,KeyEvent.KEY_TYPED,System.currentTimeMillis(),
                0,KeyEvent.VK_UNDEFINED,'g');
        doseUnit.dispatchEvent(event6);
        try {
            sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        JPanel doseInventory = (JPanel) enterView.getComponent(4);
        JSpinner invSpinner = (JSpinner) doseInventory.getComponent(1);
        invSpinner.setValue(20);
        try {
            sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        JPanel sunday = (JPanel) enterView.getComponent(6);
        JSpinner sun = (JSpinner) sunday.getComponent(1);
        sun.setValue(2);
        try {
            sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        JPanel monday = (JPanel) enterView.getComponent(7);
        JSpinner mon = (JSpinner) monday.getComponent(1);
        mon.setValue(2);
        try {
            sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        JPanel tuesday = (JPanel) enterView.getComponent(8);
        JSpinner tue = (JSpinner) tuesday.getComponent(1);
        tue.setValue(2);
        try {
            sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        JPanel wednesday = (JPanel) enterView.getComponent(9);
        JSpinner wed = (JSpinner) wednesday.getComponent(1);
        wed.setValue(2);
        try {
            sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        JPanel thursday = (JPanel) enterView.getComponent(10);
        JSpinner thu = (JSpinner) thursday.getComponent(1);
        thu.setValue(2);
        try {
            sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        JPanel friday = (JPanel) enterView.getComponent(11);
        JSpinner fri = (JSpinner) friday.getComponent(1);
        fri.setValue(2);
        try {
            sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        JPanel saturday = (JPanel) enterView.getComponent(12);
        JSpinner sat = (JSpinner) saturday.getComponent(1);
        sat.setValue(2);
        try {
            sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        LabelTextPanel description = (LabelTextPanel) enterView.getComponent(13);
        JTextField descField = (JTextField) description.getComponent(1);
        KeyEvent event7 = new KeyEvent(descField,KeyEvent.KEY_TYPED,System.currentTimeMillis(),
                0,KeyEvent.VK_UNDEFINED,'\b');
        description.dispatchEvent(event7);
        try {
            sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        JPanel buttons = (JPanel) enterView.getComponent(14);
        JButton back = (JButton) buttons.getComponent(0);
        JButton enter = (JButton) buttons.getComponent(1);
        back.doClick();
        enter.doClick();

        EnterState state = enterViewModel.getState();
        assertEquals("advil", nameField.getText());
        assertEquals("advil", state.getMedicineName());
        assertEquals(2, doseSpinner.getValue());
        assertEquals(2, state.getDoseSize());
        assertEquals("mg", unitField.getText());
        assertEquals("mg", state.getDoseUnit());
        assertEquals(20, invSpinner.getValue());
        assertEquals(20, state.getDoseInventory());
        assertEquals(2, sun.getValue());
        assertEquals(2, state.getSundayDoses());
        assertEquals(2, mon.getValue());
        assertEquals(2, state.getMondayDoses());
        assertEquals(2, tue.getValue());
        assertEquals(2, state.getTuesdayDoses());
        assertEquals(2, wed.getValue());
        assertEquals(2, state.getWednesdayDoses());
        assertEquals(2, thu.getValue());
        assertEquals(2, state.getThursdayDoses());
        assertEquals(2, fri.getValue());
        assertEquals(2, state.getFridayDoses());
        assertEquals(2, sat.getValue());
        assertEquals(2, state.getSaturdayDoses());
        assertEquals("", descField.getText());
        assertEquals("", state.getDescription());

        enterViewModel.setState(new EnterState());
        enterViewModel.firePropertyChanged();
        state = enterViewModel.getState();
        try {
            sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        assertEquals("", nameField.getText());
        assertEquals("", state.getMedicineName());
        assertEquals(0, doseSpinner.getValue());
        assertEquals(0, state.getDoseSize());
        assertEquals("", unitField.getText());
        assertEquals("", state.getDoseUnit());
        assertEquals(0, invSpinner.getValue());
        assertEquals(0, state.getDoseInventory());
        assertEquals(0, sun.getValue());
        assertEquals(0, state.getSundayDoses());
        assertEquals(0, mon.getValue());
        assertEquals(0, state.getMondayDoses());
        assertEquals(0, tue.getValue());
        assertEquals(0, state.getTuesdayDoses());
        assertEquals(0, wed.getValue());
        assertEquals(0, state.getWednesdayDoses());
        assertEquals(0, thu.getValue());
        assertEquals(0, state.getThursdayDoses());
        assertEquals(0, fri.getValue());
        assertEquals(0, state.getFridayDoses());
        assertEquals(0, sat.getValue());
        assertEquals(0, state.getSaturdayDoses());
        assertEquals("", descField.getText());
        assertEquals("", state.getDescription());
    }
}