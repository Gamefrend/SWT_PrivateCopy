package de.hsrm.mi.swt.model.storage;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Inventar implements Serializable {
    private List<Karton> kartons;

    public Inventar() {
        this.kartons = new ArrayList<>();
    }

    public List<Karton> getKartons() {
        return kartons;
    }

    public void setKartons(List<Karton> kartons) {
        this.kartons = kartons;
    }

    public void addKarton(Karton karton) {
        kartons.add(karton);
    }

    public void removeKarton(Karton karton) {
        kartons.remove(karton);
    }
}
