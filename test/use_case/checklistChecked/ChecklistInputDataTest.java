package use_case.checklistChecked;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ChecklistInputDataTest {
    @Test
    void execute() {
        ChecklistInputData checklistInputData = new ChecklistInputData("advil",2);
        assertEquals("advil", checklistInputData.getName());
        assertEquals(2, checklistInputData.getChange());
    }
}
