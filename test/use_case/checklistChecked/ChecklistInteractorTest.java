package use_case.checklistChecked;

import data_access.InMemoryDAO;
import data_access.MedicineDataAccessInterface;
import entity.Dose;
import entity.Medicine;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;

public class ChecklistInteractorTest {
    @Test
    void execute() {
        MedicineDataAccessInterface inMemoryDao = InMemoryDAO.getInMemoryDAO(LocalDate.of(2023, 12, 1));
        Medicine medicine = new Medicine("advil", new Dose(2,2,"pill"), new Integer[]{1,1,1,1,1,1,1} , "advil", "123");
        inMemoryDao.saveMedicine(medicine);
        ChecklistOutputBoundary checklistOutputBoundary = new ChecklistOutputBoundary() {
            @Override
            public void take(ChecklistOutputData checklistOutputData) {
                Assertions.assertEquals("advil", checklistOutputData.getName());
                Assertions.assertEquals("1", checklistOutputData.getDosesLeft());
                Assertions.assertEquals("2 pill", checklistOutputData.getInventory());
            }
            @Override
            public void untake(ChecklistOutputData checklistOutputData) {
                Assertions.assertEquals("advil", checklistOutputData.getName());
                Assertions.assertEquals("2", checklistOutputData.getDosesLeft());
                Assertions.assertEquals("4 pill", checklistOutputData.getInventory());
            }
        };
        ChecklistInteractor checklistInteractor = new ChecklistInteractor(checklistOutputBoundary,inMemoryDao);
        ChecklistInputData checklistInputData = new ChecklistInputData("advil",1);
        checklistInteractor.execute(checklistInputData);
        ChecklistInputData checklistInputData2 = new ChecklistInputData("advil",-1);
        checklistInteractor.execute(checklistInputData2);
    }
}
