package de.hsrm.mi.swt.model.storage;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ListChangeListener;
import java.io.Serializable;

public class Regal implements Serializable {
    private static final long serialVersionUID = 1L;

    private transient IntegerProperty hoehe;
    private transient ObservableList<RegalBrett> regalBretter;
    private transient ObservableList<Saeule> saeulen;

    private transient ObjectProperty<Runnable> onChange;
    private transient ObjectProperty<Inventar> uebrigesInventar;

    public Regal(int hoehe) {
        this.hoehe = new SimpleIntegerProperty(hoehe);
        this.regalBretter = FXCollections.observableArrayList();
        this.saeulen = FXCollections.observableArrayList();
        uebrigesInventar = new SimpleObjectProperty<>(new Inventar());
        onChange = new SimpleObjectProperty<>();

        this.hoehe.addListener((obs, oldVal, newVal) -> triggerChange());
        this.regalBretter.addListener((ListChangeListener.Change<? extends RegalBrett> change) -> triggerChange());
        this.saeulen.addListener((ListChangeListener.Change<? extends Saeule> change) -> triggerChange());
        this.uebrigesInventar.get().setOnChangeListener(onChange.get());
    }

    private void triggerChange() {
        // Hier können Sie weitere Logik hinzufügen, falls notwendig
        System.out.println("\u00C4nderung im Regal erkannt.");
        if (onChange.get() != null) {
            onChange.get().run();
        }
    }

    public void addSaeule(Saeule saeule) {
        int insertIndex = indexfindenBinarySearch(saeule);
        saeule.setOnChange(onChange.get());
        saeulen.add(insertIndex, saeule);
    }

    public void addBrett(RegalBrett b){
        regalBretter.add(b);
        for(RegalBrett brett:regalBretter){
            brett.setOnChange(onChange.get());
        }
    }



    public void verschiebeSaeule(Saeule saeule, int positionX){
        saeulen.remove(saeule);
        saeule.setPositionX(positionX);
        int insertIndex = indexfindenBinarySearch(saeule);
        saeulen.add(insertIndex, saeule);
    }

    private int indexfindenBinarySearch(Saeule saeule) {
        int ersterIndex = 0;
        int letzterIndex = saeulen.size() - 1;
        double positionX = saeule.getPositionX();

        while (ersterIndex <= letzterIndex) {
            int mitte = (ersterIndex + letzterIndex) / 2;
            double midXPosition = saeulen.get(mitte).getPositionX();

            if (midXPosition < positionX) {
                ersterIndex = mitte + 1;
            } else {
                letzterIndex = mitte - 1;
            }
        }
        return ersterIndex;
    }

    public void setOnChangeListener(Runnable listener) {
        this.onChange.set(listener);
        uebrigesInventar.get().setOnChangeListener(listener);
    }

    public IntegerProperty getHoehe() {
        return hoehe;
    }

    public void setHoehe(IntegerProperty hoehe) {
        this.hoehe = hoehe;
    }

    public ObservableList<RegalBrett> getRegalBretter() {
        for(RegalBrett brett:regalBretter){
            brett.setOnChange(onChange.get());
        }
        return regalBretter;
    }



    public void setRegalBretter(ObservableList<RegalBrett> regalBretter) {
        this.regalBretter = regalBretter;
    }

    public ObservableList<Saeule> getSaeulen() {
        return saeulen;
    }

    public void setSaeulen(ObservableList<Saeule> saeulen) {
        this.saeulen = saeulen;
    }
    public Inventar getUebrigesInventar() {
        return uebrigesInventar.get();
    }
}
