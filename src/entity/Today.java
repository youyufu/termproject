package entity;

import java.util.HashMap;

public class Today {

    private HashMap<String, Integer> todayChecklist = new HashMap<>();
    private Integer day;
    public Today(Integer day) {this.day = day;}
    public void add(String medicine, Integer taken) {todayChecklist.put(medicine, taken);}
    public void take(String medicine){todayChecklist.put(medicine, todayChecklist.get(medicine) + 1);}
    public void untake(String medicine) {todayChecklist.put(medicine, todayChecklist.get(medicine) - 1);}
    public void remove(String medicine){todayChecklist.remove(medicine);}
    public Integer getDay() {return this.day;}
    public HashMap<String, Integer> getTodayChecklist() {return todayChecklist;}
}
