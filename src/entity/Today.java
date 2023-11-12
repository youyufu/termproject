package entity;

import java.util.HashMap;
import java.util.Map;

public class Today {
    private Map<Medicine,Boolean> map = new HashMap<>();
    public void add(Medicine medicine){
        map.put(medicine,true);
    }
}
