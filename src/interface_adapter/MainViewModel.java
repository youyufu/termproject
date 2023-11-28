package interface_adapter;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class MainViewModel extends ViewModel{
    public static final String TITLE_LABEL = "MedBay";
    public static final String ENTER_BUTTON_LABEL = "Enter Medicine";
    public static final String DELETE_BUTTON_LABEL = "Delete Medicine";
    public static final String TABLE_BUTTON_LABEL = "Medicine Table";
    public static final String CHECKLIST_BUTTON_LABEL = "Daily Checklist";
    public MainViewModel() {
        super("main");
    }
    @Override
    public void firePropertyChanged() {}
    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {}
}