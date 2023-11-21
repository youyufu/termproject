package interface_adapter.checklistChecked;

import java.util.ArrayList;
import java.util.HashMap;

public class ChecklistState {
    private ArrayList<String[]> takeToday = new ArrayList<>();
    // takeToday stored in string array of {medicineName, doseString, bool}
    private HashMap<String, Integer> low = new HashMap<>();
    // low stored in HashMap of {medicineName: doseRemaining}
    private ArrayList<String> restock = new ArrayList<>();
    public ChecklistState(ChecklistState copy) {
        this.takeToday = copy.takeToday;
        this.low = copy.low;
        this.restock = copy.restock;
    }
    public ChecklistState() {}
    public ArrayList<String[]> getTakeToday() {return this.takeToday;}
    public void addTakeToday(String[] take) {this.takeToday.add(take);}
    public void removeTakeToday(String name) {
        ArrayList<String[]> newTakeToday = new ArrayList<>();
        for (String[] array:takeToday) {
            if (array[0].equals(name)) {
                continue;
            } else {
                newTakeToday.add(array);
            }
        } this.takeToday = newTakeToday;
    }

    public HashMap<String, Integer> getLow() {return low;}
    public void addLow(String[] low) {
        this.low.put(low[0], Integer.valueOf(low[1]));
        if (Integer.parseInt(low[1]) == 0) {
            this.restock.add(low[0]);
        }
    }
    public void removeLow(String name) {
        this.low.remove(name);
        this.restock.remove(name);
    }
    public ArrayList<String> getRestock() {return restock;}
}
