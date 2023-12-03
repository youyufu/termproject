package interface_adapter.checklistChecked;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class ChecklistViewModel extends ViewModel {
    public static final String HEADER_LABEL = "Today's Checklist";
    public static final String LOW_STOCK_LABEL = "Running Low On:";
    public static final String BACK_BUTTON_LABEL = "Back";
    public static final Integer TAKE = -1;
    public static final Integer UNTAKE = 1;
    private ArrayList<String> restock = new ArrayList<>();
    public void addRestock(String medicine) {this.restock.add(medicine);}
    public void removeRestock(String medicine) {this.restock.remove(medicine);}
    public ArrayList<String> getRestock() {return this.restock;}
    public ChecklistViewModel() {}
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    @Override
    public void firePropertyChanged() {support.firePropertyChange("showRestock", null, restock);}
    public void firePropertyChangedAddTake(String[] item) {support.firePropertyChange("addTake", null, item);}
    public void firePropertyChangedUpdateLow(String[] item) {support.firePropertyChange("updateLow", null, item);}
    public void firePropertyChangedRemoveMed(String item) {support.firePropertyChange("removeMed", null, item);}
    public void firePropertyChangedAddLow(String[] item) {support.firePropertyChange("addLow", null, item);}
    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {support.addPropertyChangeListener(listener);}
}
