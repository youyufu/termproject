package entity;

public class Dose {
    /**
     * Dose entity responsible for the quantity and type of dosage each medicine object has
     */

    private final Integer size;

    private Integer inventory;

    private final String unit;

    /**
     *
     * @param doseSize the dose to be taken
     * @param inventory the amount of medicine left
     * @param unit the type of dosage to be taken
     */
    public Dose(Integer doseSize, Integer inventory, String unit) {
        this.size = doseSize;
        this.inventory = inventory;
        this.unit = unit;
    }

    /**
     * Gets the amount of dosage that needs to be taken.
     * @return the dosage to be taken amount.
     */

    public Integer getSize(){return size;}

    /**
     * A getter for the unit type of the dose to be taken.
     * @return unit type of the dose to be taken.
     */
    public String getUnit(){return unit;}

    /**
     * A getter for the full amount of medicine left.
     * @return the amount of medicine remaining in stock.
     */
    public Integer getInventory() {return this.inventory;}

    /**
     * A getter for the amount of doses left depending on the inventory size.
     * @return the amount of doses that remain to be taken.
     */
    public Integer getDosesRemaining(){return inventory / size;}

    /**
     * Logs that the dosage required was taken and removes the correlating amount from inventory.
     */
    public void takeDose(){this.inventory -= this.size;}

    /**
     * Reverses the logs that the dosage required was taken and reverses the correlating amount taken
     * from inventory.
     */
    public void undoTakeDose(){this.inventory += this.size;}
}
