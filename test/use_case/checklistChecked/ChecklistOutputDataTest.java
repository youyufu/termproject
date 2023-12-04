package use_case.checklistChecked;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ChecklistOutputDataTest {
    @Test
    void execute() {
        ChecklistOutputData checklistOutputData = new ChecklistOutputData("advil","2 pill","0");
        assertEquals("advil", checklistOutputData.getName());
        assertEquals("2 pill", checklistOutputData.getInventory());
        assertEquals("0", checklistOutputData.getDosesLeft());
    }
}
