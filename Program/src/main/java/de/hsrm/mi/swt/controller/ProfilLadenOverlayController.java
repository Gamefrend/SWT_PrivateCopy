package de.hsrm.mi.swt.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import de.hsrm.mi.swt.app.StorageShelvesApplication;
import de.hsrm.mi.swt.app.StorageShelvesMain;
import de.hsrm.mi.swt.model.save.SpeicherProfil;
import de.hsrm.mi.swt.model.storage.Raum;
import de.hsrm.mi.swt.view.PrimaryViewName;
import de.hsrm.mi.swt.view.startmenue.ProfilLadenOverlayView;

import java.io.File;

public class ProfilLadenOverlayController {

    @FXML
    private TextField input;

    private ProfilLadenOverlayView view;
    private SpeicherProfil speicherProfil;
    private StorageShelvesApplication application;

    public ProfilLadenOverlayController(ProfilLadenOverlayView view, SpeicherProfil speicherProfil) {
        this.view = view;
        this.speicherProfil = speicherProfil;
        this.application = new StorageShelvesApplication();
        initialize();
    }

    @FXML
    private void initialize() {
        view.getLadenButton().setOnAction(this::handleLadenButton);
    }

    private void handleLadenButton(ActionEvent event) {
        String eingabe = view.getInputField().getText().trim();
        String fileName = eingabe + ".StorageShelves";
        File saveFile = new File("Program/src/main/resources/saves", fileName);

        if (saveFile.exists()) {
            try {
                speicherProfil.setSaveName(eingabe);
                Raum geladenerRaum = speicherProfil.load();

                // Wechsel der View
                application.switchView(PrimaryViewName.LagerView);


            } catch (RuntimeException ex) {
                System.err.println("Fehler beim Laden des Raums: " + ex.getMessage());
                // hier noch was?
            }
        } else {
            System.err.println("SpeicherProfil nicht gefunden: " + eingabe);
            // wollen wir hier Fehler behandeln?
        }
    }
}
