package interface_adapter;

import javax.swing.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class TableViewModel extends ViewModel{
    public static final String HEADER_LABEL = "Medication Table";
    public static final String BACK_BUTTON_LABEL = "Back";
    private TableState tableState = new TableState();
    public TableViewModel() {super("table");}
    public TableState getState() {return tableState;}
    public void setState(TableState tableState1) {this.tableState = tableState1;}
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    @Override
    public void firePropertyChanged() {support.firePropertyChange("state", null, tableState);
    }
    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {support.addPropertyChangeListener(listener);}
}
