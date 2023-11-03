package app;

import interface_adapter.MainViewModel;
import interface_adapter.TableState;
import interface_adapter.checklistChecked.ChecklistState;
import interface_adapter.switchView.SwitchViewController;
import interface_adapter.TableViewModel;
import interface_adapter.ViewManagerModel;
import interface_adapter.checklistChecked.ChecklistController;
import interface_adapter.checklistChecked.ChecklistViewModel;
import interface_adapter.deleteMedicine.DeleteController;
import interface_adapter.deleteMedicine.DeleteViewModel;
import interface_adapter.enterMedicine.EnterController;
import interface_adapter.enterMedicine.EnterViewModel;
import interface_adapter.switchView.SwitchViewPresenter;
import use_case.switchView.SwitchViewInteractor;
import view.*;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        JFrame application = new JFrame("MedBay");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        CardLayout cardLayout = new CardLayout();
        JPanel views = new JPanel(cardLayout);
        application.add(views);
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(views, cardLayout, viewManagerModel);
        MainViewModel mainViewModel = new MainViewModel();
        EnterViewModel enterViewModel = new EnterViewModel();
        DeleteViewModel deleteViewModel = new DeleteViewModel();
        TableViewModel tableViewModel = new TableViewModel();
        ChecklistViewModel checklistViewModel = new ChecklistViewModel();

        // for testing UI
        SwitchViewController switchViewController = new SwitchViewController(new SwitchViewInteractor(new SwitchViewPresenter(viewManagerModel)));
        MainView mainView = new MainView(switchViewController, mainViewModel);
        EnterView enterView = new EnterView(switchViewController, new EnterController(), enterViewModel);
        DeleteView deleteView = new DeleteView(switchViewController, new DeleteController(), deleteViewModel);
        TableView tableView = new TableView(switchViewController, tableViewModel);
        ChecklistView checklistView = new ChecklistView(switchViewController, new ChecklistController(), checklistViewModel);
        views.add(mainView, mainView.viewName);
        views.add(enterView, enterView.viewName);
        views.add(deleteView, deleteView.viewName);
        views.add(tableView, tableView.viewName);
        views.add(checklistView, checklistView.viewName);
        viewManagerModel.setActiveView(mainView.viewName);
        viewManagerModel.firePropertyChanged();
        application.pack();
        application.setVisible(true);

        TableState testTable = new TableState();
        ChecklistState testChecklist = new ChecklistState();
        String[] testMedicine = {"Naloxatone", "2 tablets", "100 tablets", "1", "2", "3", "4", "5", "6", "7", "take by mouth with meals"};
        testTable.addData(testMedicine);
        tableViewModel.setState(testTable);
        testChecklist.addTakeToday(Arrays.copyOfRange(testMedicine, 0, 2));
        checklistViewModel.setState(testChecklist);
        checklistViewModel.firePropertyChangedAddTake(testChecklist.getTakeToday().get(0));

        String[] testMedicine1 = {"Codeine", "5 mL", "500 mL", "1", "2", "3", "4", "5", "6", "7", "take by mouth with that purp sprite yahurr"};
        testTable.addData(testMedicine1);
        tableViewModel.setState(testTable);
        tableViewModel.firePropertyChanged();
        testChecklist.addTakeToday(Arrays.copyOfRange(testMedicine1, 0, 2));
        checklistViewModel.setState(testChecklist);
        checklistViewModel.firePropertyChangedAddTake(testChecklist.getTakeToday().get(1));

        testTable.removeData("Codeine");
        tableViewModel.setState(testTable);
        tableViewModel.firePropertyChanged();
        testChecklist.removeTakeToday("Codeine");
        checklistViewModel.setState(testChecklist);
        checklistViewModel.firePropertyChangedRemoveTake("Codeine");
    }
}
