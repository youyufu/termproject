package entity;

import java.util.Map;

public class Medicine {
    private String name;

    private Dose doses;

    private int[] weeklySchedule;

    private String description;


    public Medicine(String medicine, Dose doses, int[] weeklySchedule, String description) {
        this.name = medicine;
        this.doses = doses;
        this.weeklySchedule = weeklySchedule;
        this.description = description;
    }
}
