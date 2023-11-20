import data_access.InMemoryDAO;
import data_access.MedicineDAO;
import data_access.MedicineDataAccessInterface;
import entity.MedicineFactory;
import entity.Today;
import org.junit.jupiter.api.Assertions;
import use_case.deleteMedicine.*;
import use_case.enterMedicine.*;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

public class DeleteInteractorTests {
    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        InMemoryDAO userRepository = new InMemoryDAO(new Today(1),
                new MedicineFactory());
        EnterOutputBoundary successPresenter = new EnterOutputBoundary() {
            @Override
            public void prepareSuccessView(EnterOutputData user) {
// 2 things to check: the output data is correct, and the user has been created in the DAO.
                assertEquals("Oxycontin", user.getMedication());
                assertTrue(userRepository.exists("Oxycontin"));
            }
            @Override
            public void prepareFailView(String error) {fail("Use case failure is unexpected.");}

            @Override
            public void updateChecklistView(EnterOutputData user) {
                assertEquals(new String[]{"Oxycontin", "3 mg"}, new String[]{user.getMedication(), user.getDose()});
            }

            @Override
            public void updateLowView(EnterOutputData user) {
                assertEquals(new String[]{"Oxycontin", "10"},
                        new String[]{user.getMedication(), String.valueOf(user.getDosesRemaining())});
            }
        };
        Integer[] myArray = new Integer[]{0, 1, 2, 3,4,5,6,7};
        EnterInputData inputData = new EnterInputData("Oxycontin", 3, "mg",
                300, myArray , "Do not get addicted" );
        EnterInputBoundary interactor = new EnterInteractor(
                userRepository, successPresenter, new MedicineFactory());
        interactor.execute(inputData);
    }

    @org.junit.jupiter.api.Test
    void successTest() throws IOException {
        InMemoryDAO userRepository = new InMemoryDAO(new Today(1),
                new MedicineFactory());
        DeleteOutputBoundary successPresenter = new DeleteOutputBoundary() {
            @Override
            public void prepareSuccessView(DeleteOutputData user) {
// 2 things to check: the output data is correct, and the user has been created in the DAO.
                Assertions.assertEquals("Oxycontin", user.getMedication());
                assertFalse(userRepository.exists("Oxycontin"));
            }
            @Override
            public void prepareFailView(String error) {fail("Use case failure is unexpected.");}

        };

        Integer[] myArray = new Integer[]{0, 1, 2, 3,4,5,6,7};
        DeleteInputData inputData = new DeleteInputData("Oxycontin");
        DeleteInputBoundary interactor = new DeleteInteractor(
                userRepository, successPresenter);
        interactor.execute(inputData); // This will eventually send Output Data to the successPresenter
    }
}

