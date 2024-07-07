package de.hsrm.mi.swt.view.lager;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;

public class RaumErstellenView extends VBox {
    private Label headerLabel;
    private Button closeButton;
    private TextField nameField;
    private TextField hoeheField;
    private TextField breiteField;
    private Button createButton;

    public RaumErstellenView() {
        setSpacing(40);
        setPadding(new Insets(40));
        setStyle("-fx-background-color: white;");

        headerLabel = new Label("Neues Lagersystem anlegen");
        headerLabel.getStyleClass().add("h3");

        closeButton = new Button("X");
        closeButton.getStyleClass().addAll("h3", "raum-erstellen-close-button");

        HBox header = new HBox();
        header.getChildren().addAll(headerLabel, new javafx.scene.layout.Region(), closeButton);
        header.setAlignment(Pos.CENTER_LEFT);
        HBox.setHgrow(headerLabel, Priority.ALWAYS);
        HBox.setHgrow(header.getChildren().get(1), Priority.ALWAYS);
        header.getStyleClass().add("raum-erstellen-header");

        nameField = new TextField();
        nameField.setPromptText("Name des Lagersystems");
        nameField.getStyleClass().addAll("raum-input", "copy");

        hoeheField = new TextField();
        hoeheField.setPromptText("Höhe");
        hoeheField.getStyleClass().addAll("raum-input", "copy");

        breiteField = new TextField();
        breiteField.setPromptText("Breite");
        breiteField.getStyleClass().addAll("raum-input", "copy");

        HBox dimensionInputs = new HBox(10, hoeheField, breiteField);
        dimensionInputs.setAlignment(Pos.CENTER);

        VBox inputFields = new VBox(10, nameField, dimensionInputs);

        createButton = new Button("Lagersystem anlegen");
        createButton.getStyleClass().addAll("raum-erstellen-create-button", "copy");

        getChildren().addAll(header, inputFields, createButton);

        getStylesheets().add(getClass().getResource("/css/globals.css").toExternalForm());
        getStylesheets().add(getClass().getResource("/css/lager.css").toExternalForm());
    }

    public Button getCloseButton() {
        return closeButton;
    }

    public TextField getNameField() {
        return nameField;
    }

    public TextField getHoeheField() {
        return hoeheField;
    }

    public TextField getBreiteField() {
        return breiteField;
    }

    public Button getCreateButton() {
        return createButton;
    }
}
