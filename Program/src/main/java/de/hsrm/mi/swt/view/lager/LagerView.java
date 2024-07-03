package main.java.de.hsrm.mi.swt.view.lager;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class LagerView extends StackPane {

    private Pane centerArea;
    private HBox inventoryBox;
    private TextField profileNameField;
    private Button undoButton;
    private Button redoButton;
    private Button saveButton;
    private Button settingsButton;
    private TextField inventarTextField;

    public LagerView() {
        setId("lager-view");
        setPadding(new Insets(20));

        // Textfeld fÃ¼r den Profilnamen
        profileNameField = new TextField();
        profileNameField.setPromptText("Profilname eingeben");
        profileNameField.setMinWidth(200);

        // Buttons
        undoButton = new Button("Undo");
        redoButton = new Button("Redo");
        saveButton = new Button("Speichern");
        settingsButton = new Button("Einstellungen");

        // Buttons in einer HBox anordnen
        HBox  unAndRedoBox= new HBox(10, undoButton, redoButton);
        HBox saveAndSettingsBox= new HBox(10, saveButton, settingsButton);
        HBox buttonBox = new HBox(100, unAndRedoBox , saveAndSettingsBox);
        buttonBox.setAlignment(Pos.CENTER);

        HBox headerBox = new HBox(300, profileNameField, buttonBox);
        headerBox.setAlignment(Pos.CENTER);


        // Zentrum
        centerArea = new Pane();
        centerArea.setId("Lager-center-Area");
        centerArea.setStyle("-fx-border-color: black; -fx-background-color: white; -fx-min-height: 400px;");

        // Inventar-Bereich
        inventarTextField = new TextField("Inventar");
        inventoryBox = new HBox(10);
        inventoryBox.setId("inventory-box");
        inventoryBox.setAlignment(Pos.CENTER);
        inventoryBox.setPadding(new Insets(10));
        inventoryBox.setStyle("-fx-background-color: #f0f0f0; -fx-border-color: black;");

        // Hauptlayout
        VBox mainLayout = new VBox(180,headerBox, centerArea, inventoryBox);
        mainLayout.setAlignment(Pos.CENTER);

        getChildren().add(mainLayout);


    }

    public Pane getCenterArea() {
        return centerArea;
    }

    public HBox getInventoryBox() {
        return inventoryBox;
    }

    public TextField getProfileNameField() {
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
    public TextField getInventarTextField() {
        return inventarTextField;
    }

}