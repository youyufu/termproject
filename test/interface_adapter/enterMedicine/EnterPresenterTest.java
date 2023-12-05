package interface_adapter.enterMedicine;

import interface_adapter.checklistChecked.ChecklistViewModel;
import use_case.enterMedicine.EnterOutputData;
import interface_adapter.enterMedicine.EnterPresenter;
import interface_adapter.enterMedicine.EnterViewModel;
import interface_adapter.table.TableState;
import interface_adapter.table.TableViewModel;
import interface_adapter.ViewManagerModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import view.MainView;

import java.util.Arrays;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

public class EnterPresenterTest {

    private EnterViewModelMock enterViewModel;
    private ChecklistViewModelMock checklistViewModel;
    private TableViewModelMock tableViewModel;
    private ViewManagerModelMock viewManagerModel;
    private EnterPresenter enterPresenter;

    @BeforeEach
    public void setUp() {
        enterViewModel = new EnterViewModelMock();
        checklistViewModel = new ChecklistViewModelMock();
        tableViewModel = new TableViewModelMock();
        viewManagerModel = new ViewManagerModelMock();
        enterPresenter = new EnterPresenter(enterViewModel, checklistViewModel, tableViewModel, viewManagerModel);
    }

    @Test
    public void testPrepareSuccessView() {
        EnterOutputData entryData = new EnterOutputData(
                "MedicineA", "5mg", "10",
                1, 1, 1, 1, 1, 1, 1, "Description",2);
        TableState tableState = new TableState();
        tableViewModel.setState(tableState);
        checklistViewModel.setPropertyChangedCalled(false);
        viewManagerModel.setPropertyChangedCalled(false);

        enterPresenter.prepareSuccessView(entryData);

        assertArrayEquals(tableViewModel.getState().getData(), new String[][]{new String[]{"MedicineA", "5mg", "10",
                "1", "1", "1", "1", "1", "1", "1", "Description"}});
        assertEquals(MainView.viewName, viewManagerModel.getActiveView());
        assertTrue(viewManagerModel.isPropertyChangedCalled());
        assertNotNull(enterViewModel.getState());
        assertTrue(enterViewModel.isPropertyChangedCalled());
    }

    @Test
    public void testUpdateChecklistView() {
        EnterOutputData entryData = new EnterOutputData("MedicineA", "5mg",
                "10", 1, 1, 1, 1, 1, 1, 1, "Description", 3);

        enterPresenter.updateChecklistView(entryData);

        assertTrue(checklistViewModel.isPropertyChangedAddTakeCalled());
    }

    @Test
    public void testUpdateLowView_WithRemainingDoses() {
        EnterOutputData entryData = new EnterOutputData("MedicineA", "5mg",
                "10", 2, 1, 1, 1, 1, 1, 1, "Description", 2);

        enterPresenter.updateLowView(entryData);

        assertTrue(checklistViewModel.isPropertyChangedAddLowCalled());
        assertFalse(checklistViewModel.isAddRestockCalled());
    }

    @Test
    public void testUpdateLowView_NoRemainingDoses() {
        EnterOutputData entryData = new EnterOutputData("MedicineA", "5mg",
                "10", 2, 1, 1, 1, 1, 1, 1, "Description", 0);

        enterPresenter.updateLowView(entryData);

        assertTrue(checklistViewModel.isPropertyChangedAddLowCalled());
        assertTrue(checklistViewModel.isAddRestockCalled());
    }

    @Test
    public void testPreparePopUp() {
        String message = "Low inventory alert";

        enterPresenter.preparePopUp(message);

        assertNotNull(enterViewModel.getState());
        assertTrue(enterViewModel.isPropertyChangedCalled());
    }

    // Simulated ViewModel classes to test EnterPresenter

    private static class EnterViewModelMock extends EnterViewModel {
        private boolean propertyChangedCalled = false;

        @Override
        public void firePropertyChanged() {
            propertyChangedCalled = true;
        }

        public boolean isPropertyChangedCalled() {
            return propertyChangedCalled;
        }
    }

    private static class ChecklistViewModelMock extends ChecklistViewModel {
        private boolean propertyChangedCalled = false;
        private boolean propertyChangedAddTakeCalled = false;
        private boolean propertyChangedAddLowCalled = false;
        private boolean addRestockCalled = false;

        @Override
        public void firePropertyChanged() {
            propertyChangedCalled = true;
        }

        @Override
        public void firePropertyChangedAddTake(String[] item) {
            propertyChangedAddTakeCalled = true;
        }

        @Override
        public void firePropertyChangedAddLow(String[] item) {
            propertyChangedAddLowCalled = true;
        }

        public boolean isPropertyChangedCalled() {
            return propertyChangedCalled;
        }

        public boolean isPropertyChangedAddTakeCalled() {
            return propertyChangedAddTakeCalled;
        }

        public boolean isPropertyChangedAddLowCalled() {
            return propertyChangedAddLowCalled;
        }

        public boolean isAddRestockCalled() {
            return addRestockCalled;
        }
        @Override
        public void addRestock(String medicine) {
            addRestockCalled = true;
        }

        public void setPropertyChangedCalled(boolean b) {
            propertyChangedCalled = b;
        }
    }

    private static class TableViewModelMock extends TableViewModel {
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

    private static class ViewManagerModelMock extends ViewManagerModel {
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