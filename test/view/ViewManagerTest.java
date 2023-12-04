package view;

import app.SwitchViewUseCaseFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.checklistChecked.ChecklistController;
import interface_adapter.checklistChecked.ChecklistViewModel;
import interface_adapter.switchView.SwitchViewController;
import org.junit.jupiter.api.Test;
import use_case.checklistChecked.ChecklistInputBoundary;
import use_case.checklistChecked.ChecklistInputData;

import javax.swing.*;

import java.awt.*;

import static java.lang.Thread.sleep;
import static org.junit.jupiter.api.Assertions.*;

class ViewManagerTest {

    @Test
    void propertyChange() {
        JFrame test = new JFrame();
        test.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        CardLayout cardLayout = new CardLayout();
        JPanel views = new JPanel(cardLayout);
        test.add(views);
        new ViewManager(views, cardLayout, viewManagerModel);
        ChecklistViewModel checklistViewModel = new ChecklistViewModel();
        SwitchViewController switchViewController = SwitchViewUseCaseFactory.create(viewManagerModel, checklistViewModel);
        MainView mainView = new MainView(switchViewController);
        mainView.setName(MainView.viewName);
        ChecklistInputBoundary checklistInputBoundary = new ChecklistInputBoundary() {
            @Override
            public void execute(ChecklistInputData checklistInputData) {}
        };
        ChecklistController checklistController = new ChecklistController(checklistInputBoundary);
        ChecklistView checklistView = new ChecklistView(switchViewController, checklistController, checklistViewModel);
        checklistView.setName(ChecklistView.viewName);
        views.add(mainView, MainView.viewName);
        views.add(checklistView, ChecklistView.viewName);
        viewManagerModel.setActiveView(MainView.viewName);
        viewManagerModel.firePropertyChanged();
        test.pack();
        test.setVisible(true);
        assertTrue(mainView.isVisible());
        assertFalse(checklistView.isVisible());
        try {
            sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        viewManagerModel.setActiveView(ChecklistView.viewName);
        viewManagerModel.firePropertyChanged();
        try {
            sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        assertTrue(checklistView.isVisible());
        assertFalse(mainView.isVisible());
    }
}