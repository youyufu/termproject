package data_access;

import entity.Dose;
import entity.Medicine;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class MedicineDAOTest {
    MedicineDAO medicineDAO;
    @Test
    void testDAO() {
        medicineDAO = MedicineDAO.getMedicineDAO(LocalDate.of(2023, 12, 1), "./test.json");
        Medicine medicine = new Medicine("advil", new Dose(2, 20, "mg"), new Integer[]{2, 2, 2, 2, 2, 2, 2}, "", "153010");
        assertEquals(5, medicineDAO.getTodayDay());
        medicineDAO.saveMedicine(medicine);
        assertTrue(medicineDAO.exists("advil"));
        medicineDAO.takeMedicine("advil");
        assertEquals(9, medicine.getDose().getDosesRemaining());
        assertEquals("153010", medicineDAO.getIdListString());
        assertFalse(medicineDAO.getTodayChecklist().isEmpty());
        assertFalse(medicineDAO.getUserMedicines().isEmpty());
        medicineDAO.undoTakeMedicine("advil");
        assertEquals(10, medicine.getDose().getDosesRemaining());
        medicineDAO.takeMedicine("advil");
    }

    @Test
    void testDataPersistence() {
        medicineDAO = MedicineDAO.getMedicineDAO(LocalDate.of(2023, 12, 1), "./test.json");
        assertEquals(5, medicineDAO.getTodayDay());
        assertTrue(medicineDAO.exists("advil"));
        assertEquals(9, medicineDAO.getUserMedicines().get("advil").getDose().getDosesRemaining());
        assertEquals("153010", medicineDAO.getIdListString());
        assertTrue(medicineDAO.getTodayChecklist().containsKey("advil"));
        assertEquals(1, medicineDAO.getTodayChecklist().get("advil"));
    }

    @Test
    void testNewDay() {
        medicineDAO = MedicineDAO.getMedicineDAO(LocalDate.of(2023, 12, 2), "./test.json");
        assertEquals(6, medicineDAO.getTodayDay());
        assertEquals(0, medicineDAO.getTodayChecklist().get("advil"));
        medicineDAO.removeMedicine("advil");
        assertFalse(medicineDAO.exists("advil"));
    }
}