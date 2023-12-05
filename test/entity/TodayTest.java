package entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import static org.junit.jupiter.api.Assertions.*;

public class TodayTest {

    private Today today;

    @BeforeEach
    public void setUp() {
        today = new Today(1); // Initialize with day 1 for testing
    }

    @Test
    public void testAddMedicineToChecklist() {
        today.add("MedicineA", 0);
        HashMap<String, Integer> expectedChecklist = new HashMap<>();
        expectedChecklist.put("MedicineA", 0);

        assertEquals(expectedChecklist, today.getTodayChecklist());
    }

    @Test
    public void testTakeMedicine() {
        today.add("MedicineB", 0);
        today.take("MedicineB");

        HashMap<String, Integer> expectedChecklist = new HashMap<>();
        expectedChecklist.put("MedicineB", 1);

        assertEquals(expectedChecklist, today.getTodayChecklist());
    }

    @Test
    public void testUntakeMedicine() {
        today.add("MedicineC", 2);
        today.untake("MedicineC");

        HashMap<String, Integer> expectedChecklist = new HashMap<>();
        expectedChecklist.put("MedicineC", 1);

        assertEquals(expectedChecklist, today.getTodayChecklist());
    }

    @Test
    public void testRemoveMedicine() {
        today.add("MedicineD", 3);
        today.remove("MedicineD");

        assertFalse(today.getTodayChecklist().containsKey("MedicineD"));
    }

    @Test
    public void testGetDay() {
        assertEquals(1, today.getDay());
    }

    // Add more test cases based on your requirements
}