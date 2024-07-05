package de.hsrm.mi.swt.model.storage;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ListChangeListener;
import java.io.Serializable;

public class Regal implements Serializable {
    private transient IntegerProperty hoehe;
    private transient ObservableList<RegalBrett> regalBretter;
    private transient ObservableList<Saeule> saeulen;

    private transient ObjectProperty<Runnable> onChange;

    private Inventar uebrigesInventar;

    public Regal(IntegerProperty hoehe, ObservableList<RegalBrett> regalBretter, int saelenPos1, int saulenPos2) {
        this.hoehe = hoehe;
        this.regalBretter = regalBretter != null ? regalBretter : FXCollections.observableArrayList();
        this.saeulen = FXCollections.observableArrayList();
        this.saeulen.add(new Saeule(saelenPos1));
        this.saeulen.add(new Saeule(saulenPos2));
        uebrigesInventar = new Inventar();
        onChange = new SimpleObjectProperty<>();

        this.hoehe.addListener((obs, oldVal, newVal) -> triggerChange());
        this.regalBretter.addListener((ListChangeListener.Change<? extends RegalBrett> change) -> triggerChange());
        this.saeulen.addListener((ListChangeListener.Change<? extends Saeule> change) -> {
            triggerChange();
            return;
        });
    }

    private void triggerChange() {
        // Hier können Sie weitere Logik hinzufügen, falls notwendig
        System.out.println("Änderung im Regal erkannt.");
        if (onChange.get() != null) {
            onChange.get().run();
        }
    }

    public void setOnChangeListener(Runnable listener) {
        this.onChange.set(listener);
    }

    public IntegerProperty getHoehe() {
        return hoehe;
    }

    public void setHoehe(IntegerProperty hoehe) {
        this.hoehe = hoehe;
    }

    public ObservableList<RegalBrett> getRegalBretter() {
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
}
