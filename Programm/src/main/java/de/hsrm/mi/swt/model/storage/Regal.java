package main.java.de.hsrm.mi.swt.model.storage;
import java.util.List;

public class Regal {
    private int hoehe;
    private List<RegalBrett> regalBretter;
    private Saeule saeule;

    // Getter und Setter
    public int getHoehe() {
        return hoehe;
    }

    public void setHoehe(int hoehe) {
        this.hoehe = hoehe;
    }

    public List<RegalBrett> getRegalBretter() {
        return regalBretter;
    }

    public void setRegalBretter(List<RegalBrett> regalBretter) {
        this.regalBretter = regalBretter;
    }

    public Saeule getSaeule() {
        return saeule;
    }

    public void setSaeule(Saeule saeule) {
        this.saeule = saeule;
    }
}
