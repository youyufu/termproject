package view;

import interface_adapter.MainViewModel;
import interface_adapter.switchView.SwitchViewController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class MainView extends JPanel{
    /**
     * MainView is a JPanel class that shows the main interface.
     */

    public final static String viewName = "main";
    private final JButton enter;
    private final JButton delete;
    private final JButton table;
    private final JButton checklist;
    private final SwitchViewController switchViewController;

    /**
     * Constructs a MainView with the controller for view switching.
     *
     * @param switchViewController The controller for switching views.
     */
    public MainView(SwitchViewController switchViewController) {
        this.switchViewController = switchViewController;

        JLabel title = new JLabel((MainViewModel.TITLE_LABEL));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel buttons = new JPanel();
        enter = new JButton(MainViewModel.ENTER_BUTTON_LABEL);
        buttons.add(enter);
        delete = new JButton(MainViewModel.DELETE_BUTTON_LABEL);
        buttons.add(delete);
        table = new JButton(MainViewModel.TABLE_BUTTON_LABEL);
        buttons.add(table);
        checklist = new JButton(MainViewModel.CHECKLIST_BUTTON_LABEL);
        buttons.add(checklist);

        enter.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(enter)) {
                            switchViewController.execute(EnterView.viewName);
                        }
                    }
                }
        );
        delete.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(delete)) {
                            switchViewController.execute(DeleteView.viewName);
                        }
                    }
                }
        );
        table.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(table)) {
                            switchViewController.execute(TableView.viewName);
                        }
                    }
                }
        );
        checklist.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(checklist)) {
                            switchViewController.execute(ChecklistView.viewName);
                        }
                    }
                }
        );
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.add(buttons);
    }
}
