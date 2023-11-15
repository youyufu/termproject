package entity;

public class Medicine {
    private String name;

    private Dose doses;

    private Integer[] weeklySchedule;

    private String description;
    private String id;


    public Medicine(String medicine, Dose doses, Integer[] weeklySchedule, String description, String id) {
        this.name = medicine;
        this.doses = doses;
        this.weeklySchedule = weeklySchedule;
        this.description = description;
        this.id = id;
    }
    public String getName() {return this.name;}
    public Integer[] getWeeklySchedule() {return this.weeklySchedule;}
    public String getDoseString() {return String.valueOf(doses.getSize()) + " " + doses.getUnit();}
    public String getInventoryString() {return String.valueOf(doses.getInventory()) + " " + doses.getUnit();}
    public String getDescription() {return this.description;}
    public Dose getDose() {return this.doses;}
    public String getId() {return this.id;}
}
