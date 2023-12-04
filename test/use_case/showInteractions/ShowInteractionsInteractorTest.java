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
    @org.junit.jupiter.api.Test
    void interactionsTest() throws IOException {
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
        userRepository.saveMedicine(medicineFactory.createMedicine("Phenelzine",
                5,
                100,
                "gram",
                new Integer[]{0,0,0,0,0,0,0},
                "I will take this with Oxycontin",
                "8123"));
        ShowInteractionsOutputBoundary interactionsOutputBoundary = new ShowInteractionsOutputBoundary() {
            @Override
            public void preparePopUp(String message) {
                assertEquals("Warning - drug interaction detected: Phenelzine may increase the serotonergic" +
                        " and central nervous system depressant (CNS depressant) activities of Oxycodone.\n" +
                        "Warning - drug interaction detected: The risk or severity of serotonin syndrome can be " +
                        "increased when Oxycodone is combined with Tramadol.\n" +
                        "Warning - drug interaction detected: The risk or severity of serotonin syndrome and seizure" +
                        " can be increased when Phenelzine is combined with Tramadol.\n", message);
            }
        };
        ShowInteractionsInteractor interactionsInteractor = new ShowInteractionsInteractor(userRepository,
                interactionsOutputBoundary,
                new MedicineAPICallsObject());
        interactionsInteractor.execute();
    }
}
