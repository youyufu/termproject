package interface_adapter.enterMedicine;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class EnterViewModel extends ViewModel {
    /**
     * A view model for the enter medicine screen.
     */

    /**
     * Constants for labels in the EnterView.
     */
    public static final String BACK_BUTTON_LABEL = "Back";
    public static final String HEADER_LABEL = "Enter your medication below!";
    public static final String MEDICINE_NAME_LABEL = "Name of Medication:";
    public static final String DOSE_SIZE_LABEL = "Dose Size:";
    public static final String DOSE_UNIT_LABEL = "Dose Unit:";
    public static final String DOSE_INVENTORY_LABEL = "Dose Inventory:";
    public static final String DAYS_LABEL = "For each day of the week, please indicate the number of doses per day!";
    public static final String SUNDAY_LABEL = "SUN:";
    public static final String MONDAY_LABEL = "MON:";
    public static final String TUESDAY_LABEL = "TUE:";
    public static final String WEDNESDAY_LABEL = "WED:";
    public static final String THURSDAY_LABEL = "THU:";
    public static final String FRIDAY_LABEL = "FRI:";
    public static final String SATURDAY_LABEL = "SAT:";
    public static final String DESCRIPTION_LABEL = "Please add any additional information or instructions here:";
    public static final String ENTER_BUTTON_LABEL = "Enter";
    private EnterState enterState = new EnterState();

    /**
     * Creates an EnterViewModel.
     */
    public EnterViewModel() {}

    /**
     * Set the current state.
     * @param state the state to be set.
     */
    public void setState(EnterState state) {
        enterState = state;
    }

    /**
     * Get the current state.
     * @return the current state.
     */
    public EnterState getState() {
        return enterState;
    }
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    /**
     * Tells all PropertyChangeListeners there has been a change.
     */
    @Override
    public void firePropertyChanged() {support.firePropertyChange("", null, enterState);}

    /**
     * Adds a PropertyChangeListener.
     * @param listener, a PropertyChangeListener.
     */
    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {support.addPropertyChangeListener(listener);}
}
