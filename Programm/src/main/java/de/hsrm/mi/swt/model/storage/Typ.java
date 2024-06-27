package main.java.de.hsrm.mi.swt.model.storage;

public class Typ {
    private boolean lebensmittelBool;
    private boolean giftigBool;
    private boolean gekuehltBool;

    public Typ(boolean lebensmittelBool, boolean giftigBool, boolean gekuehltBool) {
        this.lebensmittelBool = lebensmittelBool;
        this.giftigBool = giftigBool;
        this.gekuehltBool = gekuehltBool;
    }

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
