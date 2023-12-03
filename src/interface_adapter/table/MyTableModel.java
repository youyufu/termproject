package interface_adapter.table;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import java.awt.*;

public class MyTableModel extends AbstractTableModel {
    /**
     * A custom table model for use in the TableView.
     */
    private final String[] columnNames = {"Medication", "Dose", "Inventory", "Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Additional Info"};
    private Object[][] data;
    private String[] longest;

    /**
     * A constructor for the table model.
     * @param data, the data to be stored/shown in the table.
     * @param longest, the longest strings in each column (for formatting).
     */
    public MyTableModel(Object[][] data, String[] longest) {
        this.data = data;
        this.longest = longest;
    }

    /**
     * Gets the number of rows.
     * @return the number of rows.
     */
    @Override
    public int getRowCount() {return data.length;}

    /**
     * Gets the number of columns.
     * @return the number of columns.
     */
    @Override
    public int getColumnCount() {return columnNames.length;}

    /**
     * Gets the header of the column.
     * @param col, the column being queried.
     * @return the header of the column.
     */
    public String getColumnName(int col) {return columnNames[col];}

    /**
     * Gets the value at a specified index.
     * @param rowIndex, the row whose value is to be queried
     * @param columnIndex, the column whose value is to be queried
     * @return the value at the intersection of indices.
     */
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {return data[rowIndex][columnIndex];}

    /**
     * Gets the longest values in each column.
     * @return the longest values in each column.
     */
    public String[] getLongest() {return this.longest;}

    /**
     * Formats the column sizes to adjust to the size of the longest values.
     * @param table, the table to be formatted.
     */
    public static void initColumnSizes(JTable table) {
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
}
