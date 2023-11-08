package interface_adapter.checklistChecked;

import java.util.ArrayList;

public class ChecklistState {
    private ArrayList<String[]> takeToday = new ArrayList<>();
    // takeToday stored in string array of {medicineName, doseString}
    private ArrayList<String[]> low = new ArrayList<>();
    // low stored in string array of {medicineName, doseRemaining}
    public ChecklistState(ChecklistState copy) {
        this.takeToday = copy.takeToday;
        this.low = copy.low;
    }
    public ChecklistState() {}
    public ArrayList<String[]> getTakeToday() {return this.takeToday;}
    public void addTakeToday(String[] take) {this.takeToday.add(take);}
    public void removeTakeToday(String name) {
        int i = 0;
        while (i < takeToday.size() && !takeToday.get(i)[0].equals(name)) {
            i++;
        } takeToday.remove(i);
    }

    public ArrayList<String[]> getLow() {return low;}
    public void addLow(String[] low) {this.low.add(low);}
    public void removeLow(String name) {
        int i = 0;
        while (i < low.size() && !low.get(i)[0].equals(name)) {
            i++;
        } low.remove(i);
    }
}
