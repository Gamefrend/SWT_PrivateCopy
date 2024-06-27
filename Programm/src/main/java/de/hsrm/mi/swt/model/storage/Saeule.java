package main.java.de.hsrm.mi.swt.model.storage;

import java.util.List;

public class Saeule {
    private int positionX;
    private List<Regal> regale;
    public int getPositionX() {
        return positionX;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public List<Regal> getRegale() {
        return regale;
    }

    public void setRegale(List<Regal> regale) {
        this.regale = regale;
    }
}
