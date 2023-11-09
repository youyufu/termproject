package entity;

import java.util.Map;

public class Today {

    private Map<Medicine, Boolean> todayChecklist;

    public Today(Map<Medicine, Boolean> todayChecklist) {
        this.todayChecklist = todayChecklist;
    }

    public void add(Medicine medicine){todayChecklist.put(medicine, Boolean.FALSE);}

    public void take(Medicine medicine){todayChecklist.put(medicine, Boolean.TRUE);}

    public void remove(Medicine medicine){todayChecklist.remove(medicine);}
}
