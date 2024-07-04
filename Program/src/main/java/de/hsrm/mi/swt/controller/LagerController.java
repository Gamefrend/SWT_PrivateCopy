package de.hsrm.mi.swt.controller;

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

    public LagerController(StorageShelvesApplication application, LagerView lagerView) {
        this.application = application;
        this.lagerView = lagerView;
        menuButton = lagerView.getMenuButton();
        undoButton = lagerView.getUndoButton();
        redoButton = lagerView.getRedoButton();
        saveButton = lagerView.getSaveButton();
        settingsButton = lagerView.getSettingsButton();

        initialize();
    }

    private void initialize() {
        this.aktuellerRaum = application.getAktuellerRaum();
        if(application.getAktuellesSpeicherprofil()!=null){
            this.aktuellesSpeicherprofil = application.getAktuellesSpeicherprofil();
        }else{
            System.out.println("Hier kommt Logik hin die ein neuen Raum erstellt");
            aktuellerRaum = new Raum(2,3);
            application.setAktuellerRaum(aktuellerRaum);
            aktuellesSpeicherprofil = new SpeicherProfil("TestProfil1");
        }
        undoButton.setOnAction(e -> handleUndo());
        redoButton.setOnAction(e -> handleRedo());
        saveButton.setOnAction(e -> handleSave());
        settingsButton.setOnAction(e -> handleSettings());
        menuButton.addEventHandler(ActionEvent.ACTION, e -> application.switchView(PrimaryViewName.StartmenueView));
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

    public Pane getRoot() {
        return lagerView;
    }
}
