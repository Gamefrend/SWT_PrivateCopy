package de.hsrm.mi.swt.model.storage;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.ObservableList;

import java.io.Serializable;
import java.util.List;

public class Kuehlschrank extends Regal implements Serializable {

    public Kuehlschrank(SimpleIntegerProperty hoehe) {
        super(hoehe);
    }

}
