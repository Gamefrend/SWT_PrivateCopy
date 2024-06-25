package de.hsrm.mi.swt.model.storage;
import java.util.List;

public class RegalBrett {
    private int hoehe;
    private int breite;
    private int dicke;
    private int maxBelastung;
    private int lueckenIndex;
    private List<Karton> kartons;
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

    public int getDicke() {
        return dicke;
    }

    public void setDicke(int dicke) {
        this.dicke = dicke;
    }

    public int getMaxBelastung() {
        return maxBelastung;
    }

    public void setMaxBelastung(int maxBelastung) {
        this.maxBelastung = maxBelastung;
    }

    public int getLueckenIndex() {
        return lueckenIndex;
    }

    public void setLueckenIndex(int lueckenIndex) {
        this.lueckenIndex = lueckenIndex;
    }

    public List<Karton> getKartons() {
        return kartons;
    }

    public void setKartons(List<Karton> kartons) {
        this.kartons = kartons;
    }
}
