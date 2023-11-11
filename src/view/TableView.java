package view;

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
        initColumnSizes(table);
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
    private class MyTableModel extends AbstractTableModel {
        private final String[] columnNames = {"Medication", "Dose", "Inventory", "Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Additional Info"};
        private Object[][] data;
        private String[] longest;
        public MyTableModel(Object[][] data, String[] longest) {
            this.data = data;
            this.longest = longest;
        }
        @Override
        public int getRowCount() {return data.length;}
        @Override
        public int getColumnCount() {return columnNames.length;}
        public String getColumnName(int col) {return columnNames[col];}
        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {return data[rowIndex][columnIndex];}
        public String[] getLongest() {return this.longest;}
    }
    private void initColumnSizes(JTable table) {
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );

        MyTableModel model = (MyTableModel) table.getModel();
        TableColumn column = null;
        Component comp = null;
        int headerWidth = 0;
        int cellWidth = 0;
        Object[] longValues = model.getLongest();
        TableCellRenderer headerRenderer =
                table.getTableHeader().getDefaultRenderer();

        for (int i = 0; i < 11; i++) {

            column = table.getColumnModel().getColumn(i);
            column.setCellRenderer(centerRenderer);
            comp = ((TableCellRenderer) headerRenderer).getTableCellRendererComponent(
                    null, column.getHeaderValue(),
                    false, false, 0, 0);
            headerWidth = comp.getPreferredSize().width;

            comp = table.getDefaultRenderer(model.getColumnClass(i)).
                    getTableCellRendererComponent(
                            table, longValues[i],
                            false, false, 0, i);
            cellWidth = comp.getPreferredSize().width;
            column.setPreferredWidth(Math.max(headerWidth, cellWidth));
        }
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
            initColumnSizes(table);
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
