package de.hsrm.mi.swt.model.storage;

import de.hsrm.mi.swt.view.uikomponente.Karton;
import javafx.beans.Observable;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

public class RegalBrett implements Serializable {
    private int hoehe;
    private int breite;
    private int dicke;
    private int maxBelastung;
    private int lueckenIndex;
   // private List<Karton> kartons;
   private transient ObservableList<Karton> kartons;
   private transient ObjectProperty<Runnable> onChange;



    public RegalBrett(int hoehe, int dicke, int maxBelastung, int lueckenIndex) {
        this.hoehe = hoehe;
        this.breite = breite;
        this.dicke = dicke;
        this.maxBelastung = maxBelastung;
        this.lueckenIndex = lueckenIndex;
        //this.kartons = new ArrayList<>();

        this.onChange = new SimpleObjectProperty<>();
        this.kartons = FXCollections.observableArrayList();
        this.kartons.addListener((Observable obs) -> triggerChange());
        this.onChange = new SimpleObjectProperty<>();
        //this.kartons.get(0).setOnChangeListener(this::triggerChange);
    }


    public ObservableList<Karton> getKartons() {
        return kartons;
    }

    public void addKarton(Karton karton) {
        this.kartons.add(karton);
        triggerChange();
    }

    public void removeKarton(Karton karton) {
        this.kartons.remove(karton);
        triggerChange();
    }
    private void triggerChange() {
        if (onChange.get() != null) {
            System.out.println("Änderungen im Regalbrett erkannt");
            onChange.get().run();
        }
    }

    public void setKartons(ObservableList<Karton> kartons) {
        this.kartons = kartons;
    }

    public void setOnChange(Runnable onChange) {
        this.onChange.set(onChange);
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

    //public List<Karton> getKartons() {return kartons; }

    //public void setKartons(List<Karton> kartons) { this.kartons = kartons;}

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        // Vorherige Deserialisierung
        this.kartons = FXCollections.observableArrayList();
        this.kartons.addListener((Observable obs) -> triggerChange());
    }


}
