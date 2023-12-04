package entity;

import entity.Medicine;
import entity.Dose;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MedicineTest {

    @Test
    public void testMedicineInitialization() {
        Integer[] weeklySchedule = {1, 2, 3, 4, 5};
        Dose dose = new Dose(10, 300, "mg");
        Medicine medicine = new Medicine("Paracetamol", dose, weeklySchedule, "Painkiller", "12345");

        assertEquals("Paracetamol", medicine.getName());
        assertArrayEquals(weeklySchedule, medicine.getWeeklySchedule());
        assertEquals("10 mg", medicine.getDoseString());
        assertEquals("300 mg", medicine.getInventoryString());
        assertEquals("Painkiller", medicine.getDescription());
        assertEquals("12345", medicine.getId());
    }

    @Test
    public void testGetters() {
        Integer[] weeklySchedule = {1, 2, 3, 4, 5};
        Dose dose = new Dose(5, 300, "ml");
        Medicine medicine = new Medicine("Medicine", dose, weeklySchedule, "Description", "54321");

        assertEquals("Medicine", medicine.getName());
        assertArrayEquals(weeklySchedule, medicine.getWeeklySchedule());
        assertEquals("5 ml", medicine.getDoseString());
        assertEquals("300 ml", medicine.getInventoryString());
        assertEquals("Description", medicine.getDescription());
        assertEquals("54321", medicine.getId());
    }

    // Add more test cases based on your requirements
}