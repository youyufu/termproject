package data_access;

import java.io.IOException;
import java.util.ArrayList;

public interface MedicineAPICallsInterface {
    /**
     * An interface for the API calls needed for the program.
     */

    /**
     * Finds the RxCUI associated with the entered name. Assigns DEFAULT_ID if it is not found.
     * @return the RxCUI associated with the entered name, DEFAULT_ID if it is not found.
     */
    String findId(String name);

    /**
     * Finds the drug interactions between the id of the medicine entered and all existing medicines.
     * @param medicineDataAccessObject the data access object to find all existing medicines.
     * @param id the id of the medicine to search for.
     * @return the drug interactions that exists between the medicine associated with the id and the existing medicines.
     * @throws IOException if there is an issue with the API calls.
     * @throws InterruptedException if there is an issue with the API calls.
     */
    ArrayList<String> findDrugInteractions(MedicineDataAccessInterface medicineDataAccessObject, String id) throws IOException, InterruptedException;
}
