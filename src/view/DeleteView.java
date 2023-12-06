package view;

import interface_adapter.switchView.SwitchViewController;
import interface_adapter.deleteMedicine.DeleteController;
import interface_adapter.deleteMedicine.DeleteState;
import interface_adapter.deleteMedicine.DeleteViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class DeleteView extends JPanel implements PropertyChangeListener {
    /**
     * DeleteView is a JPanel class that functions as the view for delete.
     */

    public final static String viewName = "delete";
    private final DeleteViewModel deleteViewModel;
    private final JTextField medicineNameInputField = new JTextField(50);
    private final SwitchViewController switchViewController;
    private final DeleteController deleteController;
    private final JButton back;
    private final JButton delete;

    /**
     * Constructs a DeleteView with controllers and view models.
     *
     * @param switchViewController1 The controller for switching views.
     * @param deleteController1 The controller for delete.
     * @param deleteViewModel1 The view model for delete.
     */
    public DeleteView(SwitchViewController switchViewController1, DeleteController deleteController1, DeleteViewModel deleteViewModel1) {
        this.switchViewController = switchViewController1;
        this.deleteController = deleteController1;
        this.deleteViewModel = deleteViewModel1;
        deleteViewModel.addPropertyChangeListener(this);

        JLabel header = new JLabel(DeleteViewModel.HEADER_LABEL);
        header.setAlignmentX(Component.CENTER_ALIGNMENT);
        LabelTextPanel medicineName = new LabelTextPanel(new JLabel(DeleteViewModel.MEDICINE_NAME_LABEL), medicineNameInputField);
        JPanel buttons = new JPanel();
        back = new JButton(DeleteViewModel.BACK_BUTTON_LABEL);
        buttons.add(back);
        delete = new JButton(DeleteViewModel.DELETE_BUTTON_LABEL);
        buttons.add(delete);
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
        delete.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(delete)) {
                            DeleteState currentState = deleteViewModel.getState();
                            String medName = currentState.getMedicineName();
                            deleteController.execute(medName);
                        }
                    }
                }
        );
        medicineNameInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        DeleteState currentState = deleteViewModel.getState();
                        String text = medicineNameInputField.getText() + e.getKeyChar();
                        currentState.setMedicineName(text.strip().replaceAll("\b", ""));
                        deleteViewModel.setState(currentState);
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
        this.add(buttons);

    }

    /**
     * Responds to property changes in the delete view model.
     *
     * @param evt A PropertyChangeEvent object describing the event source
     *          and the property that has changed.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        DeleteState state = (DeleteState) evt.getNewValue();
        if (state.getDeleteError() != null) {
            JOptionPane.showMessageDialog(this, state.getDeleteError());
        } medicineNameInputField.setText(state.getMedicineName());
    }
}
