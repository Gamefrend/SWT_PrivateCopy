package main.java.de.hsrm.mi.swt.controller;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import main.java.de.hsrm.mi.swt.app.StorageShelvesApplication;
import main.java.de.hsrm.mi.swt.model.save.SpeicherProfil;
import main.java.de.hsrm.mi.swt.model.storage.Raum;
import main.java.de.hsrm.mi.swt.view.PrimaryViewName;
import main.java.de.hsrm.mi.swt.view.lager.LagerView;
import javafx.event.ActionEvent;
import java.util.Map;

public class LagerController {

    private Map<PrimaryViewName, Pane> primaryViews;
    private Stage primaryStage;

    private LagerView lagerView;
    private SpeicherProfil speicherProfil;

    private StorageShelvesApplication application;
    private Button menuButton;

    public LagerController(StorageShelvesApplication application, LagerView lagerView) {
        this.application = application;
        this.lagerView = lagerView;
        menuButton = lagerView.getMenuButton();

        initialize();
    }

    private void initialize() {
        // Button-Events festlegen
        lagerView.getUndoButton().setOnAction(e -> handleUndo());
        lagerView.getRedoButton().setOnAction(e -> handleRedo());
        lagerView.getSaveButton().setOnAction(e -> handleSave());
        lagerView.getSettingsButton().setOnAction(e -> handleSettings());
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
        speicherProfil.save(new Raum(2, 2)); // Beispiel: speichert einen Raum mit 2x2 Größe
        System.out.println("Save button clicked");
    }

    private void handleSettings() {
        System.out.println("Settings button clicked");
    }

    public Pane getRoot() {
        return lagerView;
    }
}
