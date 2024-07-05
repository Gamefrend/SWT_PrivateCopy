package de.hsrm.mi.swt.model.storage;

import java.io.Serializable;

public class Saeule implements Serializable {
    private int positionX;

    public Saeule(int positionX) {
        this.positionX = positionX;
    }

    public int getPositionX() {
        return positionX;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

}
