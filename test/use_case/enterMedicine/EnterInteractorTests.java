
package use_case.enterMedicine;

import static org.junit.Assert.assertEquals;

import data_access.InMemoryDAO;
import data_access.MedicineAPICallsObject;
import org.junit.Test;
import data_access.MedicineDAO;
import data_access.MedicineDataAccessInterface;
import entity.Medicine;
import entity.Today;
import entity.MedicineFactory;
import org.junit.jupiter.api.Assertions;
import use_case.enterMedicine.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;

import static org.junit.jupiter.api.Assertions.*;

class EnterInteractorTests {

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
    }

    @org.junit.jupiter.api.Test
    void successTest() throws IOException {
        MedicineDataAccessInterface userRepository = new InMemoryDAO(new Today(1), new MedicineFactory());
        EnterOutputBoundary successPresenter = new EnterOutputBoundary() {
            @Override
            public void prepareSuccessView(EnterOutputData user) {
// 2 things to check: the output data is correct, and the user has been created in the DAO.
                assertEquals("Oxycontin", user.getMedication());
                assertTrue(userRepository.exists("Oxycontin"));
            }
            @Override
            public void preparePopUp(String error) {fail("Use case failure is unexpected.");}

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
                userRepository, successPresenter, new MedicineFactory(), new MedicineAPICallsObject());
        interactor.execute(inputData); // This will eventually send Output Data to the successPresenter
    }
    @org.junit.jupiter.api.Test
    void successTestDose() throws IOException {
        MedicineDataAccessInterface userRepository = new InMemoryDAO(new Today(1), new MedicineFactory());
        EnterOutputBoundary successPresenter = new EnterOutputBoundary() {
            @Override
            public void prepareSuccessView(EnterOutputData user) {
// 2 things to check: the output data is correct, and the user has been created in the DAO.
                assertEquals("Oxycontin", user.getMedication());
                assertTrue(userRepository.exists("Oxycontin"));
            }
            @Override
            public void preparePopUp(String error) {fail("Use case failure is unexpected.");}

            @Override
            public void updateChecklistView(EnterOutputData user) {
                assertEquals(new String[]{"Oxycontin", "3 mg"}, new String[]{user.getMedication(), user.getDose()});
            }

            @Override
            public void updateLowView(EnterOutputData user) {
                assertEquals(new String[]{"Oxycontin", "100"},
                        new String[]{user.getMedication(), String.valueOf(user.getDosesRemaining())});
            }
        };

        Integer[] myArray = new Integer[]{0,0,0,0,0,0,0};
        EnterInputData inputData = new EnterInputData("Oxycontin", 3, "tablet",
                300, myArray , "Do not get addicted" );
        EnterInputBoundary interactor = new EnterInteractor(
                userRepository, successPresenter, new MedicineFactory() , new MedicineAPICallsObject());
        interactor.execute(inputData); // This will eventually send Output Data to the successPresenter
    }
    @org.junit.jupiter.api.Test
    void failureMedicineExistsTest() throws IOException {
        MedicineDataAccessInterface userRepository = new InMemoryDAO(new Today(1), new MedicineFactory());
        MedicineFactory medicineFactory = new MedicineFactory();
        Integer[] weeklySchedule = new Integer[]{2,1,3,0,0,1,0};
        userRepository.saveMedicine(medicineFactory.createMedicine("Oxycontin",
                2,
                30,
                "ml",
                weeklySchedule,
                "",
                "123"));
        EnterOutputBoundary failurePresenter = new EnterOutputBoundary() {
            @Override
            public void prepareSuccessView(EnterOutputData enterOutputData) {
                fail("Use case should fail when medicine already exists");
            }

            @Override
            public void preparePopUp(String message) {
                assertEquals("Oxycontin already exists as a medication", message);
            }

            @Override
            public void updateChecklistView(EnterOutputData enterOutputData) {
                fail("This view should not be called in case the medication already exists.");
            }

            @Override
            public void updateLowView(EnterOutputData enterOutputData) {
                fail("this view should not be called in case the medication already exists.");
            }
        };

        Integer[] myArray = new Integer[]{0,0,0,0,0,0,0};
        EnterInputData inputData = new EnterInputData("Oxycontin", 3, "tablet",
                300, myArray , "Do not get addicted" );
        EnterInputBoundary interactor = new EnterInteractor(
                userRepository, failurePresenter, new MedicineFactory() , new MedicineAPICallsObject());
        interactor.execute(inputData);
    }
    @org.junit.jupiter.api.Test
    void drugInteractionTest() throws IOException {
        MedicineDataAccessInterface userRepository = new InMemoryDAO(new Today(1), new MedicineFactory());
        MedicineFactory medicineFactory = new MedicineFactory();
        Integer[] weeklySchedule = new Integer[]{2,1,3,0,0,1,0};
        userRepository.saveMedicine(medicineFactory.createMedicine("Oxycontin",
                2,
                30,
                "ml",
                weeklySchedule,
                "",
                "218986"));
        userRepository.saveMedicine(medicineFactory.createMedicine("Tramadol",
                3,
                15,
                "tablet",
                weeklySchedule,
                "take with Oxycontin",
                "10689"));
        EnterOutputBoundary successPresenter = new EnterOutputBoundary() {
            @Override
            public void prepareSuccessView(EnterOutputData enterOutputData) {
                assertEquals("Phenelzine", enterOutputData.getMedication());
            }

            @Override
            public void preparePopUp(String message) {
                assertEquals("Warning - drug interaction detected: Phenelzine may increase the serotonergic" +
                        " and central nervous system depressant (CNS depressant) activities of Oxycodone.\n" +
                                "Warning - drug interaction detected: The risk or severity of serotonin syndrome " +
                                "and seizure can be increased when Phenelzine is combined with Tramadol.\n",
                         message);
            }

            @Override
            public void updateChecklistView(EnterOutputData enterOutputData) {
                assertEquals(new String[]{"Phenelzine", "5 gram"}, new String[]{enterOutputData.getMedication(),
                        enterOutputData.getDose()});
            }

            @Override
            public void updateLowView(EnterOutputData enterOutputData) {
                assertEquals(new String[]{"Phenelzine", "20"}, new String[]{enterOutputData.getMedication(),
                        String.valueOf(enterOutputData.getDosesRemaining())});
            }
        };

        Integer[] myArray = new Integer[]{0,0,0,0,0,0,0};
        EnterInputData inputData = new EnterInputData("Phenelzine",
                5,
                "gram",
                100,
                myArray,
                "I will take this with Oxycontin");
        EnterInputBoundary interactor = new EnterInteractor(userRepository,
                successPresenter,
                medicineFactory,
                new MedicineAPICallsObject());
        interactor.execute(inputData);
    }
    @org.junit.jupiter.api.Test
    void noIdZeroDoseTest() throws IOException {
        MedicineDataAccessInterface userRepository = new InMemoryDAO(new Today(1), new MedicineFactory());
        EnterOutputBoundary successPresenter = new EnterOutputBoundary() {
            @Override
            public void prepareSuccessView(EnterOutputData user) {fail("Medicine not supposed to be successfully entered.");}
            @Override
            public void preparePopUp(String error) {
                ArrayList<String> messages = new ArrayList<String>();
                messages.add("jam could not be found in the database. The drug will still be" +
                        " entered but drug interactions with jam cannot be confirmed.");
                messages.add("Cannot enter medicine with a dose size of 0.");
                assertTrue(messages.contains(error));
            }

            @Override
            public void updateChecklistView(EnterOutputData user) {fail("Medicine not supposed to be successfully entered.");}

            @Override
            public void updateLowView(EnterOutputData user) {fail("Medicine not supposed to be successfully entered.");}
        };

        Integer[] myArray = new Integer[]{0, 1, 2, 3,4,5,6,7};
        EnterInputData inputData = new EnterInputData("jam", 0, "can",
                300, myArray , "Do not get addicted" );
        EnterInputBoundary interactor = new EnterInteractor(
                userRepository, successPresenter, new MedicineFactory(), new MedicineAPICallsObject());
        interactor.execute(inputData); // This will eventually send Output Data to the successPresenter
    }
}