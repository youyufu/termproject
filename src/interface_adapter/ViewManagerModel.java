package interface_adapter;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ViewManagerModel{
    /**
     * A view model for the ViewManager.
     */

    private String activeViewName;

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    /**
     * Gets the active view.
     * @return the name of the active view.
     */
    public String getActiveView() {return activeViewName;}

    /**
     * Sets the active view.
     * @param activeView, the name of the view to set to active.
     */
    public void setActiveView(String activeView) {this.activeViewName = activeView;}

    /**
     * Tells all PropertyChangeListeners that the active view has been changed.
     */
    public void firePropertyChanged() {support.firePropertyChange("view", null, this.activeViewName);}

    /**
     * Adds a PropertyChangeListener.
     * @param listener, a PropertyChangeListener.
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {support.addPropertyChangeListener(listener);}
}
