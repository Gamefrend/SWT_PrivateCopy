package de.hsrm.mi.swt.model.storage;

import java.io.Serializable;
import java.time.LocalDate;

public class Ware implements Serializable {
    private String name;
    private int hoehe;
    private int gewicht;
    private int xPosition;
    private LocalDate mhd;
    private Typ typ;

    public Ware(String name, int hoehe, int gewicht, int xPosition, LocalDate mhd, Typ typ) {
        this.name = name;
        this.hoehe = hoehe;
        this.gewicht = gewicht;
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

    public int getGewicht() {
        return gewicht;
    }

    public void setGewicht(int gewicht) {
        this.gewicht = gewicht;
    }

    public int getXPosition() {
        return xPosition;
    }

    public void setXPosition(int xPosition) {
        this.xPosition = xPosition;
    }

    public LocalDate getMhd() {
        return mhd;
    }

    public void setMhd(LocalDate mhd) {
        this.mhd = mhd;
    }

    public Typ getTyp() {
        return typ;
    }

    public void setTyp(Typ typ) {
        this.typ = typ;
    }
}
