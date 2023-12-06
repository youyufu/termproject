package interface_adapter.deleteMedicine;
import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class DeleteViewModel extends ViewModel {
    /**
     * A view model for the delete medicine screen.
     */

    /**
     * Constants for labels in the DeleteView.
     */
    public static final String BACK_BUTTON_LABEL = "Back";
    public static final String DELETE_BUTTON_LABEL = "Delete";
    public static final String HEADER_LABEL = "Enter your medication below to delete!";
    public static final String MEDICINE_NAME_LABEL = "Name of Medication:";
    private DeleteState deleteState = new DeleteState();
    /**
     * Creates a DeleteViewModel.
     */
    public DeleteViewModel() {}
    /**
     * Set the current state.
     * @param state the state to be set.
     */
    public void setState(DeleteState state) {
        deleteState = state;
    }
    /**
     * Get the current state.
     * @return the current state.
     */
    public DeleteState getState() {
        return deleteState;
    }
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    /**
     * Tells all PropertyChangeListeners there has been a change.
     */
    @Override
    public void firePropertyChanged() {support.firePropertyChange("", null, deleteState);}
    /**
     * Adds a PropertyChangeListener.
     * @param listener, a PropertyChangeListener.
     */
    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {support.addPropertyChangeListener(listener);}
}
