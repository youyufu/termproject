package interface_adapter.checklistChecked;

import interface_adapter.ViewModel;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class ChecklistViewModel extends ViewModel {
    /**
     * ChecklistViewModel is a view model in the interface adapter layer that manages
     * the state and presentation logic for checklist.
     */


    public static final String HEADER_LABEL = "Today's Checklist";
    public static final String LOW_STOCK_LABEL = "Running Low On:";
    public static final String BACK_BUTTON_LABEL = "Back";
    public static final Integer TAKE = -1;
    public static final Integer UNTAKE = 1;
    private ArrayList<String> restock = new ArrayList<>();

    /**
     * Adds a medicine to the restock list.
     *
     * @param medicine The name of the medicine.
     */
    public void addRestock(String medicine) {this.restock.add(medicine);}

    /**
     * Removes a medicine from the restock list.
     *
     * @param medicine The name of the medicine.
     */
    public void removeRestock(String medicine) {this.restock.remove(medicine);}

    /**
     * Returns the list of medicines marked for restocking.
     *
     * @return ArrayList of medicines that need to be restocked.
     */
    public ArrayList<String> getRestock() {return this.restock;}

    /**
     * Constructs a new ChecklistViewModel with initial settings.
     */
    public ChecklistViewModel() {}
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    /**
     * Fires a property change event about changes in the restock list.
     */
    @Override
    public void firePropertyChanged() {support.firePropertyChange("showRestock", null, restock);}

    /**
     * Fires a property change event about a new medication take entry.
     *
     * @param item The taken medication.
     */
    public void firePropertyChangedAddTake(String[] item) {support.firePropertyChange("addTake", null, item);}

    /**
     * Fires a property change event to update the list of medications that are running low.
     *
     * @param item The updated information of low medication.
     */
    public void firePropertyChangedUpdateLow(String[] item) {support.firePropertyChange("updateLow", null, item);}

    /**
     * Fires a property change event to remove a medication from the view.
     *
     * @param item The name of the medication to be removed.
     */
    public void firePropertyChangedRemoveMed(String item) {support.firePropertyChange("removeMed", null, item);}

    /**
     * Fires a property change event to add a medication to the list of medications running low.
     *
     * @param item The medication running low.
     */
    public void firePropertyChangedAddLow(String[] item) {support.firePropertyChange("addLow", null, item);}

    /**
     * Fires a property change event to add a medication to the list of medications running low.
     *
     * @param item The medication running low.
     */
    public void firePropertyChangedRemoveLow(String item) {support.firePropertyChange("removeLow", null, item);}

    /**
     * Adds a property change listener to this model.
     *
     * @param listener The PropertyChangeListener to be added.
     */
    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {support.addPropertyChangeListener(listener);}
}
