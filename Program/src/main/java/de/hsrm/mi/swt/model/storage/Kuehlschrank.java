package de.hsrm.mi.swt.model.storage;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.ObservableList;

import java.io.Serializable;
import java.util.List;

public class Kuehlschrank extends Regal implements Serializable {

    public Kuehlschrank(SimpleIntegerProperty hoehe, Saeule saeule, int saelenPos1, int saulenPos2) {
        super(hoehe, saelenPos1, saulenPos2);
    }

}
