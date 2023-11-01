package interface_adapter;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class TableViewModel extends ViewModel{
    public static final String HEADER_LABEL = "Medication Table";
    public static final String BACK_BUTTON_LABEL = "Back";
    private TableState tableState = new TableState();
    private TableModel tableModel = new MyTableModel();
    private class MyTableModel extends AbstractTableModel {
        private String[] columnNames = tableState.getColumnHeaders();
        private Object[][] data = tableState.getData();
        @Override
        public int getRowCount() {return tableState.getData().length;}
        @Override
        public int getColumnCount() {return tableState.getColumnHeaders().length;}
        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {return tableState.getData()[rowIndex][columnIndex];}
    }
    public TableViewModel() {super("table");}
    public TableModel getTableModel() {return tableModel;}

    public TableState getState() {return tableState;}
    public void setState(TableState tableState1) {
        this.tableState = tableState1;
        tableModel = new MyTableModel();
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    @Override
    public void firePropertyChanged() {support.firePropertyChange("state", null, tableState);
    }

    @Override
    public void firePropertyChanged(String[] item) {}

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {support.addPropertyChangeListener(listener);}
}
