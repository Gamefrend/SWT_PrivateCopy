package de.hsrm.mi.swt.model.storage;

import javafx.beans.Observable;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Inventar implements Serializable {
    private transient ObservableList<Karton> kartons;

    private transient ObjectProperty<Runnable> onChange;

    public Inventar() {
        this.kartons = FXCollections.observableArrayList();
        kartons.addListener((Observable obs) -> triggerChange());
        onChange = new SimpleObjectProperty<>();
    }

    public void setOnChangeListener(Runnable listener) {
        this.onChange.set(listener);
    }

    private void triggerChange() {
        System.out.println("Änderung im Inventar erkannt.");
        if (onChange.get() != null) {
            onChange.get().run();
        }
    }

    public List<Karton> getKartons() {
        return kartons;
    }

    public void addKarton(Karton karton) {
        kartons.add(karton);
    }

    public void removeKarton(Karton karton) {
        kartons.remove(karton);
    }
}
