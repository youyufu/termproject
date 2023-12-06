package interface_adapter.checklistChecked;


import app.SwitchViewUseCaseFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.switchView.SwitchViewController;
import interface_adapter.table.TableState;
import interface_adapter.table.TableViewModel;
import org.junit.jupiter.api.Test;
import use_case.checklistChecked.ChecklistInputBoundary;
import use_case.checklistChecked.ChecklistInputData;
import use_case.checklistChecked.ChecklistOutputData;
import view.ChecklistView;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.*;

public class ChecklistCheckedPresenterTest {
    @Test
    void execute() {
        ChecklistViewModel checklistViewModel = new ChecklistViewModel();
        TableViewModel tableViewModel = new TableViewModel();
        SwitchViewController switchViewController = SwitchViewUseCaseFactory.create(new ViewManagerModel(), checklistViewModel);
        ChecklistController checklistController = new ChecklistController(new ChecklistInputBoundary() {
            @Override
            public void execute(ChecklistInputData checklistInputData) {}
        });
        ChecklistView checklistView = new ChecklistView(switchViewController, checklistController, checklistViewModel);

        TableState tableState = new TableState();
        tableState.addData(new String[]{"advil", "2 mg", "20 mg", "2", "2", "2", "2", "2", "2", "2", ""});
        tableViewModel.setState(tableState);
        tableViewModel.firePropertyChanged();
        ChecklistPresenter checklistPresenter = new ChecklistPresenter(checklistViewModel,tableViewModel);
        ChecklistOutputData checklistOutputData = new ChecklistOutputData("advil","1 mg","0");
        checklistPresenter.take(checklistOutputData);
        assertArrayEquals(new String[]{"advil", "2 mg", "1 mg", "2", "2", "2", "2", "2", "2", "2", ""}, tableViewModel.getState().getData()[0]);
        assertFalse(checklistViewModel.getRestock().isEmpty());
        JPanel lowStock = (JPanel) checklistView.getComponent(3);
        JLabel label = (JLabel) lowStock.getComponent(0);
        assertEquals("advil (0 doses remaining)", label.getText());
        ChecklistOutputData checklistOutputData1 = new ChecklistOutputData("advil","29 mg","14");
        checklistPresenter.untake(checklistOutputData1);
        assertArrayEquals(new String[]{"advil", "2 mg", "29 mg", "2", "2", "2", "2", "2", "2", "2", ""}, tableViewModel.getState().getData()[0]);
        assertTrue(checklistViewModel.getRestock().isEmpty());
        JPanel checklist = (JPanel) checklistView.getComponent(1);
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class, () -> {checklist.getComponent(1);});
        String expected = "No such child:";
        String actual = exception.getMessage();
        assertTrue(actual.contains(expected));
        Exception exception0 = assertThrows(ArrayIndexOutOfBoundsException.class, () -> {lowStock.getComponent(0);});
        String expected0 = "No such child:";
        String actual0 = exception0.getMessage();
        assertTrue(actual0.contains(expected0));
    }
}
