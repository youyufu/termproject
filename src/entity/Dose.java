package entity;

public class Dose {

    private final Integer size;

    private Integer inventory;

    private final String unit;

    public Dose(Integer doseSize, Integer inventory, String unit) {
        this.size = doseSize;
        this.inventory = inventory;
        this.unit = unit;
    }

    public Integer getSize(){return size;}
    public String getUnit(){return unit;}
    public Integer getInventory() {return this.inventory;}
    public Integer getDosesRemaining(){return inventory / size;}
    public void takeDose(){this.inventory -= this.size;}
    public void undoTakeDose(){this.inventory += this.size;}
}
