package de.hsrm.mi.swt.controller;

import de.hsrm.mi.swt.model.storage.Regal;
import de.hsrm.mi.swt.model.storage.RegalBrett;
import de.hsrm.mi.swt.model.storage.Saeule;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import de.hsrm.mi.swt.app.StorageShelvesApplication;
import de.hsrm.mi.swt.model.save.SpeicherProfil;
import de.hsrm.mi.swt.model.storage.Raum;
import de.hsrm.mi.swt.view.PrimaryViewName;
import de.hsrm.mi.swt.view.lager.LagerView;
import javafx.event.ActionEvent;

import java.util.Map;

public class LagerController {

    private Map<PrimaryViewName, Pane> primaryViews;
    private Stage primaryStage;
    private LagerView lagerView;
    private Raum aktuellerRaum;
    private SpeicherProfil aktuellesSpeicherprofil;
    private StorageShelvesApplication application;
    private Button menuButton;
    private Button undoButton;
    private Button redoButton;
    private Button saveButton;
    private Button settingsButton;
    private Button brettButton;
    private Button saueleButton;
    private Button skalierenButton;
    private Button moveButton;
    public LagerController(StorageShelvesApplication application, LagerView lagerView) {
        this.application = application;
        this.lagerView = lagerView;
        menuButton = lagerView.getMenuButton();
        undoButton = lagerView.getUndoButton();
        redoButton = lagerView.getRedoButton();
        saveButton = lagerView.getSaveButton();
        settingsButton = lagerView.getSettingsButton();
        brettButton = lagerView.getBrettButton();
        saueleButton = lagerView.getSaueleButton();
        skalierenButton = lagerView.getSkalierenButton();
        moveButton = lagerView.getMoveButton();

        initialize();
    }

    private void initialize() {
        this.aktuellerRaum = application.getAktuellerRaum();
        if (application.getAktuellesSpeicherprofil() != null) {
            this.aktuellesSpeicherprofil = application.getAktuellesSpeicherprofil();
        } else {
            System.out.println("Hier kommt Logik hin die ein neuen Raum erstellt");
            aktuellerRaum = new Raum(2000, 3000);
            aktuellerRaum.setRegal(new Regal(new SimpleIntegerProperty(2000), null, 100, 200));
            application.setAktuellerRaum(aktuellerRaum);
            aktuellesSpeicherprofil = new SpeicherProfil("TestProfil1");
        }

        aktuellerRaum.setOnChangeListener(() -> {
            lagerView.redraw(aktuellerRaum);
        });


        lagerView.bindModel(aktuellerRaum);

        undoButton.setOnAction(e -> handleUndo());
        redoButton.setOnAction(e -> handleRedo());
        saveButton.setOnAction(e -> handleSave());
        settingsButton.setOnAction(e -> handleSettings());
        menuButton.addEventHandler(ActionEvent.ACTION, e -> application.switchView(PrimaryViewName.StartmenueView));
        brettButton.addEventHandler(ActionEvent.ACTION, e -> handleBrett());
        saueleButton.addEventHandler(ActionEvent.ACTION, e -> handleSauele());
        lagerView.redraw(aktuellerRaum);
    }

    private void handleUndo() {
        // Undo-Logik muss noch implementiert werden
        System.out.println("Undo button clicked");
    }

    private void handleRedo() {
        // Redo-Logik muss noch implementiert werden
        System.out.println("Redo button clicked");
    }

    private void handleSave() {
        aktuellesSpeicherprofil.save(aktuellerRaum);
        System.out.println("Save button clicked");
    }

    private void handleSettings() {
        System.out.println("Settings button clicked");
    }

    public void handleBrett() {
        aktuellerRaum.getRegal().getRegalBretter().add(new RegalBrett(1, 1, 1, 1, 1));
    }

    public void handleSauele() {
        System.out.println("In hadleSauele()");
        System.out.println(aktuellerRaum);
        aktuellerRaum.getRegal().getSaeulen().add(new Saeule(1));
        System.out.println(aktuellerRaum.getRegal().getSaeulen().toString());
    }

    public LagerView getRoot() {
        return lagerView;
    }
}
