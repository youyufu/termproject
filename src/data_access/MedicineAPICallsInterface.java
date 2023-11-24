package data_access;

import java.io.IOException;
import java.util.ArrayList;

public interface MedicineAPICallsInterface {
    String findId(String name);
    ArrayList<String> findDrugInteractions(MedicineDataAccessInterface medicineDataAccessObject, String id) throws IOException, InterruptedException;
}
