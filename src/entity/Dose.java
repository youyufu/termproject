package entity;

public class Dose {

    private int size;

    private int inventory;

    private String unit;


    public int getDose(){return size;}

    public String getUnit(){return unit;}
    public int getDosesRemaining(){return inventory - size;}
}
