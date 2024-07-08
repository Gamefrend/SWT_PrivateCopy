package de.hsrm.mi.swt.model.storage;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Raum implements Serializable {
    private transient IntegerProperty hoehe;
    private transient IntegerProperty breite;
    private transient ObjectProperty<Regal> regal;
    private transient ObjectProperty<Runnable> onChange;


    public Raum(int hoehe, int breite) {
        this.hoehe = new SimpleIntegerProperty(hoehe);
        this.breite = new SimpleIntegerProperty(breite);
        this.regal = new SimpleObjectProperty<>(new Regal(this.hoehe));
        this.onChange = new SimpleObjectProperty<>();

        // Listeners for properties
        this.hoehe.addListener((obs, oldVal, newVal) -> triggerChange());
        this.breite.addListener((obs, oldVal, newVal) -> triggerChange());
        this.regal.get().setOnChangeListener(onChange.get());
    }

    private void triggerChange() {
        if (onChange.get() != null) {
            onChange.get().run();
        }
    }

    public void setOnChangeListener(Runnable listener) {
        this.regal.get().setOnChangeListener(listener);
        this.onChange.set(listener);
    }

    public int getHoehe() {
        return hoehe.get();
    }

    public void setHoehe(int hoehe) {
        this.hoehe.set(hoehe);
    }

    public IntegerProperty hoeheProperty() {
        return hoehe;
    }

    public int getBreite() {
        return breite.get();
    }

    public void setBreite(int breite) {
        this.breite.set(breite);
    }

    public IntegerProperty breiteProperty() {
        return breite;
    }

    public Regal getRegal() {
        return regal.get();
    }

    public void setRegal(Regal regal) {
        this.regal.addListener((obs, oldVal, newVal) -> triggerChange());
        this.regal.set(regal);
    }

    public ObjectProperty<Regal> regalProperty() {
        return regal;
    }




    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        out.writeInt(getHoehe());
        out.writeInt(getBreite());
        out.writeObject(getRegal());
    }

}
