package view;

import interface_adapter.table.MyTableModel;
import interface_adapter.table.TableState;
import interface_adapter.table.TableViewModel;
import interface_adapter.showInteractions.ShowInteractionsController;
import interface_adapter.switchView.SwitchViewController;
import org.junit.jupiter.api.Test;
import use_case.showInteractions.ShowInteractionsInputBoundary;
import use_case.switchView.SwitchViewInputBoundary;
import use_case.switchView.SwitchViewInputData;

import javax.swing.*;

import static java.lang.Thread.sleep;
import static org.junit.jupiter.api.Assertions.*;

class TableViewTest {

    @Test
    void testTableView() {
        SwitchViewInputBoundary switchViewInputBoundary = new SwitchViewInputBoundary() {
            @Override
            public void execute(SwitchViewInputData switchViewInputData) {
                assertEquals(MainView.viewName, switchViewInputData.getViewName());
            }
        };
        SwitchViewController switchViewController = new SwitchViewController(switchViewInputBoundary);
        TableViewModel tableViewModel= new TableViewModel();
        ShowInteractionsInputBoundary showInteractionsInputBoundary = new ShowInteractionsInputBoundary() {
            @Override
            public void execute() {
                assertTrue(tableViewModel.getState().getMessage().isEmpty());
            }
        };
        ShowInteractionsController showInteractionsController = new ShowInteractionsController(showInteractionsInputBoundary);
        JPanel tableView = new TableView(switchViewController, showInteractionsController, tableViewModel);
        JFrame frame = new JFrame();
        frame.setContentPane(tableView);
        frame.pack();
        frame.setVisible(true);

        try {
            sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        JPanel buttons = (JPanel) tableView.getComponent(2);
        JButton back = (JButton) buttons.getComponent(0);
        back.doClick();
        JButton show = (JButton) buttons.getComponent(1);
        show.doClick();

        TableState tableState = new TableState();
        tableState.addData(new String[]{"advil", "2 mg", "20 mg", "2", "2", "2", "2", "2", "2", "2", ""});
        tableViewModel.setState(tableState);
        tableViewModel.firePropertyChanged();

        try {
            sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        JPanel tablePanel = (JPanel) tableView.getComponent(1);
        JScrollPane tablePane = (JScrollPane) tablePanel.getComponent(0);
        JViewport viewport = tablePane.getViewport();
        JTable table = (JTable) viewport.getComponent(0);
        MyTableModel tableModel = (MyTableModel) table.getModel();
        assertEquals("advil", tableModel.getValueAt(0,0));
        assertEquals("2 mg", tableModel.getValueAt(0,1));
        assertEquals("20 mg", tableModel.getValueAt(0,2));
        assertEquals("2", tableModel.getValueAt(0,3));
        assertEquals("2", tableModel.getValueAt(0,4));
        assertEquals("2", tableModel.getValueAt(0,5));
        assertEquals("2", tableModel.getValueAt(0,6));
        assertEquals("2", tableModel.getValueAt(0,7));
        assertEquals("2", tableModel.getValueAt(0,8));
        assertEquals("2", tableModel.getValueAt(0,9));
        assertEquals("", tableModel.getValueAt(0, 10));
    }
}