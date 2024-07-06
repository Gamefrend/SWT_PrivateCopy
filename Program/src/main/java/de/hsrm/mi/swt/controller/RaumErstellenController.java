package de.hsrm.mi.swt.controller;

import de.hsrm.mi.swt.app.StorageShelvesApplication;
import de.hsrm.mi.swt.model.save.SpeicherProfil;
import de.hsrm.mi.swt.model.storage.Raum;
import de.hsrm.mi.swt.view.lager.RaumErstellenView;
import javafx.stage.Popup;
import javafx.stage.Window;
import de.hsrm.mi.swt.view.PrimaryViewName;

public class RaumErstellenController {
    private StorageShelvesApplication application;
    private RaumErstellenView raumErstellenView;
    private Popup popup;

    public RaumErstellenController(StorageShelvesApplication application) {
        this.application = application;
        this.raumErstellenView = new RaumErstellenView();
        this.popup = new Popup();
        this.popup.getContent().add(raumErstellenView);

        initialize();
    }

    private void initialize() {
        raumErstellenView.getCloseButton().setOnAction(e -> hidePopup());
        raumErstellenView.getCreateButton().setOnAction(e -> createLagersystem());
    }

    public void showPopup(Window owner) {
        if (!popup.isShowing()) {
            popup.show(owner);
        }
    }

    public void hidePopup() {
        if (popup.isShowing()) {
            popup.hide();
        }
    }

    private void createLagersystem() {
        try {
            String name = raumErstellenView.getNameField().getText();
            int hoehe = Integer.parseInt(raumErstellenView.getHoeheField().getText());
            int breite = Integer.parseInt(raumErstellenView.getBreiteField().getText());

            if (name.isEmpty()) {
                throw new IllegalArgumentException("Name darf nicht leer sein");
            }

            application.setAktuellerRaum(new Raum(hoehe, breite));
            application.setAktuellesSpeicherprofil(new SpeicherProfil(name));
            hidePopup();

            application.switchView(PrimaryViewName.LagerView);
        } catch (NumberFormatException e) {
            System.err.println("Ungültige Eingabe für Höhe oder Breite");
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }
    }
}
