package entity;

import entity.Dose;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DoseTest {

    @Test
    public void testDoseInitialization() {
        Dose dose = new Dose(10, 50, "mg");

        assertEquals(10, dose.getSize());
        assertEquals("mg", dose.getUnit());
        assertEquals(50, dose.getInventory());
        assertEquals(5, dose.getDosesRemaining());
    }

    @Test
    public void testTakeDose() {
        Dose dose = new Dose(5, 20, "ml");

        assertEquals(20, dose.getInventory());
        assertEquals(4, dose.getDosesRemaining());

        dose.takeDose();

        assertEquals(15, dose.getInventory());
        assertEquals(3, dose.getDosesRemaining());
    }

    @Test
    public void testUndoTakeDose() {
        Dose dose = new Dose(5, 20, "ml");

        assertEquals(20, dose.getInventory());
        assertEquals(4, dose.getDosesRemaining());

        dose.takeDose();

        assertEquals(15, dose.getInventory());
        assertEquals(3, dose.getDosesRemaining());

        dose.undoTakeDose();

        assertEquals(20, dose.getInventory());
        assertEquals(4, dose.getDosesRemaining());
    }

    // Add more test cases based on your requirements
}
