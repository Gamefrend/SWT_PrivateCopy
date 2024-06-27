package main.java.de.hsrm.mi.swt.model.storage;
import java.util.List;

public class Karton {
    private int gewicht;
    private int breite;
    private int hoehe;
    private int maxBelastung;
    private int xPosition;
    private List<Ware> waren;

    public int getGewicht() {
        return gewicht;
    }

    public void setGewicht(int gewicht) {
        this.gewicht = gewicht;
    }

    public int getBreite() {
        return breite;
    }

    public void setBreite(int breite) {
        this.breite = breite;
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

    public List<Ware> getWaren() {
        return waren;
    }

    public void setWaren(List<Ware> waren) {
        this.waren = waren;
    }
}
