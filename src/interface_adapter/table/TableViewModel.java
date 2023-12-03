package interface_adapter.table;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class TableViewModel extends ViewModel {
    public static final String HEADER_LABEL = "Medication Table";
    public static final String BACK_BUTTON_LABEL = "Back";
    public static final String SHOW_BUTTON_LABEL = "Show All Interactions";
    private TableState tableState = new TableState();
    public TableViewModel() {}
    public TableState getState() {return tableState;}
    public void setState(TableState tableState1) {this.tableState = tableState1;}
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    @Override
    public void firePropertyChanged() {support.firePropertyChange("state", null, tableState);
    }
    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {support.addPropertyChangeListener(listener);}

    public void firePopUp(String message){support.firePropertyChange("popup", null, tableState);};
}
