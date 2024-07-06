package de.hsrm.mi.swt.model.storage;

import de.hsrm.mi.swt.view.uikomponente.KartonView;
import javafx.beans.Observable;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RegalBrett implements Serializable {
    private int hoehe;
    private int breite;
    private int dicke;
    private int maxBelastung;
    private int lueckenIndex;
   // private List<Karton> kartons;
   private transient ObservableList<KartonView> kartons;
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
    private void triggerChange() {
        if (onChange.get() != null) {
            onChange.get().run();
        }
    }
 //   public void addKarton(Karton karton) {kartons.add(karton);}

   // public void removeKarton(Karton karton) {kartons.remove(karton);}

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
