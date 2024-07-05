package de.hsrm.mi.swt.model.storage;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import java.io.Serializable;

public class Saeule implements Serializable {
    private transient IntegerProperty positionX;

    public Saeule(int positionX) {
        this.positionX = new SimpleIntegerProperty(positionX);
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
