package view;

import interface_adapter.SwitchViewController;
import interface_adapter.TableState;
import interface_adapter.TableViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class TableView extends JLabel implements ActionListener, PropertyChangeListener {
    public final String viewName = "table";
    private final TableViewModel tableViewModel;
    private JTable table;
    private final SwitchViewController switchViewController;
    private final JButton back;
    public TableView(SwitchViewController switchViewController1, TableViewModel tableViewModel1) {
        this.tableViewModel = tableViewModel1;
        this.switchViewController = switchViewController1;
        tableViewModel.addPropertyChangeListener(this);

        back = new JButton(TableViewModel.BACK_BUTTON_LABEL);
        JLabel header = new JLabel(TableViewModel.HEADER_LABEL);
        header.setAlignmentX(Component.CENTER_ALIGNMENT);
        table = new JTable(tableViewModel.getTableModel());
        JPanel buttons = new JPanel();
        buttons.add(back);
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
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(header);
        this.add(table);
        this.add(buttons);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        TableState state = (TableState) evt.getNewValue();
        if (state.getData() != null) {
            table = new JTable(tableViewModel.getTableModel());
        }
    }

}
