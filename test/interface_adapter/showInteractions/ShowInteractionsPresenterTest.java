package interface_adapter.showInteractions;

import interface_adapter.showInteractions.ShowInteractionsPresenter;
import interface_adapter.table.TableState;
import interface_adapter.table.TableViewModel;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ShowInteractionsPresenterTest {

    @Test
    public void testPreparePopUp() {
        TableViewModel tableViewModel = new TableViewModel();

        // Create the presenter with the actual TableViewModel
        ShowInteractionsPresenter presenter = new ShowInteractionsPresenter(tableViewModel);

        // Message to be used in the test
        String message = "Test message";

        // Call the method under test
        presenter.preparePopUp(message);

        // Validate the interactions and states
        assertEquals(message, tableViewModel.getState().getMessage());
    }
}