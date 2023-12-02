package data_access;

import entity.Medicine;
import entity.MedicineFactory;
import entity.Today;

import java.time.LocalDate;
import java.util.HashMap;

/**
 * A data access object for manipulating data stored in entities in memory.
 */
public class InMemoryDAO implements MedicineDataAccessInterface {
    private HashMap<String, Medicine> userMedicine = new HashMap<>();
    private Today today;
    private final MedicineFactory medicineFactory;

    /**
     * Constructs an InMemoryDAO data access object.
     * @param today, an entity representing the current day.
     * @param medicineFactory, a factory to create Medicine and Dose entities for storing data in memory.
     */
    public InMemoryDAO(Today today, MedicineFactory medicineFactory) {
        this.today = today;
        this.medicineFactory = medicineFactory;
    }

    /**
     * Checks if a specific medicine exists in the DAO.
     * @param name, the name of the medicine.
     * @return true if the DAO contains the medicine, false otherwise.
     */
    @Override
    public boolean exists(String name) {
        return userMedicine.containsKey(name);
    }

    /**
     * Saves the medicine in the DAO.
     * @param medicine, the Medicine entity to be stored.
     */
    @Override
    public void saveMedicine(Medicine medicine) {
        userMedicine.put(medicine.getName(), medicine);
        if (medicine.getWeeklySchedule()[today.getDay()] != 0) {
            today.add(medicine.getName(), 0);
        }
    }

    /**
     * Deletes the medicine in the DAO.
     * @param medicine, the name of the medicine to be deleted.
     */
    @Override
    public void removeMedicine(String medicine) {
        userMedicine.remove(medicine);
        today.remove(medicine);
    }

    /**
     * Gets the current day.
     * @return an integer representing the current day.
     */
    @Override
    public Integer getTodayDay() {
        return today.getDay();
    }

    /**
     * Gets the checklist from the Today entity.
     * @return a hashmap containing information pertaining to the number of doses taken per medicine on the current day.
     */
    @Override
    public HashMap<String, Integer> getTodayChecklist() {return today.getTodayChecklist();}

    /**
     * Gets the medicines stored in the DAO.
     * @return a hashmap containing all medicines stored in the DAO.
     */
    @Override
    public HashMap<String, Medicine> getUserMedicines() {
        return userMedicine;
    }

    /**
     * Takes one dose of the medicine and updates the entity accordingly.
     * @param medicine, the name of the medicine taken.
     */
    @Override
    public void takeMedicine(String medicine) {
        userMedicine.get(medicine).getDose().takeDose();
        today.take(medicine);
    }

    /**
     * Reverts the taking of one dose of the medicine and updates the entity accordingly.
     * @param medicine, the name of the medicine taken.
     */
    @Override
    public void undoTakeMedicine(String medicine) {
        userMedicine.get(medicine).getDose().undoTakeDose();
        today.untake(medicine);
    }

    /**
     * Gets all medicine IDs for API processing.
     * @return a string of all medicine IDs formatted for an API endpoint.
     */
    @Override
    public String getIdListString() {
        StringBuilder stringBuilder = new StringBuilder();
        if (userMedicine.values().isEmpty()) {return "";}
        else {
            for (Medicine medicine:userMedicine.values()) {
                stringBuilder.append(medicine.getId()).append("+");
            } stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            return stringBuilder.toString();
        }
    }

    /**
     * Gets an InMemoryDAO for the current day.
     * @param localDate, the current day.
     * @return an InMemoryDAO for the current day.
     */
    public static InMemoryDAO getInMemoryDAO(LocalDate localDate) {
        String day = localDate.getDayOfWeek().name();
        Integer dayInt = null;
        switch (day) {
            case "SUNDAY" -> dayInt = 0;
            case "MONDAY" -> dayInt = 1;
            case "TUESDAY" -> dayInt = 2;
            case "WEDNESDAY" -> dayInt = 3;
            case "THURSDAY" -> dayInt = 4;
            case "FRIDAY" -> dayInt = 5;
            case "SATURDAY" -> dayInt = 6;
        }
        return new InMemoryDAO(new Today(dayInt), new MedicineFactory());
    }
}
