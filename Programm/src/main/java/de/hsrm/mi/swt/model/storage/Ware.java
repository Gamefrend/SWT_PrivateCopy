package main.java.de.hsrm.mi.swt.model.storage;
public class Ware {
    private String name;
    private int hoehe;
    private int maxBelastung;
    private int xPosition;
    private String mhd;
    private Typ typ;

    // Getter und Setter
    public String getName() {
        return name;
    }

    public int getHoehe() {
        return hoehe;
    }

    public void setHoehe(int hoehe) {
        this.hoehe = hoehe;
    }

    public int getMaxBelastung() {
        return maxBelastung;
    }

    public void setMaxBelastung(int maxBelastung) {
        this.maxBelastung = maxBelastung;
    }

    public int getXPosition() {
        return xPosition;
    }

    public void setXPosition(int xPosition) {
        this.xPosition = xPosition;
    }

    public String getMhd() {
        return mhd;
    }

    public void setMhd(String mhd) {
        this.mhd = mhd;
    }

    public Typ getTyp() {
        return typ;
    }

    public void setTyp(Typ typ) {
        this.typ = typ;
    }
}
