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

public class EnterView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "enter";
    private final EnterViewModel enterViewModel;
    private final JTextField medicineNameInputField = new JTextField(50);
    private final JTextField doseUnitInputField = new JTextField(8);
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
        if (editor instanceof JSpinner.DefaultEditor) {
            return ((JSpinner.DefaultEditor)editor).getTextField();
        } else {
            System.err.println("Unexpected editor type: "
                    + spinner.getEditor().getClass()
                    + " isn't a descendant of DefaultEditor");
            return null;
        }
    }
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
                                    new Integer[]{currentState.getSundayDoses(),
                                            currentState.getMondayDoses(),
                                            currentState.getTuesdayDoses(),
                                            currentState.getWednesdayDoses(),
                                            currentState.getThursdayDoses(),
                                            currentState.getFridayDoses(),
                                            currentState.getSaturdayDoses()},
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
        JPanel doseSizePanel = new JPanel();
        JSpinner doseSize = addLabeledSpinner(doseSizePanel, EnterViewModel.DOSE_SIZE_LABEL, 3);
        doseSize.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                EnterState currentState = enterViewModel.getState();
                Integer currentInt = (Integer) doseSize.getModel().getValue();
                currentState.setDoseSize(currentInt);
            }
        });
        this.add(doseSizePanel);
        this.add(doseUnit);
        JPanel doseInventoryPanel = new JPanel();
        JSpinner doseInventory = addLabeledSpinner(doseInventoryPanel, EnterViewModel.DOSE_INVENTORY_LABEL, 5);
        doseInventory.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                EnterState currentState = enterViewModel.getState();
                Integer currentInt = (Integer) doseInventory.getModel().getValue();
                currentState.setDoseInventory(currentInt);
            }
        });
        this.add(doseInventoryPanel);
        this.add(days);
        JPanel sundayPanel = new JPanel();
        JSpinner sunday = addLabeledSpinner(sundayPanel, EnterViewModel.SUNDAY_LABEL, 2);
        sunday.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                EnterState currentState = enterViewModel.getState();
                Integer currentInt = (Integer) sunday.getModel().getValue();
                currentState.setSundayDoses(currentInt);
            }
        });
        this.add(sundayPanel);
        JPanel mondayPanel = new JPanel();
        JSpinner monday = addLabeledSpinner(mondayPanel, EnterViewModel.MONDAY_LABEL, 2);
        monday.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                EnterState currentState = enterViewModel.getState();
                Integer currentInt = (Integer) monday.getModel().getValue();
                currentState.setMondayDoses(currentInt);
            }
        });
        this.add(mondayPanel);
        JPanel tuesdayPanel = new JPanel();
        JSpinner tuesday = addLabeledSpinner(tuesdayPanel, EnterViewModel.TUESDAY_LABEL, 2);
        tuesday.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                EnterState currentState = enterViewModel.getState();
                Integer currentInt = (Integer) tuesday.getModel().getValue();
                currentState.setTuesdayDoses(currentInt);
            }
        });
        this.add(tuesdayPanel);
        JPanel wednesdayPanel = new JPanel();
        JSpinner wednesday = addLabeledSpinner(wednesdayPanel, EnterViewModel.WEDNESDAY_LABEL, 2);
        wednesday.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                EnterState currentState = enterViewModel.getState();
                Integer currentInt = (Integer) wednesday.getModel().getValue();
                currentState.setWednesdayDoses(currentInt);
            }
        });
        this.add(wednesdayPanel);
        JPanel thursdayPanel = new JPanel();
        JSpinner thursday = addLabeledSpinner(thursdayPanel, EnterViewModel.THURSDAY_LABEL, 2);
        thursday.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                EnterState currentState = enterViewModel.getState();
                Integer currentInt = (Integer) thursday.getModel().getValue();
                currentState.setThursdayDoses(currentInt);
            }
        });
        this.add(thursdayPanel);
        JPanel fridayPanel = new JPanel();
        JSpinner friday = addLabeledSpinner(fridayPanel, EnterViewModel.FRIDAY_LABEL, 2);
        friday.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                EnterState currentState = enterViewModel.getState();
                Integer currentInt = (Integer) friday.getModel().getValue();
                currentState.setFridayDoses(currentInt);
            }
        });
        this.add(fridayPanel);
        JPanel saturdayPanel = new JPanel();
        JSpinner saturday = addLabeledSpinner(saturdayPanel, EnterViewModel.SATURDAY_LABEL, 2);
        saturday.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                EnterState currentState = enterViewModel.getState();
                Integer currentInt = (Integer) saturday.getModel().getValue();
                currentState.setSaturdayDoses(currentInt);
            }
        });
        this.add(saturdayPanel);
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
