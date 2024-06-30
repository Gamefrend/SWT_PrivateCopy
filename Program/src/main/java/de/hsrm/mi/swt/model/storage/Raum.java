package main.java.de.hsrm.mi.swt.model.storage;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Raum implements Serializable {

    private int hoehe;
    private int breite;
    private List<Regal> regale;

    public Raum(int hoehe, int breite) {
        this.hoehe = hoehe;
        this.breite = breite;
        this.regale = new ArrayList<>();
    }

    public void addRegal(Regal regal) {
        regale.add(regal);
    }

    public void removeRegal(Regal regal) {
        regale.remove(regal);
    }

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
