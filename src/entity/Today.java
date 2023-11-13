package entity;

import java.util.HashMap;

public class Today {

    private HashMap<Medicine, Boolean> todayChecklist;
    private Integer day;

    public Today(Integer day, HashMap<Medicine, Boolean> todayChecklist) {
        this.day = day;
        this.todayChecklist = todayChecklist;
    }

    public void add(Medicine medicine){todayChecklist.put(medicine, Boolean.FALSE);}

    public void take(Medicine medicine){todayChecklist.put(medicine, Boolean.TRUE);}

    public void remove(Medicine medicine){todayChecklist.remove(medicine);}
    public Integer getDay() {return this.day;}

    public HashMap<Medicine, Boolean> getTodayChecklist() {return todayChecklist;}
}
