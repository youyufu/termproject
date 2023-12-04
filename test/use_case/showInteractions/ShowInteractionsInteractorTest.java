package use_case.showInteractions;

import data_access.InMemoryDAO;
import data_access.MedicineAPICallsObject;
import data_access.MedicineDataAccessInterface;
import entity.MedicineFactory;
import entity.Today;
import use_case.enterMedicine.EnterOutputBoundary;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class ShowInteractionsInteractorTest {

    @org.junit.jupiter.api.BeforeEach
    void setUp() {}

    @org.junit.jupiter.api.Test
    void noInteractionsTest() throws IOException {
        MedicineDataAccessInterface userRepository = new InMemoryDAO(new Today(1), new MedicineFactory());
        ShowInteractionsOutputBoundary presenter = new ShowInteractionsOutputBoundary() {
            @Override
            public void preparePopUp(String message) {
                assertEquals("No drug interaction found between the existing medicines.\n" +
                        " Please note that some medicine might not have been found in the database.", message);
            }
        };
        ShowInteractionsInteractor interactionsInteractor = new ShowInteractionsInteractor(userRepository,
                presenter,
                new MedicineAPICallsObject());
        interactionsInteractor.execute();
    }
}
