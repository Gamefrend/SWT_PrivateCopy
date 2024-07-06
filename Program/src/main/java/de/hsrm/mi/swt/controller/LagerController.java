package de.hsrm.mi.swt.controller;

import de.hsrm.mi.swt.model.storage.Regal;
import de.hsrm.mi.swt.model.storage.RegalBrett;
import de.hsrm.mi.swt.model.storage.Saeule;
import de.hsrm.mi.swt.view.uikomponente.Karton;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
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

    private Runnable onChange;

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
        initialize(application.getAktuellerRaum());
        application.setRaumChangeListener(this::initialize);
    }

    private void initialize(Raum raum) {
        this.aktuellerRaum = raum;
        setupRoom();
        setupViewBindings();
        setupButtonHandlers();
        lagerView.redraw(aktuellerRaum);
    }

    private void setupRoom() {
        if (application.getAktuellesSpeicherprofil() != null) {
            this.aktuellesSpeicherprofil = application.getAktuellesSpeicherprofil();
        } else {
            System.out.println("Hier kommt Logik hin die ein neuen Raum erstellt");
            application.setAktuellerRaum(new Raum(2000, 3000));
            aktuellerRaum = application.getAktuellerRaum();
            application.setAktuellesSpeicherprofil(new SpeicherProfil("TestProfil1"));
            aktuellesSpeicherprofil = application.getAktuellesSpeicherprofil();
            aktuellerRaum.setRegal(new Regal(new SimpleIntegerProperty(2000), 50, 300));
        }
        lagerView.getProfileNameField().setText(aktuellesSpeicherprofil.getSaveName());
    }

    private void setupViewBindings() {
        onChange = () -> {
            aktuellerRaum = application.getAktuellerRaum();
            lagerView.redraw(aktuellerRaum);
        };
        aktuellerRaum.setOnChangeListener(onChange);
        lagerView.bindModel(aktuellerRaum);
    }

    private void setupButtonHandlers() {
        undoButton.setOnAction(e -> handleUndo());
        redoButton.setOnAction(e -> handleRedo());
        saveButton.setOnAction(e -> handleSave());
        settingsButton.setOnAction(e -> handleSettings());
        menuButton.setOnAction(e -> {
            System.out.println("Restarted?!?");
            application.restart();
        });
        brettButton.addEventHandler(ActionEvent.ACTION, e -> handleBrett());
        saueleButton.addEventHandler(ActionEvent.ACTION, e -> handleSauele());
        kartonButton.addEventHandler(ActionEvent.ACTION, e -> handleKarton());
        lagerView.getCenterArea().setOnMouseClicked(event -> {
            if (saeuleButtonActive) {
                double x = event.getX();
                addSaeule(x);
            }
        });
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
        RegalBrett neuesBrett = new RegalBrett(100, 10, 1, 0);
        aktuellerRaum.getRegal().getRegalBretter().add(neuesBrett);
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

    public void handleKarton() {
        aktuellerRaum.getRegal().getRegalBretter().get(0).getKartons().add(new Karton(50, 50, Color.FIREBRICK, 100, 20, null));
        System.out.println(aktuellerRaum.getRegal().getRegalBretter().get(0).getKartons().toString());
    }

    public LagerView getRoot() {
        return lagerView;
    }
}
