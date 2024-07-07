package de.hsrm.mi.swt.controller;

import de.hsrm.mi.swt.app.StorageShelvesApplication;
import de.hsrm.mi.swt.model.storage.Ware;
import de.hsrm.mi.swt.view.uikomponente.KartonView;
import javafx.scene.paint.Color;

public class KartonController {
    private StorageShelvesApplication application;
    private KartonView kartonView;

    public KartonController(StorageShelvesApplication application, KartonView kartonView) {
        this.application = application;
        this.kartonView = kartonView;
        initialize();
    }

    private void initialize() {

    }

    public KartonView getKartonView() {
        return kartonView;
    }


    public void updateKartonView(int width, int height, Color color, int maxBelastung, int xPosition, Ware ware) {
        kartonView.setWidth(width);
        kartonView.setHeight(height);
        kartonView.setColor(color);
        kartonView.setMaxBelastung(maxBelastung);
        kartonView.setXPosition(xPosition);
        kartonView.setWare(ware);
    }


}
