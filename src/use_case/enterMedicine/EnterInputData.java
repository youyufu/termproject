package use_case.enterMedicine;

public class EnterInputData {
    final private String medicine;
    final private Integer doseSize;
    final private Integer inventory;
    final private String unit;
    final private Integer[] day;
    final private String description;

    public EnterInputData(String medicine,
                          String doseSize,
                          Integer inventory,
                          String unit,
                          Integer[] day,
                          String description) {
        this.medicine = medicine;
        this.doseSize = Integer.valueOf(doseSize);
        this.inventory = inventory;
        this.unit = unit;
        this.day = day;
        this.description = description;
    }

    String getMedicine() {return medicine;}

    Integer getDoseSize() {return doseSize;}

    Integer getInventory() {return inventory;}

    String getUnit() {return unit;}

    Integer[] getDay() {return day;}

    String getDescription() {return description;}

}
