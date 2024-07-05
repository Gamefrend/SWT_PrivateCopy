package de.hsrm.mi.swt.model.storage;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Raum implements Serializable {

    private int hoehe;
    private int breite;
    private Regal regal;

    public Raum(int hoehe, int breite) {
        this.hoehe = hoehe;
        this.breite = breite;
        this.regal = new Regal(hoehe,null,0, breite);
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

    public Regal getRegal() {
        return regal;
    }

    public void setRegal(Regal regal) {
        this.regal = regal;
    }
}
