package de.hsrm.mi.swt.controller;

import de.hsrm.mi.swt.model.storage.Regal;
import de.hsrm.mi.swt.model.storage.RegalBrett;
import de.hsrm.mi.swt.model.storage.Saeule;
import de.hsrm.mi.swt.view.uikomponente.KartonView;
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
import javafx.stage.Window;
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
    private Button kartonButton;
    private boolean saeuleButtonActive = false;

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
        kartonButton = lagerView.getKartonButton();
        initialize();
    }

    private void initialize() {
        this.aktuellerRaum = application.getAktuellerRaum();
        if (application.getAktuellesSpeicherprofil() != null) {
            this.aktuellesSpeicherprofil = application.getAktuellesSpeicherprofil();
        } else {
            System.out.println("Hier kommt Logik hin die ein neuen Raum erstellt");
            aktuellerRaum = new Raum(2000, 3000);
            aktuellerRaum.setRegal(new Regal(new SimpleIntegerProperty(2000), null, 50, 300));
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
        menuButton.addEventHandler(ActionEvent.ACTION, e -> {
            application.setAktuellerRaum(null);
            application.setAktuellesSpeicherprofil(null);
            aktuellerRaum.setOnChangeListener(null);
            aktuellerRaum = null;
            aktuellesSpeicherprofil = null;
            application.switchView(PrimaryViewName.StartmenueView);
        });
        brettButton.addEventHandler(ActionEvent.ACTION, e -> handleBrett());
        saueleButton.addEventHandler(ActionEvent.ACTION, e -> handleSauele());
        kartonButton.setOnAction(event -> lagerView.fuegeKartonHinzu());

        lagerView.redraw(aktuellerRaum);

        // Add mouse click event handler to centerArea
        lagerView.getCenterArea().setOnMouseClicked(event -> {
            if (saeuleButtonActive) {
                double x = event.getX();
                addSaeule(x);
            }
        });
    //-------------------
       // kartonButton.addEventHandler(ActionEvent.ACTION , e-> application.switchView(KartonView));

        //---------------------
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
        aktuellerRaum.getRegal().getRegalBretter().add(new RegalBrett(100, 10, 1, 0));
    }

    public void handleSauele() {
        saeuleButtonActive = !saeuleButtonActive;
        if (saeuleButtonActive) {
            saueleButton.getStyleClass().add("active-button");
        } else {
            saueleButton.getStyleClass().remove("active-button");
        }
    }

    public void addSaeule(double x) {
        int positionX = (int) x;
        aktuellerRaum.getRegal().getSaeulen().add(new Saeule(positionX));
    }

    public boolean isSaeuleButtonActive() {
        return saeuleButtonActive;
    }

    public LagerView getRoot() {
        return lagerView;
    }
}
