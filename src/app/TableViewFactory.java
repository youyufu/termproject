package app;

import data_access.MedicineAPICallsInterface;
import data_access.MedicineDataAccessInterface;
import entity.Medicine;
import interface_adapter.table.TableState;
import interface_adapter.table.TableViewModel;
import interface_adapter.showInteractions.ShowInteractionsController;
import interface_adapter.showInteractions.ShowInteractionsPresenter;
import interface_adapter.switchView.SwitchViewController;
import use_case.showInteractions.ShowInteractionsInputBoundary;
import use_case.showInteractions.ShowInteractionsInteractor;
import use_case.showInteractions.ShowInteractionsOutputBoundary;
import view.TableView;
import java.util.HashMap;

public class TableViewFactory {
    /**
     * TableViewFactory is a factory class designed to create and configure
     * the table view use case.
     */

    private TableViewFactory() {}

    /**
     * Creates and returns a TableView in a table format.
     *
     * @param switchViewController The controller for switching views.
     * @param tableViewModel The view model for table view.
     * @param medicineDAO The DAO for medicine data.
     * @param medicineAPICallsInterface The interface for API calls related to medicine.
     * @return TableView The configured view for displaying data in a table format.
     */
    public static TableView create(SwitchViewController switchViewController, TableViewModel tableViewModel, MedicineDataAccessInterface medicineDAO, MedicineAPICallsInterface medicineAPICallsInterface) {
        HashMap<String, Medicine> userMedicines = medicineDAO.getUserMedicines();
        TableState tableState = tableViewModel.getState();
        for (Medicine medicine:userMedicines.values()) {
            Integer[] weeklySched = medicine.getWeeklySchedule();
            String[] tableData = new String[]{medicine.getName(), medicine.getDoseString(),
                    medicine.getInventoryString(), String.valueOf(weeklySched[0]), String.valueOf(weeklySched[1]),
                    String.valueOf(weeklySched[2]), String.valueOf(weeklySched[3]), String.valueOf(weeklySched[4]),
                    String.valueOf(weeklySched[5]), String.valueOf(weeklySched[6]), medicine.getDescription()};
            tableState.addData(tableData);
        } tableViewModel.setState(tableState);
        ShowInteractionsController showInteractionsController = createShowInteractionsUseCase(tableViewModel, medicineDAO, medicineAPICallsInterface);
        TableView tableView = new TableView(switchViewController, showInteractionsController, tableViewModel);
        tableViewModel.firePropertyChanged();
        return tableView;
    }

    /**
     * Creates and returns a ShowInteractionsController for managing and showing
     * interactions in the table view.
     *
     * @param tableViewModel The view model for table view.
     * @param medicineDataAccessInterface The data access interface for interacting with medicine data.
     * @param medicineAPICallsInterface The interface for API calls related to medicine interactions.
     * @return ShowInteractionsController The configured controller for managing interactions in the table view.
     */
    public static ShowInteractionsController createShowInteractionsUseCase(TableViewModel tableViewModel, MedicineDataAccessInterface medicineDataAccessInterface, MedicineAPICallsInterface medicineAPICallsInterface) {
        ShowInteractionsOutputBoundary showInteractionsOutputBoundary = new ShowInteractionsPresenter(tableViewModel);
        ShowInteractionsInputBoundary showInteractionsInputBoundary = new ShowInteractionsInteractor(medicineDataAccessInterface, showInteractionsOutputBoundary, medicineAPICallsInterface);
        return new ShowInteractionsController(showInteractionsInputBoundary);
    }
}
