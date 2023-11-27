package interface_adapter;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import java.awt.*;

public class MyTableModel extends AbstractTableModel {
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
