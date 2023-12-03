package data_access;

import entity.Medicine;

import java.util.HashMap;

/**
 * An interface for access to the data persistence layer.
 */
public interface MedicineDataAccessInterface {

    /**
     * Checks if a specific medicine exists.
     *      * @param name, the name of the medicine.
     *      * @return true if the data contains the medicine, false otherwise.
     */
    boolean exists(String name);

    /**
     * Saves the medicine.
     * @param medicine, the Medicine entity to be stored.
     */
    void saveMedicine(Medicine medicine);

    /**
     * Deletes the medicine.
     * @param medicine, the name of the medicine to be deleted.
     */
    void removeMedicine(String medicine);

    /**
     * Gets the current day.
     * @return an integer representing the current day.
     */
    Integer getTodayDay();

    /**
     * Gets the checklist for the current day.
     * @return a hashmap containing information pertaining to the number of doses taken per medicine on the current day.
     */
    HashMap<String, Integer> getTodayChecklist();

    /**
     * Gets the medicines stored.
     * @return a hashmap containing all medicines stored.
     */
    HashMap<String, Medicine> getUserMedicines();

    /**
     * Takes one dose of the medicine.
     * @param medicine, the name of the medicine taken.
     */
    void takeMedicine(String medicine);

    /**
     * Reverts the taking of one dose of the medicine.
     * @param medicine, the name of the medicine taken.
     */
    void undoTakeMedicine(String medicine);

    /**
     * Gets all medicine IDs.
     * @return a string of all medicine IDs formatted for an API endpoint.
     */
    String getIdListString();
}
