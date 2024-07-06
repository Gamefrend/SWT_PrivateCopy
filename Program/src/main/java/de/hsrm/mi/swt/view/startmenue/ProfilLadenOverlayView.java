package de.hsrm.mi.swt.view.startmenue;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class ProfilLadenOverlayView extends StackPane {

    private TextField inputField;
    private Button ladenButton;

    public ProfilLadenOverlayView() {
        // Hintergrund mit 60% Opazität
        setId("overlay-background");

        // Weißer Kasten
        VBox whiteBox = new VBox(40); // 2.5rem = 40px
        whiteBox.setId("overlay-white-box");
        whiteBox.setAlignment(Pos.CENTER_LEFT);

        // Header
        HBox header = new HBox();
        header.setAlignment(Pos.CENTER_LEFT);
        header.setId("overlay-header");

        Label titleLabel = new Label("ARTIKEL ERSTELLEN");
        titleLabel.getStyleClass().add("overlay-header-text");

        Button closeButton = new Button("X");
        closeButton.setId("overlay-close-button");
        closeButton.setOnAction(e -> setVisible(false));

        header.getChildren().addAll(titleLabel, closeButton);
        HBox.setHgrow(titleLabel, javafx.scene.layout.Priority.ALWAYS);

        // Input Feld
        inputField = new TextField();
        inputField.setPromptText("Eingabe hier...");
        inputField.getStyleClass().add("overlay-input");

        // Laden Button
        ladenButton = new Button("Laden");
        ladenButton.getStyleClass().add("overlay-laden-button");

        // Fügen Sie alle Elemente zum weißen Kasten hinzu
        whiteBox.getChildren().addAll(header, inputField, ladenButton);

        // Fügen Sie den weißen Kasten zum Overlay hinzu
        getChildren().add(whiteBox);

        // Zentrieren Sie den weißen Kasten
        StackPane.setAlignment(whiteBox, Pos.CENTER);

        // Overlay initial unsichtbar machen
        setVisible(false);
    }

    public Button getLadenButton() {
        return ladenButton;
    }

    public TextField getInputField() {
        return inputField;
    }
}
