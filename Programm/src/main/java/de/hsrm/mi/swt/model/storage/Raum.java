package de.hsrm.mi.swt.model.storage;

import java.util.List;

public class Raum {
    private int hoehe;
    private int breite;
    private List<Regal> regale;
    public int getHoehe() {
        return hoehe;
    }

    public void setHoehe(int hoehe) {
        this.hoehe = hoehe;
    }

    public int getBreite() {
        return breite;
    }

    public void setBreite(int breite) {
        this.breite = breite;
    }

    public List<Regal> getRegale() {
        return regale;
    }

    public void setRegale(List<Regal> regale) {
        this.regale = regale;
    }
}
