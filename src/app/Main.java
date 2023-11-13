package app;

import data_access.MedicineDAO;
import entity.MedicineFactory;
import interface_adapter.MainViewModel;
import interface_adapter.enterMedicine.EnterPresenter;
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
import use_case.enterMedicine.EnterInputBoundary;
import use_case.enterMedicine.EnterInteractor;
import use_case.enterMedicine.EnterOutputBoundary;
import use_case.switchView.SwitchViewInteractor;
import view.*;

import javax.swing.*;
import java.awt.*;

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
        MedicineDAO medicineDAO = new MedicineDAO();

        // for testing UI
        SwitchViewController switchViewController = new SwitchViewController(new SwitchViewInteractor(new SwitchViewPresenter(viewManagerModel)));
        MainView mainView = new MainView(switchViewController, mainViewModel);
        EnterOutputBoundary enterPresenter = new EnterPresenter(enterViewModel, checklistViewModel, tableViewModel);
        EnterInputBoundary enterInteractor = new EnterInteractor(medicineDAO, enterPresenter, new MedicineFactory());
        EnterController enterController = new EnterController(enterInteractor);
        EnterView enterView = new EnterView(switchViewController, enterController, enterViewModel);
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
    }
}
