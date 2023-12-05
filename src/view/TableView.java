package view;

import interface_adapter.table.MyTableModel;
import interface_adapter.showInteractions.ShowInteractionsController;
import interface_adapter.switchView.SwitchViewController;
import interface_adapter.table.TableState;
import interface_adapter.table.TableViewModel;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class TableView extends JPanel implements PropertyChangeListener {
    /**
     * TableView is a JPanel class that shows the view for displaying tabular data.
     */

    public final static String viewName = "table";
    private final TableViewModel tableViewModel;
    private JLabel header;
    private JPanel tablePanel;
    private final SwitchViewController switchViewController;
    private final ShowInteractionsController showInteractionsController;
    private final JButton back;
    private final JButton show;
    private JPanel buttons;

    /**
     * Constructs a TableView with the  controllers and view model.
     *
     * @param switchViewController1 The controller for switching views.
     * @param showInteractionsController The controller for showing data interactions.
     * @param tableViewModel1 The view model for table.
     */
    public TableView(SwitchViewController switchViewController1, ShowInteractionsController showInteractionsController, TableViewModel tableViewModel1) {
        this.tableViewModel = tableViewModel1;
        this.switchViewController = switchViewController1;
        this.showInteractionsController = showInteractionsController;
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
                            switchViewController.execute(MainView.viewName);
                        }
                    }
                }
        );

        show = new JButton(TableViewModel.SHOW_BUTTON_LABEL);
        buttons.add(show);
        show.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource().equals(show)) {
                    showInteractionsController.execute();
                }
            }
        });
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(header);
        this.add(tablePanel);
        this.add(buttons);
    }

    /**
     * Responds to property changes in the table view model.
     *
     * @param evt A PropertyChangeEvent object describing the event source
     *          and the property that has changed.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        TableState state = (TableState) evt.getNewValue();
        if (evt.getPropertyName().equals("popup")) {
            JOptionPane.showMessageDialog(this, state.getMessage());
        }
        else{
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

}
