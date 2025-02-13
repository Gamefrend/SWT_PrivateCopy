package de.hsrm.mi.swt.model.storage;

import javafx.beans.property.ObjectProperty;

import java.io.Serializable;

public class Karton implements Serializable {
    private int gewicht;
    private int breite;
    private int hoehe;
    private int maxBelastung;
    private int xPosition;
    private Ware waren;

    private transient ObjectProperty<Runnable> onChange;


    public Karton(int gewicht, int breite, int hoehe, int maxBelastung, int xPosition, Ware waren) {
        this.gewicht = gewicht;
        this.breite = breite;
        this.hoehe = hoehe;
        this.maxBelastung = maxBelastung;
        this.xPosition = xPosition;
        this.waren = waren;
    }

    private void triggerChange() {
        if (onChange.get() != null) {
            onChange.get().run();
        }
    }
    public void setOnChange(Runnable onChange) {
        this.onChange.set(onChange);
    }
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

    public Ware getWaren() {
        return waren;
    }

    public void setWaren(Ware ware) {
        this.waren = waren;
    }
}
