package view;

import interface_adapter.switchView.SwitchViewController;
import interface_adapter.enterMedicine.EnterController;
import interface_adapter.enterMedicine.EnterState;
import interface_adapter.enterMedicine.EnterViewModel;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class EnterView extends JPanel implements PropertyChangeListener {
    /**
     * EnterView is a JPanel class that serves as the view for enter.
     */

    public final static String viewName = "enter";
    private final EnterViewModel enterViewModel;
    private final JTextField medicineNameInputField = new JTextField(50);
    private final JTextField doseUnitInputField = new JTextField(8);
    private final JSpinner doseSize;
    private final JSpinner doseInventory;
    private final JSpinner sunday;
    private final JSpinner monday;
    private final JSpinner tuesday;
    private final JSpinner wednesday;
    private final JSpinner thursday;
    private final JSpinner friday;
    private final JSpinner saturday;
    private final JTextField descriptionInputField = new JTextField(50);
    private final SwitchViewController switchViewController;
    private final EnterController enterController;
    private final JButton back;
    private final JButton enter;

    static JSpinner addLabeledSpinner(Container c, String label, int columns) {
        SpinnerNumberModel model = new SpinnerNumberModel(0, 0, null, 1);
        JLabel l = new JLabel(label);
        c.add(l);
        JSpinner spinner = new JSpinner(model);
        l.setLabelFor(spinner);
        c.add(spinner);
        JFormattedTextField ftf = getTextField(spinner);
        if (ftf != null) {
            ftf.setColumns(columns);
            ftf.setHorizontalAlignment(JTextField.RIGHT);
        }
        return spinner;
    }
    static JFormattedTextField getTextField(JSpinner spinner) {
        JComponent editor = spinner.getEditor();
        return ((JSpinner.DefaultEditor)editor).getTextField();
    }

    /**
     * Constructs an EnterView with the controllers and view models.
     * @param switchViewController1 The controller for switching views.
     * @param enterController1 The controller for enter.
     * @param enterViewModel1 The view model for enter.
     */
    public EnterView(SwitchViewController switchViewController1, EnterController enterController1, EnterViewModel enterViewModel1) {
        switchViewController = switchViewController1;
        enterController = enterController1;
        enterViewModel = enterViewModel1;
        enterViewModel.addPropertyChangeListener(this);

        JLabel header = new JLabel(EnterViewModel.HEADER_LABEL);
        header.setAlignmentX(Component.CENTER_ALIGNMENT);

        LabelTextPanel medicineName = new LabelTextPanel(new JLabel(EnterViewModel.MEDICINE_NAME_LABEL), medicineNameInputField);
        LabelTextPanel doseUnit = new LabelTextPanel(new JLabel(EnterViewModel.DOSE_UNIT_LABEL), doseUnitInputField);
        JLabel days = new JLabel(EnterViewModel.DAYS_LABEL);
        days.setAlignmentX(Component.CENTER_ALIGNMENT);
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
                            switchViewController.execute(MainView.viewName);
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
                            String medName = currentState.getMedicineName();
                            String doseUnit = currentState.getDoseUnit();
                            String description = currentState.getDescription();
                            enterController.execute(
                                    medName,
                                    currentState.getDoseSize(),
                                    doseUnit,
                                    currentState.getDoseInventory(),
                                    new Integer[]{currentState.getSundayDoses(),
                                            currentState.getMondayDoses(),
                                            currentState.getTuesdayDoses(),
                                            currentState.getWednesdayDoses(),
                                            currentState.getThursdayDoses(),
                                            currentState.getFridayDoses(),
                                            currentState.getSaturdayDoses()},
                                    description
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
                        currentState.setMedicineName(text.replaceAll("\b", "").strip());
                        enterViewModel.setState(currentState);
                    }
                    @Override
                    public void keyPressed(KeyEvent e) {}

                    @Override
                    public void keyReleased(KeyEvent e) {}
                }
        );
        doseUnitInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        EnterState currentState = enterViewModel.getState();
                        String text = doseUnitInputField.getText() + e.getKeyChar();
                        currentState.setDoseUnit(text.strip().replaceAll("\b", ""));
                        enterViewModel.setState(currentState);
                    }
                    @Override
                    public void keyPressed(KeyEvent e) {}
                    @Override
                    public void keyReleased(KeyEvent e) {}
                }
        );
        descriptionInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        EnterState currentState = enterViewModel.getState();
                        String text = descriptionInputField.getText() + e.getKeyChar();
                        currentState.setDescription(text.strip().replaceAll("\b", ""));
                        enterViewModel.setState(currentState);
                    }
                    @Override
                    public void keyPressed(KeyEvent e) {}
                    @Override
                    public void keyReleased(KeyEvent e) {}
                }
        );
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(header);
        this.add(medicineName);
        JPanel doseSizePanel = new JPanel();
        doseSize = addLabeledSpinner(doseSizePanel, EnterViewModel.DOSE_SIZE_LABEL, 3);
        doseSize.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                EnterState currentState = enterViewModel.getState();
                Object currentInt = doseSize.getModel().getValue();
                if (currentInt == null) {currentState.setDoseSize(0);}
                else{currentState.setDoseSize((Integer) currentInt);}
            }
        });
        this.add(doseSizePanel);
        this.add(doseUnit);
        JPanel doseInventoryPanel = new JPanel();
        doseInventory = addLabeledSpinner(doseInventoryPanel, EnterViewModel.DOSE_INVENTORY_LABEL, 5);
        doseInventory.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                EnterState currentState = enterViewModel.getState();
                Object currentInt = doseInventory.getModel().getValue();
                if (currentInt == null) {currentState.setDoseInventory(0);}
                else{currentState.setDoseInventory((Integer) currentInt);}
            }
        });
        this.add(doseInventoryPanel);
        this.add(days);
        JPanel sundayPanel = new JPanel();
        sunday = addLabeledSpinner(sundayPanel, EnterViewModel.SUNDAY_LABEL, 2);
        sunday.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                EnterState currentState = enterViewModel.getState();
                Object currentInt = sunday.getModel().getValue();
                if (currentInt == null) {currentState.setSundayDoses(0);}
                else{currentState.setSundayDoses((Integer) currentInt);}
            }
        });
        this.add(sundayPanel);
        JPanel mondayPanel = new JPanel();
        monday = addLabeledSpinner(mondayPanel, EnterViewModel.MONDAY_LABEL, 2);
        monday.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                EnterState currentState = enterViewModel.getState();
                Object currentInt = monday.getModel().getValue();
                if (currentInt == null) {currentState.setMondayDoses(0);}
                else{currentState.setMondayDoses((Integer) currentInt);}
            }
        });
        this.add(mondayPanel);
        JPanel tuesdayPanel = new JPanel();
        tuesday = addLabeledSpinner(tuesdayPanel, EnterViewModel.TUESDAY_LABEL, 2);
        tuesday.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                EnterState currentState = enterViewModel.getState();
                Object currentInt = tuesday.getModel().getValue();
                if (currentInt == null) {currentState.setTuesdayDoses(0);}
                else{currentState.setTuesdayDoses((Integer) currentInt);}
            }
        });
        this.add(tuesdayPanel);
        JPanel wednesdayPanel = new JPanel();
        wednesday = addLabeledSpinner(wednesdayPanel, EnterViewModel.WEDNESDAY_LABEL, 2);
        wednesday.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                EnterState currentState = enterViewModel.getState();
                Object currentInt = wednesday.getModel().getValue();
                if (currentInt == null) {currentState.setWednesdayDoses(0);}
                else{currentState.setWednesdayDoses((Integer) currentInt);}
            }
        });
        this.add(wednesdayPanel);
        JPanel thursdayPanel = new JPanel();
        thursday = addLabeledSpinner(thursdayPanel, EnterViewModel.THURSDAY_LABEL, 2);
        thursday.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                EnterState currentState = enterViewModel.getState();
                Object currentInt = thursday.getModel().getValue();
                if (currentInt == null) {currentState.setThursdayDoses(0);}
                else{currentState.setThursdayDoses((Integer) currentInt);}
            }
        });
        this.add(thursdayPanel);
        JPanel fridayPanel = new JPanel();
        friday = addLabeledSpinner(fridayPanel, EnterViewModel.FRIDAY_LABEL, 2);
        friday.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                EnterState currentState = enterViewModel.getState();
                Object currentInt = friday.getModel().getValue();
                if (currentInt == null) {currentState.setFridayDoses(0);}
                else{currentState.setFridayDoses((Integer) currentInt);}
            }
        });
        this.add(fridayPanel);
        JPanel saturdayPanel = new JPanel();
        saturday = addLabeledSpinner(saturdayPanel, EnterViewModel.SATURDAY_LABEL, 2);
        saturday.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                EnterState currentState = enterViewModel.getState();
                Object currentInt = saturday.getModel().getValue();
                if (currentInt == null) {currentState.setSaturdayDoses(0);}
                else{currentState.setSaturdayDoses((Integer) currentInt);}
            }
        });
        this.add(saturdayPanel);
        this.add(description);
        this.add(buttons);
    }

    /**
     * Responds to property changes in the enter view model.
     *
     * @param evt A PropertyChangeEvent object describing the event source
     *          and the property that has changed.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        EnterState state = (EnterState) evt.getNewValue();
        if (state.getMessage() != null) {
            JOptionPane.showMessageDialog(this, state.getMessage());
        }
        medicineNameInputField.setText(state.getMedicineName());
        doseSize.setValue(state.getDoseSize());
        doseUnitInputField.setText(state.getDoseUnit());
        doseInventory.setValue(state.getDoseInventory());
        sunday.setValue(state.getSundayDoses());
        monday.setValue(state.getMondayDoses());
        tuesday.setValue(state.getTuesdayDoses());
        wednesday.setValue(state.getWednesdayDoses());
        thursday.setValue(state.getThursdayDoses());
        friday.setValue(state.getFridayDoses());
        saturday.setValue(state.getSaturdayDoses());
        descriptionInputField.setText(state.getDescription());
    }
}
