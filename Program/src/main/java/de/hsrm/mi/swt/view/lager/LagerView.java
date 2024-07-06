package de.hsrm.mi.swt.view.lager;

import de.hsrm.mi.swt.model.storage.*;
import de.hsrm.mi.swt.view.uikomponente.Karton;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;

import java.time.LocalDate;


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
        kartonButton = new Button("Karton");

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
        CENTRALHIGHT = Integer.parseInt(centerArea.getStyle().split(": ")[centerArea.getStyle().split(": ").length - 1].replace(";", ""));

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

        VBox toolBox = new VBox(brettButton, saueleButton, kartonButton, skalierenButton, moveButton);
        toolBox.setAlignment(Pos.CENTER);
        toolBox.setPadding(new Insets(10));
        toolBox.setSpacing(10);

        VBox preMainLayout = new VBox(180, headerBox, centerArea, inventoryBox);
        preMainLayout.setAlignment(Pos.CENTER);

        // Hauptlayout
        HBox mainLayout = new HBox(toolBox, preMainLayout);
        getChildren().add(mainLayout);

        

        getStylesheets().add(getClass().getResource("/css/globals.css").toExternalForm());
        getStylesheets().add(getClass().getResource("/css/lager.css").toExternalForm());
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
        centerArea.getChildren().clear();
        int raumHoehe = raum.getHoehe();

        for (RegalBrett brett : raum.getRegal().getRegalBretter()) {
            int xPosition = 0;
            xPosition = raum.getRegal().getSaeulen().get(brett.getLueckenIndex()).getPositionX();
            Rectangle rectangle = new Rectangle();
            System.out.println(raum.getRegal().getSaeulen().get(brett.getLueckenIndex() + 1).getPositionX() - raum.getRegal().getSaeulen().get(brett.getLueckenIndex()).getPositionX());
            rectangle.setWidth(raum.getRegal().getSaeulen().get(brett.getLueckenIndex() + 1).getPositionX() - raum.getRegal().getSaeulen().get(brett.getLueckenIndex()).getPositionX());
            rectangle.setHeight(brett.getDicke());
            rectangle.setFill(Color.BROWN);
            rectangle.setY(brett.getHoehe());
            rectangle.setX(xPosition);
            centerArea.getChildren().add(rectangle);

            for (Karton karton : brett.getKartons()){
                rectangle = new Rectangle();
                rectangle.setHeight(karton.getHeight()); // Höhe der Säule
                rectangle.setWidth(karton.getWidth()); // Breite der Säule
                rectangle.setX(xPosition + karton.getXPosition()); // Position der Säule
                rectangle.setY(brett.getHoehe()-karton.getHeight()); // Start bei 0 Y-Achse
                rectangle.setFill(Color.RED); // Farbe der Säule
                centerArea.getChildren().add(rectangle);
            }
        }

        for (Saeule saeule : raum.getRegal().getSaeulen()) {
            int positionX = saeule.getPositionX();
            // Create a rectangle for each Säule
            Rectangle rectangle = new Rectangle();
            rectangle.setHeight(CENTRALHIGHT);
            rectangle.setWidth(10);
            rectangle.setX(positionX);
            rectangle.setY(5);
            rectangle.setFill(Color.GRAY);
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

    public Button getKartonButton() {
        return kartonButton;
    }
}
