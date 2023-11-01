package view;

import interface_adapter.SwitchViewController;
import interface_adapter.deleteMedicine.DeleteController;
import interface_adapter.deleteMedicine.DeleteState;
import interface_adapter.deleteMedicine.DeleteViewModel;
import interface_adapter.enterMedicine.EnterViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class DeleteView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "delete";
    private final DeleteViewModel deleteViewModel;
    private final JTextField medicineNameInputField = new JTextField(50);
    private final SwitchViewController switchViewController;
    private final DeleteController deleteController;
    private final JButton back;
    private final JButton delete;
    public DeleteView(SwitchViewController switchViewController1, DeleteController deleteController1, DeleteViewModel deleteViewModel1) {
        this.switchViewController = switchViewController1;
        this.deleteController = deleteController1;
        this.deleteViewModel = deleteViewModel1;
        deleteViewModel.addPropertyChangeListener(this);

        JLabel header = new JLabel(DeleteViewModel.HEADER_LABEL);
        header.setAlignmentX(Component.CENTER_ALIGNMENT);
        LabelTextPanel medicineName = new LabelTextPanel(new JLabel(DeleteViewModel.MEDICINE_NAME_LABEL), medicineNameInputField);
        JPanel buttons = new JPanel();
        back = new JButton(EnterViewModel.BACK_BUTTON_LABEL);
        buttons.add(back);
        delete = new JButton(EnterViewModel.ENTER_BUTTON_LABEL);
        buttons.add(delete);
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
        delete.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(delete)) {
                            DeleteState currentState = deleteViewModel.getState();
                            deleteController.execute(currentState.getMedicineName());
                        }
                    }
                }
        );
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(header);
        this.add(medicineName);
        this.add(buttons);

    }
    @Override
    public void actionPerformed(ActionEvent e) {
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        DeleteState state = (DeleteState) evt.getNewValue();
        if (state.getDeleteError() != null) {
            JOptionPane.showMessageDialog(this, state.getDeleteError());
        }
    }
}
