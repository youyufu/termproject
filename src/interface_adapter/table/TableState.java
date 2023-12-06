package interface_adapter.table;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class TableState {
    /**
     * TableState is a class that holds the state of a table view.
     */

    private ArrayList<String[]> data = new ArrayList<>();
    private ArrayList<ArrayList<String>> longest = new ArrayList<>();
    private String message = "";

    /**
     * Constructs a new instance of TableState.
     */
    public TableState() {
        String[] headers = {"Medication", "Dose", "Inventory", "Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Additional Info"};
        for (String header: headers) {
            ArrayList<String> list = new ArrayList<>();
            list.add(header);
            longest.add(list);
        }
    }

    /**
     * Sets a message related to table.
     *
     * @param message The message to be set.
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Get the current message set for table.
     *
     * @return The message for table.
     */
    public String getMessage() {
        return message;
    }

    /**
     * Get the current data in table.
     *
     * @return An array of the table data.
     */
    public String[][] getData() {
        String[][] dataArray = new String[data.size()][];
        for (int i = 0; i < data.size(); i++) {
            String[] array = data.get(i);
            dataArray[i] = array;
        } return dataArray;
    }

    /**
     * Adds a row of data to the table.
     *
     * @param data An array of data to be added.
     */
    public void addData(String[] data) {
        this.data.add(data);
        for (int i = 0; i < data.length; i++) {
            longest.get(i).add(data[i]);
        }
    }

    /**
     * Edits the inventory for a medication in table.
     *
     * @param name The name of the medicine.
     * @param inventory The new inventory of the medicine.
     */
    public void editInventory(String name, String inventory) {
        int i = 0;
        while (i < data.size() && !data.get(i)[0].equals(name)) {
            i++;
        } data.get(i)[2] = inventory;
    }

    /**
     * Remove data from table.
     *
     * @param name the name of the medicine.
     */
    public void removeData(String name) {
        int i = 0;
        while (i < data.size() && !data.get(i)[0].equals(name)) {
            i++;
        } data.remove(i);
        for (ArrayList<String> list: longest) {
            list.remove(i + 1);
        }
    }

    /**
     * Get the longest string.
     *
     * @return An array of the longest string.
     */
    public String[] getLongValues() {
        String[] longValues = new String[longest.size()];
        for (int i = 0; i < longest.size(); i++) {
            longValues[i] = (Collections.max(longest.get(i), Comparator.comparing(String::length)));
        } return longValues;
    }
}

