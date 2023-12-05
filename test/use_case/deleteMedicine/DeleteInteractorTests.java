package use_case.deleteMedicine;

import data_access.*;
import entity.Dose;
import entity.Medicine;
import entity.MedicineFactory;
import entity.Today;
import org.junit.jupiter.api.Assertions;
import use_case.deleteMedicine.*;
import use_case.enterMedicine.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

public class DeleteInteractorTests {
    InMemoryDAO userRepository = new InMemoryDAO(new Today(1), new MedicineFactory());
    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        Medicine medicine = new Medicine("Oxycontin", new Dose(2, 10, "mg"), new Integer[]{1, 1, 1, 1, 1, 1, 1}, "", "");
        userRepository.saveMedicine(medicine);
    }

    @org.junit.jupiter.api.Test
    void successTest() throws IOException {
        DeleteOutputBoundary successPresenter = new DeleteOutputBoundary() {
            @Override
            public void prepareSuccessView(DeleteOutputData user) {
// 2 things to check: the output data is correct, and the user has been created in the DAO.
                Assertions.assertEquals("Oxycontin", user.getMedication());
                assertFalse(userRepository.exists("Oxycontin"));
            }
            @Override
            public void prepareFailView(String error) {;}

        };
        DeleteInputData inputData = new DeleteInputData("Oxycontin");
        DeleteInputBoundary interactor = new DeleteInteractor(
                userRepository, successPresenter);
        interactor.execute(inputData); // This will eventually send Output Data to the successPresenter
    }
    @org.junit.jupiter.api.Test
    void failTest() throws IOException {
        DeleteOutputBoundary successPresenter = new DeleteOutputBoundary() {
            @Override
            public void prepareSuccessView(DeleteOutputData user) {
            }
            @Override
            public void prepareFailView(String error) {
                Assertions.assertEquals("Ritalin does not exist.", error);
            }

        };
        DeleteInputData inputData = new DeleteInputData("Ritalin");
        DeleteInputBoundary interactor = new DeleteInteractor(
                userRepository, successPresenter);
        interactor.execute(inputData); // This will eventually send Output Data to the successPresenter
    }
}

