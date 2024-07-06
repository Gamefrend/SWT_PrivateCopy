package de.hsrm.mi.swt.model.storage;

import de.hsrm.mi.swt.view.uikomponente.KartonView;
import javafx.beans.Observable;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Raum implements Serializable {
    private transient IntegerProperty hoehe;
    private transient IntegerProperty breite;
    private transient ObjectProperty<Regal> regal;
    private transient ObjectProperty<Runnable> onChange;

    private transient ObservableList<KartonView> kartons;

    public Raum(int hoehe, int breite) {
        this.hoehe = new SimpleIntegerProperty(hoehe);
        this.breite = new SimpleIntegerProperty(breite);
        this.regal = new SimpleObjectProperty<>(new Regal(this.hoehe, null, 0, 1300));
        this.onChange = new SimpleObjectProperty<>();

        // Listeners for properties
        this.hoehe.addListener((obs, oldVal, newVal) -> triggerChange());
        this.breite.addListener((obs, oldVal, newVal) -> triggerChange());
        this.regal.get().setOnChangeListener(this::triggerChange);

        this.kartons = FXCollections.observableArrayList();
        this.kartons.addListener((Observable obs) -> triggerChange());
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


    public ObservableList<KartonView> getKartons() {
        return kartons;
    }

    public void addKarton(KartonView karton) {
        this.kartons.add(karton);
        triggerChange();
    }

    public void removeKarton(KartonView karton) {
        this.kartons.remove(karton);
        triggerChange();
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        out.writeInt(getHoehe());
        out.writeInt(getBreite());
        out.writeObject(getRegal());
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        int hoehe = in.readInt();
        int breite = in.readInt();
        Regal regal = (Regal) in.readObject();

        this.hoehe = new SimpleIntegerProperty(hoehe);
        this.breite = new SimpleIntegerProperty(breite);
        this.regal = new SimpleObjectProperty<>(regal);
        this.onChange = new SimpleObjectProperty<>();

        // Restore listeners
        this.hoehe.addListener((obs, oldVal, newVal) -> triggerChange());
        this.breite.addListener((obs, oldVal, newVal) -> triggerChange());
        this.regal.addListener((obs, oldVal, newVal) -> triggerChange());

        // Vorherige Deserialisierung
        this.kartons = FXCollections.observableArrayList();
        this.kartons.addListener((Observable obs) -> triggerChange());
    }
}
