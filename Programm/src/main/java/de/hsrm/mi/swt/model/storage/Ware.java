package main.java.de.hsrm.mi.swt.model.storage;

import java.util.Date;

public class Ware {
    private String name;
    private int hoehe;
    private int maxBelastung;
    private int xPosition;
    private Date mhd;
    private Typ typ;

    public Ware(String name, int hoehe, int maxBelastung, int xPosition, Date mhd, Typ typ) {
        this.name = name;
        this.hoehe = hoehe;
        this.maxBelastung = maxBelastung;
        this.xPosition = xPosition;
        this.mhd = mhd;
        this.typ = typ;
    }

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

    public Date getMhd() {
        return mhd;
    }

    public void setMhd(Date mhd) {
        this.mhd = mhd;
    }

    public Typ getTyp() {
        return typ;
    }

    public void setTyp(Typ typ) {
        this.typ = typ;
    }
}
