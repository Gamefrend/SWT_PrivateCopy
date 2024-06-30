package main.java.de.hsrm.mi.swt.model.storage;

import java.util.ArrayList;
import java.util.List;

public class Regal {
    private int hoehe;
    private List<RegalBrett> regalBretter;
    private List<Saeule> saeulen;

    public Regal(int hoehe, List<RegalBrett> regalBretter, Saeule saeule, int saelenPos1, int saulenPos2) {
        this.hoehe = hoehe;
        this.regalBretter = regalBretter;
        this.saeulen = new ArrayList<>();
        this.saeulen.add(new Saeule(saelenPos1));
        this.saeulen.add(new Saeule(saulenPos2));
    }

    public void addSaele(Saeule saeule) {
        saeulen.add(saeule);
    }

    public void removeSaele(Saeule saeule) {
        saeulen.remove(saeule);
    }

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

    public List<Saeule> getSaeule() {
        return saeulen;
    }

    public void setSaeule(List<Saeule> saeule) {
        this.saeulen = saeule;
    }
}
