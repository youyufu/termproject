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
    public String getName() {return this.name;}
    public Integer[] getWeeklySchedule() {return this.weeklySchedule;}
    public String getDoseString() {return String.valueOf(doses.getSize()) + " " + doses.getUnit();}
    public String getInventoryString() {return String.valueOf(doses.getInventory()) + " " + doses.getUnit();}
    public String getDescription() {return this.description;}
    public Dose getDose() {return this.doses;}
}
