package use_case.enterMedicine;

public class EnterOutputData {
    /**
     * An object representing the data to be passed to the presenter.
     */
    private final String medication;
    private final String dose;
    private final String inventory;
    private final Integer su;
    private final Integer m;
    private final Integer tu;
    private final Integer w;
    private final Integer th;
    private final Integer f;
    private final Integer sa;
    private final String description;
    private final Integer dosesRemaining;

    /**
     * Creates the output data.
     * @param medication the name of the medicine.
     * @param dose the size of each dose.
     * @param inventory the amount of medicine remaining.
     * @param su the number of doses to be taken on Sunday.
     * @param m the number of doses to be taken on Monday.
     * @param tu the number of doses to be taken on Tuesday.
     * @param w the number of doses to be taken on Wednesday.
     * @param th the number of doses to be taken on Thursday.
     * @param f the number of doses to be taken on Friday.
     * @param sa the number of doses to be taken on Saturday.
     * @param description the description for the medicine.
     * @param dosesRemaining the number of doses remaining.
     */
    public EnterOutputData(String medication,
                           String dose,
                           String inventory,
                           Integer su,
                           Integer m,
                           Integer tu,
                           Integer w,
                           Integer th,
                           Integer f,
                           Integer sa,
                           String description,
                           Integer dosesRemaining) {
        this.medication = medication;
        this.dose = dose;
        this.inventory = inventory;
        this.su = su;
        this.m = m;
        this.tu = tu;
        this.w = w;
        this.th = th;
        this.f = f;
        this.sa = sa;
        this.description = description;
        this.dosesRemaining = dosesRemaining;
    }

    /**
     * Gets the medicine attribute.
     * @return the name of the medicine.
     */
    public String getMedication() {
        return medication;
    }

    /**
     * Gets the dose attribute.
     * @return the size of each dose.
     */
    public String getDose() {
        return dose;
    }

    /**
     * Gets the inventory attribute.
     * @return the amount of medicine left.
     */
    public String getInventory() {
        return inventory;
    }

    /**
     * Gets the su attribute.
     * @return the number of doses to be taken on Sunday.
     */
    public Integer getSu() {
        return su;
    }

    /**
     * Gets the m attribute.
     * @return the number of doses to be taken on Monday.
     */
    public Integer getM() {
        return m;
    }

    /**
     * Gets the tu attribute.
     * @return the number of doses to be taken on Tuesday.
     */
    public Integer getTu() {
        return tu;
    }

    /**
     * Gets the w attribute.
     * @return the number of doses to be taken on Wednesday.
     */
    public Integer getW() {
        return w;
    }

    /**
     * Gets the th attribute.
     * @return the number of doses to be taken on Thursday.
     */
    public Integer getTh() {
        return th;
    }

    /**
     * Gets the f attribute.
     * @return the number of doses to be taken on Friday.
     */
    public Integer getF() {
        return f;
    }

    /**
     * Gets the sa attribute.
     * @return the number of doses to be taken on Saturday.
     */
    public Integer getSa() {
        return sa;
    }

    /**
     * Gets the description attribute.
     * @return the description for the medicine.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets the dosesRemaining attribute.
     * @return the number of doses remaining.
     */
    public Integer getDosesRemaining() {return dosesRemaining;}
}
