package app;

import data_access.MedicineDataAccessInterface;
import entity.Medicine;
import interface_adapter.TableState;
import interface_adapter.TableViewModel;
import interface_adapter.switchView.SwitchViewController;
import view.TableView;

import java.util.HashMap;

public class TableViewFactory {
    private TableViewFactory() {}
    public static TableView create(SwitchViewController switchViewController, TableViewModel tableViewModel, MedicineDataAccessInterface medicineDAO) {
        HashMap<String, Medicine> userMedicines = medicineDAO.getUserMedicines();
        TableState tableState = tableViewModel.getState();
        for (Medicine medicine:userMedicines.values()) {
            CrossUseCaseFactory.map.put(medicine.getName(),Integer.parseInt(medicine.getInventoryString().split(" ")[0]));
            CrossUseCaseFactory.mapTotal.put(medicine.getName(),Integer.parseInt(medicine.getInventoryString().split(" ")[0]));
            Integer[] weeklySched = medicine.getWeeklySchedule();
            String[] tableData = new String[]{medicine.getName(), medicine.getDoseString(),
                    medicine.getInventoryString(), String.valueOf(weeklySched[0]), String.valueOf(weeklySched[1]),
                    String.valueOf(weeklySched[2]), String.valueOf(weeklySched[3]), String.valueOf(weeklySched[4]),
                    String.valueOf(weeklySched[5]), String.valueOf(weeklySched[6]), medicine.getDescription()};
            tableState.addData(tableData);
        }
        tableViewModel.setState(tableState);
        TableView tableView = new TableView(switchViewController, tableViewModel);
        tableViewModel.firePropertyChanged();

        return tableView;
    }
}
