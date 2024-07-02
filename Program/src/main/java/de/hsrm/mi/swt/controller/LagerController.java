package main.java.de.hsrm.mi.swt.controller;


import main.java.de.hsrm.mi.swt.model.save.SpeicherProfil;
import main.java.de.hsrm.mi.swt.model.storage.Raum;
import main.java.de.hsrm.mi.swt.view.lager.LagerView;

public class LagerController {

    private LagerView lagerView;
    private SpeicherProfil speicherProfil;

    public LagerController(LagerView lagerView, SpeicherProfil speicherProfil) {
        this.lagerView = lagerView;
        this.speicherProfil = speicherProfil;

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
}
