package interface_adapter.checklistChecked;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ChecklistViewModel extends ViewModel {
    public static final String HEADER_LABEL = "Today's Checklist";
    public static final String LOW_STOCK_LABEL = "Running Low On:";
    public static final String BACK_BUTTON_LABEL = "Back";
    private ChecklistState checklistState = new ChecklistState();
    public ChecklistViewModel() {super("checklist");}
    public void setState(ChecklistState checklistState) {this.checklistState = checklistState;}
    public ChecklistState getState() {return checklistState;}
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    @Override
    public void firePropertyChanged() {support.firePropertyChange("showRestock", null, checklistState);}
    @Override
    public void firePropertyChanged(String[] item) {}
    public void firePropertyChangedAddTake(String[] item) {support.firePropertyChange("addTake", checklistState, item);}
    public void firePropertyChangedRemoveMed(String item) {support.firePropertyChange("removeMed", checklistState, item);}
    public void firePropertyChangedAddLow(String[] item) {support.firePropertyChange("addLow", checklistState, item);}
    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {support.addPropertyChangeListener(listener);}
}
