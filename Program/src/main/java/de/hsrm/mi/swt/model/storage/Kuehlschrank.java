package de.hsrm.mi.swt.model.storage;

import java.io.Serializable;
import java.util.List;

public class Kuehlschrank extends Regal implements Serializable {

    public Kuehlschrank(int hoehe, List<RegalBrett> regalBretter, Saeule saeule, int saelenPos1, int saulenPos2) {
        super(hoehe, regalBretter, saelenPos1, saulenPos2);
    }

}
