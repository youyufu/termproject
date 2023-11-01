package interface_adapter;

import java.util.ArrayList;
import java.util.Arrays;

public class TableState {
    private final String[] columnHeaders = {"Medication", "Dose Size", "Inventory", "Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Additional Info"};
    private ArrayList<String[]> data = new ArrayList<>();
    public TableState(TableState copy) {
        this.data = copy.data;
    }
    public TableState() {};

    public String[][] getData() {
        String[][] dataArray = new String[data.size()][];
        for (int i = 0; i < data.size(); i++) {
            String[] array = data.get(i);
            dataArray[i] = array;
        } return dataArray;
    }

    public void addData(String[] data) {this.data.add(data);}
    public void removeData(String name) {
        int i = 0;
        while (i < data.size() && !data.get(i)[0].equals(name)) {
            i++;
        } data.remove(i);
    }

    public String[] getColumnHeaders() {return columnHeaders;}
}
