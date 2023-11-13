package use_case.enterMedicine;

public class EnterOutputData {
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

    public String getMedication() {
        return medication;
    }

    public String getDose() {
        return dose;
    }

    public String getInventory() {
        return inventory;
    }

    public Integer getSu() {
        return su;
    }

    public Integer getM() {
        return m;
    }

    public Integer getTu() {
        return tu;
    }

    public Integer getW() {
        return w;
    }

    public Integer getTh() {
        return th;
    }

    public Integer getF() {
        return f;
    }

    public Integer getSa() {
        return sa;
    }

    public String getDescription() {
        return description;
    }
    public Integer getDosesRemaining() {return dosesRemaining;}
}
