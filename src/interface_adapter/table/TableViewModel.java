package interface_adapter.table;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class TableViewModel extends ViewModel {
    /**
     * TableViewModel is a class that extends ViewModel and manages the state and interactions
     * for a table view.
     */

    public static final String HEADER_LABEL = "Medication Table";
    public static final String BACK_BUTTON_LABEL = "Back";
    public static final String SHOW_BUTTON_LABEL = "Show All Interactions";
    private TableState tableState = new TableState();

    /**
     * Constructs a new instance of TableViewModel.
     */
    public TableViewModel() {}

    /**
     * Get the current state of the table.
     *
     * @return The current table state.
     */
    public TableState getState() {return tableState;}

    /**
     * Set the state of the table.
     *
     * @param tableState1 The new table state to be set.
     */
    public void setState(TableState tableState1) {this.tableState = tableState1;}

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    /**
     * Fires a property change event.
     */
    @Override
    public void firePropertyChanged() {support.firePropertyChange("state", null, tableState);
    }

    /**
     * Adds a property change listener to this model.
     *
     * @param listener The PropertyChangeListener to be added.
     */
    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {support.addPropertyChangeListener(listener);}

    /**
     * Fires a property change about the need to show a popup.
     */
    public void firePopUp(){support.firePropertyChange("popup", null, tableState);};
}
