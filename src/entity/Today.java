package entity;

import java.util.HashMap;

public class Today {

    private HashMap<String, Boolean> todayChecklist = new HashMap<>();
    private Integer day;
    public Today(Integer day) {this.day = day;}
    public void add(String medicine){todayChecklist.put(medicine, Boolean.FALSE);}
    public void take(String medicine){todayChecklist.put(medicine, Boolean.TRUE);}
    public void remove(String medicine){todayChecklist.remove(medicine);}
    public Integer getDay() {return this.day;}
    public HashMap<String, Boolean> getTodayChecklist() {return todayChecklist;}
}
