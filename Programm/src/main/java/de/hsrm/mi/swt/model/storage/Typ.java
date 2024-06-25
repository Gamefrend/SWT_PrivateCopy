package de.hsrm.mi.swt.model.storage;

public class Typ {
    private boolean lebensmittelBool;
    private boolean giftigBool;
    private boolean gekuehltBool;
    public boolean isLebensmittelBool() {
        return lebensmittelBool;
    }

    public void setLebensmittelBool(boolean lebensmittelBool) {
        this.lebensmittelBool = lebensmittelBool;
    }

    public boolean isGiftigBool() {
        return giftigBool;
    }

    public void setGiftigBool(boolean giftigBool) {
        this.giftigBool = giftigBool;
    }

    public boolean isGekuehltBool() {
        return gekuehltBool;
    }

    public void setGekuehltBool(boolean gekuehltBool) {
        this.gekuehltBool = gekuehltBool;
    }
}
