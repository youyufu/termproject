package interface_adapter.table;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class TableState {
    private ArrayList<String[]> data = new ArrayList<>();
    private ArrayList<ArrayList<String>> longest = new ArrayList<>();
    private String message = "";
    public TableState() {
        String[] headers = {"Medication", "Dose", "Inventory", "Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Additional Info"};
        for (String header: headers) {
            ArrayList<String> list = new ArrayList<>();
            list.add(header);
            longest.add(list);
        }
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public String getMessage() {
        return message;
    }
    public String[][] getData() {
        String[][] dataArray = new String[data.size()][];
        for (int i = 0; i < data.size(); i++) {
            String[] array = data.get(i);
            dataArray[i] = array;
        } return dataArray;
    }
    public void addData(String[] data) {
        this.data.add(data);
        for (int i = 0; i < data.length; i++) {
            longest.get(i).add(data[i]);
        }
    }
    // data in the form of {medication, dose, inventory, Su, M, Tu, W, Th, F, Sa, Description}
    public void editInventory(String name, String inventory) {
        int i = 0;
        while (i < data.size() && !data.get(i)[0].equals(name)) {
            i++;
        } data.get(i)[2] = inventory;
    }
    public void removeData(String name) {
        int i = 0;
        while (i < data.size() && !data.get(i)[0].equals(name)) {
            i++;
        } data.remove(i);
        for (ArrayList<String> list: longest) {
            list.remove(i + 1);
        }
    }
    public String[] getLongValues() {
        String[] longValues = new String[longest.size()];
        for (int i = 0; i < longest.size(); i++) {
            longValues[i] = (Collections.max(longest.get(i), Comparator.comparing(String::length)));
        } return longValues;
    }
}

