package view;

import interface_adapter.switchView.SwitchViewController;
import interface_adapter.enterMedicine.EnterController;
import interface_adapter.enterMedicine.EnterState;
import interface_adapter.enterMedicine.EnterViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class EnterView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "enter";
    private final EnterViewModel enterViewModel;
    private final JTextField medicineNameInputField = new JTextField(50);
    private final JTextField doseSizeInputField = new JTextField(4);
    private final JTextField doseUnitInputField = new JTextField(10);
    private final JTextField doseInventoryInputField = new JTextField(5);
    private final JTextField sundayInputField = new JTextField(2);
    private final JTextField mondayInputField = new JTextField(2);
    private final JTextField tuesdayInputField = new JTextField(2);
    private final JTextField wednesdayInputField = new JTextField(2);
    private final JTextField thursdayInputField = new JTextField(2);
    private final JTextField fridayInputField = new JTextField(2);
    private final JTextField saturdayInputField = new JTextField(2);
    private final JTextField descriptionInputField = new JTextField(75);
    private final SwitchViewController switchViewController;
    private final EnterController enterController;
    private final JButton back;
    private final JButton enter;

    public EnterView(SwitchViewController switchViewController1, EnterController enterController1, EnterViewModel enterViewModel1) {
        switchViewController = switchViewController1;
        enterController = enterController1;
        enterViewModel = enterViewModel1;
        enterViewModel.addPropertyChangeListener(this);

        JLabel header = new JLabel(EnterViewModel.HEADER_LABEL);
        header.setAlignmentX(Component.CENTER_ALIGNMENT);

        LabelTextPanel medicineName = new LabelTextPanel(new JLabel(EnterViewModel.MEDICINE_NAME_LABEL), medicineNameInputField);
        LabelTextPanel doseSize = new LabelTextPanel(new JLabel(EnterViewModel.DOSE_SIZE_LABEL), doseSizeInputField);
        LabelTextPanel doseUnit = new LabelTextPanel(new JLabel(EnterViewModel.DOSE_UNIT_LABEL), doseUnitInputField);
        LabelTextPanel doseInventory = new LabelTextPanel(new JLabel(EnterViewModel.DOSE_INVENTORY_LABEL), doseInventoryInputField);
        JLabel days = new JLabel(EnterViewModel.DAYS_LABEL);
        days.setAlignmentX(Component.CENTER_ALIGNMENT);
        LabelTextPanel sunday = new LabelTextPanel(new JLabel(EnterViewModel.SUNDAY_LABEL), sundayInputField);
        LabelTextPanel monday = new LabelTextPanel(new JLabel(EnterViewModel.MONDAY_LABEL), mondayInputField);
        LabelTextPanel tuesday = new LabelTextPanel(new JLabel(EnterViewModel.TUESDAY_LABEL), tuesdayInputField);
        LabelTextPanel wednesday = new LabelTextPanel(new JLabel(EnterViewModel.WEDNESDAY_LABEL), wednesdayInputField);
        LabelTextPanel thursday = new LabelTextPanel(new JLabel(EnterViewModel.THURSDAY_LABEL), thursdayInputField);
        LabelTextPanel friday = new LabelTextPanel(new JLabel(EnterViewModel.FRIDAY_LABEL), fridayInputField);
        LabelTextPanel saturday = new LabelTextPanel(new JLabel(EnterViewModel.SATURDAY_LABEL), saturdayInputField);
        LabelTextPanel description = new LabelTextPanel(new JLabel(EnterViewModel.DESCRIPTION_LABEL), descriptionInputField);

        JPanel buttons = new JPanel();
        back = new JButton(EnterViewModel.BACK_BUTTON_LABEL);
        buttons.add(back);
        enter = new JButton(EnterViewModel.ENTER_BUTTON_LABEL);
        buttons.add(enter);
        back.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(back)) {
                            switchViewController.execute("main");
                        }
                    }
                }
        );
        enter.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(enter)) {
                            EnterState currentState = enterViewModel.getState();
                            enterController.execute(
                                    currentState.getMedicineName(),
                                    currentState.getDoseSize(),
                                    currentState.getDoseUnit(),
                                    currentState.getDoseInventory(),
                                    currentState.getSundayDoses(),
                                    currentState.getMondayDoses(),
                                    currentState.getTuesdayDoses(),
                                    currentState.getWednesdayDoses(),
                                    currentState.getThursdayDoses(),
                                    currentState.getFridayDoses(),
                                    currentState.getSaturdayDoses(),
                                    currentState.getDescription()
                            );
                        }
                    }
                }
        );
        medicineNameInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        EnterState currentState = enterViewModel.getState();
                        String text = medicineNameInputField.getText() + e.getKeyChar();
                        currentState.setMedicineName(text);
                        enterViewModel.setState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {

                    }

                    @Override
                    public void keyReleased(KeyEvent e) {

                    }
                }
        );
        doseSizeInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        EnterState currentState = enterViewModel.getState();
                        String text = doseSizeInputField.getText() + e.getKeyChar();
                        currentState.setDoseSize(text);
                        enterViewModel.setState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {

                    }

                    @Override
                    public void keyReleased(KeyEvent e) {

                    }
                }
        );
        doseUnitInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        EnterState currentState = enterViewModel.getState();
                        String text = doseUnitInputField.getText() + e.getKeyChar();
                        currentState.setDoseUnit(text);
                        enterViewModel.setState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {

                    }

                    @Override
                    public void keyReleased(KeyEvent e) {

                    }
                }
        );
        doseInventoryInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        EnterState currentState = enterViewModel.getState();
                        String text = doseInventoryInputField.getText() + e.getKeyChar();
                        currentState.setDoseInventory(text);
                        enterViewModel.setState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {

                    }

                    @Override
                    public void keyReleased(KeyEvent e) {

                    }
                }
        );
        sundayInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        EnterState currentState = enterViewModel.getState();
                        String text = sundayInputField.getText() + e.getKeyChar();
                        currentState.setSundayDoses(text);
                        enterViewModel.setState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {

                    }

                    @Override
                    public void keyReleased(KeyEvent e) {

                    }
                }
        );
        mondayInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        EnterState currentState = enterViewModel.getState();
                        String text = mondayInputField.getText() + e.getKeyChar();
                        currentState.setMondayDoses(text);
                        enterViewModel.setState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {

                    }

                    @Override
                    public void keyReleased(KeyEvent e) {

                    }
                }
        );
        tuesdayInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        EnterState currentState = enterViewModel.getState();
                        String text = tuesdayInputField.getText() + e.getKeyChar();
                        currentState.setTuesdayDoses(text);
                        enterViewModel.setState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {

                    }

                    @Override
                    public void keyReleased(KeyEvent e) {

                    }
                }
        );
        wednesdayInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        EnterState currentState = enterViewModel.getState();
                        String text = wednesdayInputField.getText() + e.getKeyChar();
                        currentState.setWednesdayDoses(text);
                        enterViewModel.setState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {

                    }

                    @Override
                    public void keyReleased(KeyEvent e) {

                    }
                }
        );
        thursdayInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        EnterState currentState = enterViewModel.getState();
                        String text = thursdayInputField.getText() + e.getKeyChar();
                        currentState.setThursdayDoses(text);
                        enterViewModel.setState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {

                    }

                    @Override
                    public void keyReleased(KeyEvent e) {

                    }
                }
        );
        fridayInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        EnterState currentState = enterViewModel.getState();
                        String text = fridayInputField.getText() + e.getKeyChar();
                        currentState.setFridayDoses(text);
                        enterViewModel.setState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {

                    }

                    @Override
                    public void keyReleased(KeyEvent e) {

                    }
                }
        );
        saturdayInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        EnterState currentState = enterViewModel.getState();
                        String text = saturdayInputField.getText() + e.getKeyChar();
                        currentState.setSaturdayDoses(text);
                        enterViewModel.setState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {

                    }

                    @Override
                    public void keyReleased(KeyEvent e) {

                    }
                }
        );
        descriptionInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        EnterState currentState = enterViewModel.getState();
                        String text = descriptionInputField.getText() + e.getKeyChar();
                        currentState.setDescription(text);
                        enterViewModel.setState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {

                    }

                    @Override
                    public void keyReleased(KeyEvent e) {

                    }
                }
        );
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(header);
        this.add(medicineName);
        this.add(doseSize);
        this.add(doseUnit);
        this.add(doseInventory);
        this.add(days);
        this.add(sunday);
        this.add(monday);
        this.add(tuesday);
        this.add(wednesday);
        this.add(thursday);
        this.add(friday);
        this.add(saturday);
        this.add(description);
        this.add(buttons);
    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        EnterState state = (EnterState) evt.getNewValue();
        if (state.getEnterError() != null) {
            JOptionPane.showMessageDialog(this, state.getEnterError());
        }
    }
}
