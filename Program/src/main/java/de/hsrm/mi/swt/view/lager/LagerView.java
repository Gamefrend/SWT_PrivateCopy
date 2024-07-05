package de.hsrm.mi.swt.view.lager;

import de.hsrm.mi.swt.model.storage.RegalBrett;
import de.hsrm.mi.swt.model.storage.Saeule;
import de.hsrm.mi.swt.view.uikomponente.KartonView;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import de.hsrm.mi.swt.model.save.SpeicherProfil;
import de.hsrm.mi.swt.model.storage.Raum;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class LagerView extends StackPane {

    private Pane centerArea;
    private HBox inventoryBox;
    private Label profileNameField;
    private Button undoButton;
    private Button redoButton;
    private Button saveButton;
    private Button settingsButton;
    private Button menuButton;
    private Button brettButton;
    private Button saueleButton;
    private Button skalierenButton;
    private Button moveButton;
    private Label inventarTextField;

    private Button kartonButton;

    private final int CENTRALHIGHT;

    public LagerView() {
        setId("lager-view");
        setPadding(new Insets(20));

        // Textfeld für den Profilnamen
        profileNameField = new Label("Profilname eingeben");
        profileNameField.setMinWidth(200);

        // Buttons
        undoButton = new Button("Undo");
        redoButton = new Button("Redo");
        saveButton = new Button("Speichern");
        settingsButton = new Button("Einstellungen");
        menuButton = new Button("Menu");
        kartonButton= new Button("Karton");

        // Buttons in einer HBox anordnen
        HBox unAndRedoBox = new HBox(10, undoButton, redoButton);
        HBox saveAndSettingsBox = new HBox(10, saveButton, settingsButton);
        HBox buttonBox = new HBox(100, unAndRedoBox, saveAndSettingsBox);
        buttonBox.setAlignment(Pos.CENTER);

        HBox headerBox = new HBox(300, menuButton, profileNameField, buttonBox);
        headerBox.setAlignment(Pos.CENTER);

        // Zentrum
        centerArea = new Pane();
        centerArea.setId("Lager-center-Area");
        centerArea.setStyle("-fx-border-color: black; -fx-background-color: white; -fx-min-height: 400;");
        CENTRALHIGHT = Integer.parseInt(centerArea.getStyle().split(": ")[centerArea.getStyle().split(": ").length -1].replace(";",""));


        // Inventar-Bereich
        inventarTextField = new Label("Inventar");
        inventoryBox = new HBox(10);
        inventoryBox.setId("inventory-box");
        inventoryBox.setAlignment(Pos.CENTER);
        inventoryBox.setPadding(new Insets(10));
        inventoryBox.setStyle("-fx-background-color: #f0f0f0; -fx-border-color: black;");

        brettButton = new Button("Brett");
        saueleButton = new Button("Sauele");
        skalierenButton = new Button("Skalieren");
        moveButton = new Button("Move");

        VBox toolBox = new VBox(brettButton, saueleButton, skalierenButton, moveButton);
        toolBox.setAlignment(Pos.CENTER);
        toolBox.setPadding(new Insets(10));
        toolBox.setSpacing(10);

        VBox preMainLayout = new VBox(180, headerBox, centerArea, inventoryBox);
        preMainLayout.setAlignment(Pos.CENTER);

        // Hauptlayout
        HBox mainLayout = new HBox(toolBox, preMainLayout);

        getChildren().add(mainLayout);

        // Event-Handler für den Karton-Button
        kartonButton.setOnAction(event -> oeffneKartonPopup());
    }

    private void oeffneKartonPopup() {
        // Erstellen des Popup-Fensters
        Stage popupStage = new Stage();
        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.setTitle("Karton hinzufügen");

        // KartonView erstellen
        KartonView kartonView = new KartonView(100, 200, Color.BEIGE, 50, 150);

        // Layout für das Popup-Fenster
        VBox popupLayout = new VBox(10);
        popupLayout.setPadding(new Insets(10));
        popupLayout.setAlignment(Pos.CENTER);
        popupLayout.getChildren().addAll(
                new Label("Karton"),
                kartonView.getRectangle(),
                new Button("OK") {{
                    setOnAction(e -> popupStage.close());
                }}
        );

        Scene popupScene = new Scene(popupLayout, 300, 300);
        popupStage.setScene(popupScene);
        popupStage.showAndWait();
    }

    public void setProfilname(String name) {
        this.profileNameField.setText(name);
    }

    public void bindModel(Raum raum) {
        raum.setOnChangeListener(() -> {
            System.out.println("Raum geändert");
            redraw(raum);
        });
    }

    public void redraw(Raum raum) {
        centerArea.getChildren().clear(); // Clear previous drawings
        int raumHoehe = raum.getHoehe();

        for (RegalBrett brett: raum.getRegal().getRegalBretter()){

            Rectangle rectangle = new Rectangle();
            System.out.println(raum.getRegal().getSaeulen().get(brett.getLueckenIndex()+1).getPositionX()-raum.getRegal().getSaeulen().get(brett.getLueckenIndex()).getPositionX());
            rectangle.setWidth(raum.getRegal().getSaeulen().get(brett.getLueckenIndex()+1).getPositionX()-raum.getRegal().getSaeulen().get(brett.getLueckenIndex()).getPositionX());
            rectangle.setHeight(brett.getDicke());
            rectangle.setFill(Color.BROWN);
            rectangle.setY(brett.getHoehe());
            rectangle.setX(raum.getRegal().getSaeulen().get(brett.getLueckenIndex()).getPositionX());
            centerArea.getChildren().add(rectangle);
        }

        for (Saeule saeule : raum.getRegal().getSaeulen ()) {
            int positionX = saeule.getPositionX();

            // Create a rectangle for each Säule
            Rectangle rectangle = new Rectangle();
            rectangle.setHeight(CENTRALHIGHT); // Höhe der Säule
            rectangle.setWidth(10); // Breite der Säule
            rectangle.setX(positionX); // Position der Säule
            rectangle.setY(5); // Start bei 0 Y-Achse
            rectangle.setFill(Color.GRAY); // Farbe der Säule

            centerArea.getChildren().add(rectangle);
        }

    }

    public Pane getCenterArea() {
        return centerArea;
    }

    public HBox getInventoryBox() {
        return inventoryBox;
    }

    public Label getProfileNameField() {
        return profileNameField;
    }

    public Button getUndoButton() {
        return undoButton;
    }

    public Button getRedoButton() {
        return redoButton;
    }

    public Button getSaveButton() {
        return saveButton;
    }

    public Button getSettingsButton() {
        return settingsButton;
    }

    public Label getInventarTextField() {
        return inventarTextField;
    }

    public Button getMenuButton() {
        return menuButton;
    }

    public Button getBrettButton() {
        return brettButton;
    }

    public Button getSaueleButton() {
        return saueleButton;
    }

    public Button getSkalierenButton() {
        return skalierenButton;
    }

    public Button getMoveButton() {
        return moveButton;
    }
}
