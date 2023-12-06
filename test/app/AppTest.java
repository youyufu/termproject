package app;

import data_access.MedicineAPICallsInterface;
import data_access.MedicineAPICallsObject;
import data_access.MedicineDAO;
import data_access.MedicineDataAccessInterface;
import entity.Dose;
import entity.Medicine;
import interface_adapter.ViewManagerModel;
import interface_adapter.checklistChecked.ChecklistViewModel;
import interface_adapter.deleteMedicine.DeleteViewModel;
import interface_adapter.enterMedicine.EnterViewModel;
import interface_adapter.switchView.SwitchViewController;
import interface_adapter.table.TableViewModel;
import org.junit.jupiter.api.Test;
import view.*;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class AppTest {

    @Test
    void factoryTest() {
        JFrame application = new JFrame("MedBay");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        CardLayout cardLayout = new CardLayout();
        JPanel views = new JPanel(cardLayout);
        application.add(views);
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(views, cardLayout, viewManagerModel);
        EnterViewModel enterViewModel = new EnterViewModel();
        DeleteViewModel deleteViewModel = new DeleteViewModel();
        TableViewModel tableViewModel = new TableViewModel();
        ChecklistViewModel checklistViewModel = new ChecklistViewModel();
        LocalDate localDate = LocalDate.now();
        MedicineDataAccessInterface medicineDAO = MedicineDAO.getMedicineDAO(localDate, "./test.json");
        Dose dose = new Dose(2, 1, "mg");
        Medicine medicine = new Medicine("advil", dose, new Integer[]{2, 2, 2, 2, 2, 2, 2}, "", "");
        medicineDAO.saveMedicine(medicine);
        Dose dose1 = new Dose(2, 3, "mg");
        Medicine medicine1 = new Medicine("tylenol", dose1, new Integer[]{2, 2, 2, 2, 2, 2, 2}, "", "");
        medicineDAO.saveMedicine(medicine1);
        SwitchViewController switchViewController = SwitchViewUseCaseFactory.create(viewManagerModel, checklistViewModel);
        MainView mainView = new MainView(switchViewController);
        MedicineAPICallsInterface medicineAPICallsObject = new MedicineAPICallsObject();
        EnterView enterView = EnterUseCaseFactory.create(switchViewController, enterViewModel, checklistViewModel, tableViewModel, viewManagerModel, medicineDAO, medicineAPICallsObject);
        DeleteView deleteView = DeleteUseCaseFactory.create(switchViewController, deleteViewModel, checklistViewModel, tableViewModel, viewManagerModel, medicineDAO);
        TableView tableView = TableViewFactory.create(switchViewController, tableViewModel, medicineDAO, medicineAPICallsObject);
        ChecklistView checklistView = ChecklistUseCaseFactory.create(switchViewController, checklistViewModel, tableViewModel, medicineDAO);
        views.add(mainView, MainView.viewName);
        views.add(enterView, EnterView.viewName);
        views.add(deleteView, DeleteView.viewName);
        views.add(tableView, TableView.viewName);
        views.add(checklistView, ChecklistView.viewName);
        viewManagerModel.setActiveView(MainView.viewName);
        viewManagerModel.firePropertyChanged();
        application.pack();
        application.setVisible(true);
        assertTrue(mainView.isVisible());
        assertEquals(2, tableViewModel.getState().getData().length);
        assertFalse(checklistViewModel.getRestock().isEmpty());
        JPanel lowStock = (JPanel) checklistView.getComponent(3);
        JLabel label = (JLabel) lowStock.getComponent(0);
        assertEquals("advil (0 doses remaining)", label.getText());
        JLabel label1 = (JLabel) lowStock.getComponent(1);
        assertEquals("tylenol (1 doses remaining)", label1.getText());
        JPanel checklist = (JPanel) checklistView.getComponent(1);
        JCheckBox checkBox = (JCheckBox) checklist.getComponent(0);
        assertEquals("Take 2 mg of tylenol", checkBox.getText());
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class, () -> {checklist.getComponent(1);});
        String expected = "No such child:";
        String actual = exception.getMessage();
        assertTrue(actual.contains(expected));
        medicineDAO.removeMedicine("advil");
        medicineDAO.removeMedicine("tylenol");
    }

    @Test
    void mainTest() {
        Main.main(new String[] {});
        //see if program successfully opens
    }
}