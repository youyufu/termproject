package interface_adapter;

import java.beans.PropertyChangeListener;

public abstract class ViewModel {
    /**
     * An abstract view model.
     */

    /**
     * Constructs a view model.
     */
    public ViewModel() {}

    /**
     * Tells all PropertyChangeListeners there has been a change.
     */
    public abstract void firePropertyChanged();

    /**
     * Adds a PropertyChangeListener.
     * @param listener, a PropertyChangeListener.
     */
    public abstract void addPropertyChangeListener(PropertyChangeListener listener);
}
