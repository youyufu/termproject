package app;

import interface_adapter.MainViewModel;
import interface_adapter.SwitchViewController;
import interface_adapter.TableViewModel;
import interface_adapter.ViewManagerModel;
import interface_adapter.checklistChecked.ChecklistController;
import interface_adapter.checklistChecked.ChecklistViewModel;
import interface_adapter.deleteMedicine.DeleteController;
import interface_adapter.deleteMedicine.DeleteViewModel;
import interface_adapter.enterMedicine.EnterController;
import interface_adapter.enterMedicine.EnterViewModel;
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

        // for testing UI
        SwitchViewController switchViewController = new SwitchViewController();
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
    }
}
