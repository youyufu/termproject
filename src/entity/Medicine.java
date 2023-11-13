package entity;

public class Medicine {
    private String name;

    private Dose doses;

    private Integer[] weeklySchedule;

    private String description;


    public Medicine(String medicine, Dose doses, Integer[] weeklySchedule, String description) {
        this.name = medicine;
        this.doses = doses;
        this.weeklySchedule = weeklySchedule;
        this.description = description;
    }
}
