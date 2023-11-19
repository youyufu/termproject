package app;

import data_access.MedicineDAO;
import data_access.MedicineDataAccessInterface;
import entity.MedicineFactory;
import entity.Today;
import interface_adapter.MainViewModel;
import interface_adapter.switchView.SwitchViewController;
import interface_adapter.TableViewModel;
import interface_adapter.ViewManagerModel;
import interface_adapter.checklistChecked.ChecklistViewModel;
import interface_adapter.deleteMedicine.DeleteViewModel;
import interface_adapter.enterMedicine.EnterViewModel;
import view.*;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.time.LocalDate;

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
        LocalDate localDate = LocalDate.now();
        MedicineDataAccessInterface medicineDAO = getMedicineDAO(localDate);
        SwitchViewController switchViewController = SwitchViewUseCaseFactory.create(viewManagerModel, checklistViewModel);
        MainView mainView = new MainView(switchViewController, mainViewModel);
        EnterView enterView = EnterUseCaseFactory.create(switchViewController, enterViewModel, checklistViewModel, tableViewModel, medicineDAO);
        DeleteView deleteView = DeleteUseCaseFactory.create(switchViewController, deleteViewModel, checklistViewModel, tableViewModel, medicineDAO);
        TableView tableView = TableViewFactory.create(switchViewController, tableViewModel, medicineDAO);
        ChecklistView checklistView = ChecklistUseCaseFactory.create(switchViewController, checklistViewModel, tableViewModel, medicineDAO);
        views.add(mainView, mainView.viewName);
        views.add(enterView, enterView.viewName);
        views.add(deleteView, deleteView.viewName);
        views.add(tableView, tableView.viewName);
        views.add(checklistView, checklistView.viewName);
        viewManagerModel.setActiveView(mainView.viewName);
        viewManagerModel.firePropertyChanged();
        application.pack();
        application.setVisible(true);
        checklistViewModel.firePropertyChanged();
    }

    private static MedicineDAO getMedicineDAO(LocalDate localDate) {
        String day = localDate.getDayOfWeek().name();
        Integer dayInt = null;
        switch (day) {
            case "SUNDAY" -> dayInt = 0;
            case "MONDAY" -> dayInt = 1;
            case "TUESDAY" -> dayInt = 2;
            case "WEDNESDAY" -> dayInt = 3;
            case "THURSDAY" -> dayInt = 4;
            case "FRIDAY" -> dayInt = 5;
            case "SATURDAY" -> dayInt = 6;
        }
        try {
            return new MedicineDAO("./medicine.json", new Today(dayInt), new MedicineFactory());
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }
}
