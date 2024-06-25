package de.hsrm.mi.swt.model.storage;

import java.util.List;

public class Inventar {
    private List<Karton> kartons;
    public List<Karton> getKartons() {
        return kartons;
    }
    public void setKartons(List<Karton> kartons) {
        this.kartons = kartons;
    }
}
