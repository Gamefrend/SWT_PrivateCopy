package main.java.de.hsrm.mi.swt.controller;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
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

    public LagerController(LagerView lagerView, SpeicherProfil speicherProfil, Stage primaryStage, Map<PrimaryViewName, Pane> primaryViews) {
        this.lagerView = lagerView;
        this.speicherProfil = speicherProfil;
        this.primaryStage = primaryStage;
        this.primaryViews = primaryViews;

        initialize();
    }

    private void initialize() {
        // Raum laden
        // Raum raum = speicherProfil.load();

        // Beispiel: Anzeigen der Raumdetails in der View
        lagerView.getProfileNameField().setText(speicherProfil.getSaveName());

        // Button-Events festlegen
        lagerView.getUndoButton().setOnAction(e -> handleUndo());
        lagerView.getRedoButton().setOnAction(e -> handleRedo());
        lagerView.getSaveButton().setOnAction(e -> handleSave());
        lagerView.getSettingsButton().setOnAction(e -> handleSettings());
        lagerView.getMenuButton().addEventHandler(ActionEvent.ACTION, e -> handleMenuButton());
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

    private void handleMenuButton() {
        System.out.println("Menu button clicked"); // Debugging-Output
        Scene currentScene = primaryStage.getScene();
        Pane nextView = primaryViews.get(PrimaryViewName.StartmenueView);
        if (nextView != null) {
            currentScene.setRoot(nextView);
        } else {
            System.err.println("StartmenueView not found"); // Debugging-Output
        }
    }

    public Pane getRoot() {
        return lagerView;
    }
}
