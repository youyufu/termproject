package app;

import data_access.MedicineDataAccessInterface;
import interface_adapter.TableViewModel;
import interface_adapter.ViewManagerModel;
import interface_adapter.checklistChecked.ChecklistViewModel;
import interface_adapter.switchView.SwitchViewController;

import java.util.HashMap;

public class CrossUseCaseFactory {
    public static ViewManagerModel viewManagerModel;
    public static ChecklistViewModel checklistViewModel;
    public static TableViewModel tableViewModel;
    public static SwitchViewController switchViewController;
    public static MedicineDataAccessInterface medicineDAO;
    public static HashMap<String, Integer> map = new HashMap();
    public static HashMap<String, Integer> mapTotal = new HashMap();
    public static void init(ViewManagerModel v,ChecklistViewModel c,TableViewModel t,SwitchViewController s,MedicineDataAccessInterface m){
        viewManagerModel = v;
        checklistViewModel = c;
        tableViewModel = t;
        switchViewController = s;
        medicineDAO = m;
    }
}
