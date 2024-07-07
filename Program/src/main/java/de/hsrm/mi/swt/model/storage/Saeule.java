package de.hsrm.mi.swt.model.storage;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.io.Serializable;

public class Saeule implements Serializable {
    private transient IntegerProperty positionX;
    private transient ObjectProperty<Runnable> onChange;


    public Saeule(int positionX) {
        if (positionX > 1300 || positionX < 0) {
            System.out.println(positionX);
            System.err.println("Säulen müssen zwischen 0 und 1300 platziert werden!");
            this.positionX = new SimpleIntegerProperty(Math.min(Math.max(positionX, 0), 1300));
            onChange = new SimpleObjectProperty<>();
        } else {
            this.positionX = new SimpleIntegerProperty(positionX);
            this.positionX.addListener((obs, oldVal, newVal) -> triggerChange());
            onChange = new SimpleObjectProperty<>();
        }
    }
    private void triggerChange() {
        // Hier können Sie weitere Logik hinzufügen, falls notwendig
        System.out.println("Änderung in Säule.");
        if (onChange.get() != null) {
            onChange.get().run();
        }
    }

    public void setOnChange(Runnable onChange) {
        this.onChange.set(onChange);
    }

    public int getPositionX() {
        return positionX.get();
    }

    public IntegerProperty positionXProperty() {
        return positionX;
    }

    public void setPositionX(int positionX) {
        this.positionX.set(positionX);
    }
}
