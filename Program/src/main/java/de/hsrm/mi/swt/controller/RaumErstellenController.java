package de.hsrm.mi.swt.controller;

import de.hsrm.mi.swt.app.StorageShelvesApplication;
import de.hsrm.mi.swt.model.save.SpeicherProfil;
import de.hsrm.mi.swt.model.storage.Raum;
import de.hsrm.mi.swt.view.PrimaryViewName;
import de.hsrm.mi.swt.view.lager.RaumErstellenView;
import javafx.stage.Popup;
import javafx.stage.Window;

public class RaumErstellenController {
    private RaumErstellenView view;
    private StorageShelvesApplication application;
    private Popup popup;
    private boolean isEditing;

    public RaumErstellenController(StorageShelvesApplication application) {
        this.application = application;
        this.popup = new Popup();
    }

    public void showPopup(Window owner, boolean isEditing) {
        this.isEditing = isEditing;
        view = new RaumErstellenView(isEditing);
        popup = new Popup();
        popup.getContent().add(view);

        if (isEditing) {
            SpeicherProfil currentProfil = application.getAktuellesSpeicherprofil();
            Raum currentRaum = application.getAktuellerRaum();
            view.setValues(currentProfil.getSaveName(), currentRaum.getHoehe(), currentRaum.getBreite());
        } else {
            view.clearValues();
        }

        view.getCloseButton().setOnAction(e -> hidePopup());
        view.getActionButton().setOnAction(e -> handleAction());

        popup.show(owner);
    }

    public void hidePopup() {
        if (popup.isShowing()) {
            popup.hide();
        }
    }

    private void handleAction() {
        boolean isValid = true;
        String name = view.getNameField().getText();
        int hoehe = 0, breite = 0;

        view.clearInvalidInput(view.getNameField());
        view.clearInvalidInput(view.getHoeheField());
        view.clearInvalidInput(view.getBreiteField());

        if (name.isEmpty()) {
            view.setInvalidInput(view.getNameField());
            isValid = false;
        }

        try {
            hoehe = Integer.parseInt(view.getHoeheField().getText());
        } catch (NumberFormatException e) {
            view.setInvalidInput(view.getHoeheField());
            isValid = false;
        }

        try {
            breite = Integer.parseInt(view.getBreiteField().getText());
        } catch (NumberFormatException e) {
            view.setInvalidInput(view.getBreiteField());
            isValid = false;
        }

        if (isValid) {
            if (isEditing) {
                SpeicherProfil currentProfil = application.getAktuellesSpeicherprofil();
                currentProfil.setSaveName(name);
                Raum currentRaum = application.getAktuellerRaum();
                currentRaum.setHoehe(hoehe);
                currentRaum.setBreite(breite);
                currentProfil.save(currentRaum);

                application.getLagerController().updateProfileName();
            } else {
                Raum newRaum = new Raum(hoehe, breite);
                SpeicherProfil newProfil = new SpeicherProfil(name);
                application.setAktuellerRaum(newRaum);
                application.setAktuellesSpeicherprofil(newProfil);
                application.switchView(PrimaryViewName.LagerView);
            }
            hidePopup();
        }
    }

}
