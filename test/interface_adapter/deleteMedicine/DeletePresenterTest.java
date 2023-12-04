package interface_adapter.deleteMedicine;

import interface_adapter.deleteMedicine.DeletePresenter;
import interface_adapter.table.TableState;
import interface_adapter.table.TableViewModel;
import interface_adapter.ViewManagerModel;
import interface_adapter.checklistChecked.ChecklistViewModel;
import use_case.deleteMedicine.DeleteOutputData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import view.MainView;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class DeletePresenterTest {

    private DeleteViewModel deleteViewModel;
    private ChecklistViewModel checklistViewModel;
    private TableViewModel tableViewModel;
    private ViewManagerModel viewManagerModel;
    private DeletePresenter deletePresenter;

    @BeforeEach
    public void setUp() {
        deleteViewModel = new DeleteViewModel();
        checklistViewModel = new ChecklistViewModel();
        tableViewModel = new TableViewModel();
        viewManagerModel = new ViewManagerModel();
        deletePresenter = new DeletePresenter(deleteViewModel, checklistViewModel, tableViewModel, viewManagerModel);
    }

    @Test
    public void testPrepareSuccessView() {
        DeleteOutputData entry = new DeleteOutputData("SampleMedicine");
        TableState tableState = new TableState();
        tableState.addData(
                new String[] {"SampleMedicine", "10", "300", "1", "2", "3", "4", "5", "6", "7", "take safely"});
        tableViewModel.setState(tableState);
        checklistViewModel.setPropertyChangedCalled(false);
        viewManagerModel.setPropertyChangedCalled(false);

        deletePresenter.prepareSuccessView(entry);

        assertTrue(Arrays.stream(tableViewModel.getState().getData()).toList().isEmpty());
        assertTrue(checklistViewModel.isPropertyChangedCalled());
        assertEquals(MainView.viewName, viewManagerModel.getActiveView());
        assertTrue(viewManagerModel.isPropertyChangedCalled());
        assertNotNull(deleteViewModel.getState());
        assertTrue(deleteViewModel.isPropertyChangedCalled());
    }

    @Test
    public void testPrepareFailView() {
        deletePresenter.prepareFailView("Error");

        assertNotNull(deleteViewModel.getState());
        assertTrue(deleteViewModel.isPropertyChangedCalled());
    }

    // Mock ViewModel classes to simulate ViewModel behavior

    private static class DeleteViewModel extends interface_adapter.deleteMedicine.DeleteViewModel {
        private boolean propertyChangedCalled = false;

        @Override
        public void firePropertyChanged() {
            propertyChangedCalled = true;
        }

        public boolean isPropertyChangedCalled() {
            return propertyChangedCalled;
        }
    }

    private static class ChecklistViewModel extends interface_adapter.checklistChecked.ChecklistViewModel {
        private boolean propertyChangedCalled = false;

        @Override
        public void firePropertyChangedRemoveMed(String medicineName) {
            propertyChangedCalled = true;
        }

        public boolean isPropertyChangedCalled() {
            return propertyChangedCalled;
        }

        public void setPropertyChangedCalled(boolean b) {
            propertyChangedCalled = b;
        }
    }

    private static class TableViewModel extends interface_adapter.table.TableViewModel {
        private TableState state = new TableState();

        @Override
        public TableState getState() {
            return state;
        }

        @Override
        public void setState(TableState state) {
            this.state = state;
        }
    }

    private static class ViewManagerModel extends interface_adapter.ViewManagerModel {
        private boolean propertyChangedCalled = false;
        private String activeView;

        @Override
        public void setActiveView(String activeView) {
            this.activeView = activeView;
        }

        @Override
        public String getActiveView() {
            return activeView;
        }

        @Override
        public void firePropertyChanged() {
            propertyChangedCalled = true;
        }

        public boolean isPropertyChangedCalled() {
            return propertyChangedCalled;
        }

        public void setPropertyChangedCalled(boolean b) {
            propertyChangedCalled = b;
        }
    }
}