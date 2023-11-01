package view;

import interface_adapter.MainViewModel;
import interface_adapter.SwitchViewController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class MainView extends JLabel implements ActionListener, PropertyChangeListener {
    public final String viewName = "main";
    private final MainViewModel mainViewModel;
    private final JButton enter;
    private final JButton delete;
    private final JButton table;
    private final JButton checklist;
    private final SwitchViewController switchViewController;
    public MainView(SwitchViewController switchViewController, MainViewModel mainViewModel) {
        this.switchViewController = switchViewController;
        this.mainViewModel = mainViewModel;
        mainViewModel.addPropertyChangeListener(this);

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
                            switchViewController.execute("enter");
                        }
                    }
                }
        );
        delete.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(delete)) {
                            switchViewController.execute("delete");
                        }
                    }
                }
        );
        table.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(table)) {
                            switchViewController.execute("table");
                        }
                    }
                }
        );
        checklist.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(checklist)) {
                            switchViewController.execute("checklist");
                        }
                    }
                }
        );

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.add(buttons);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
