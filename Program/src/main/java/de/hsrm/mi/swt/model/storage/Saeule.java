package de.hsrm.mi.swt.model.storage;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import java.io.Serializable;

public class Saeule implements Serializable {
    private transient IntegerProperty positionX;

    public Saeule(int positionX) {
        if (positionX > 1300 || positionX < 0) {
            System.out.println(positionX);
            System.err.println("Säulen müssen zwischen 0 und 1300 platziert werden!");
            this.positionX = new SimpleIntegerProperty(Math.min(Math.max(positionX, 0), 1300));
        } else {
            this.positionX = new SimpleIntegerProperty(positionX);
        }
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
