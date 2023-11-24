package view;

import interface_adapter.MyTableModel;
import interface_adapter.switchView.SwitchViewController;
import interface_adapter.TableState;
import interface_adapter.TableViewModel;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class TableView extends JLabel implements ActionListener, PropertyChangeListener {
    public final String viewName = "table";
    private final TableViewModel tableViewModel;
    private JLabel header;
    private JPanel tablePanel;
    private final SwitchViewController switchViewController;
    private final JButton back;
    private JPanel buttons;
    public TableView(SwitchViewController switchViewController1, TableViewModel tableViewModel1) {
        this.tableViewModel = tableViewModel1;
        this.switchViewController = switchViewController1;
        tableViewModel.addPropertyChangeListener(this);

        header = new JLabel(TableViewModel.HEADER_LABEL);
        header.setAlignmentX(Component.CENTER_ALIGNMENT);

        TableModel tableModel = new MyTableModel(tableViewModel.getState().getData(), tableViewModel.getState().getLongValues());
        JTable table = new JTable(tableModel);
        MyTableModel.initColumnSizes(table);
        JScrollPane tablePane = new JScrollPane(table);
        table.setPreferredScrollableViewportSize(new Dimension(800, 150));
        tablePanel = new JPanel();
        tablePanel.add(tablePane);

        back = new JButton(TableViewModel.BACK_BUTTON_LABEL);
        buttons = new JPanel();
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
        this.add(tablePanel);
        this.add(buttons);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        TableState state = (TableState) evt.getNewValue();
        if (state.getData() != null) {
            this.removeAll();
            tablePanel.removeAll();
            this.add(header);
            JTable table = new JTable(new MyTableModel(state.getData(), state.getLongValues()));
            MyTableModel.initColumnSizes(table);
            JScrollPane tablePane = new JScrollPane(table);
            table.setPreferredScrollableViewportSize(new Dimension(800, 150));
            tablePanel.add(tablePane);
            this.add(tablePanel);
            this.add(buttons);
            this.revalidate();
            this.repaint();
        }
    }

}
