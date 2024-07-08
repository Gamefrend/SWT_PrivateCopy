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
    private Button actionButton;

    public RaumErstellenView(boolean isEditing) {
        setSpacing(40);
        setPadding(new Insets(40));
        if (isEditing) {
            setStyle("-fx-background-color: white; -fx-border-color: #0029FF; -fx-border-width: 2px;");
        } else {
            setStyle("-fx-background-color: white;");
        }

        // Initialize headerLabel first
        headerLabel = new Label();
        headerLabel.getStyleClass().add("h3");

        // Set the text based on isEditing
        headerLabel.setText(isEditing ? "Raum bearbeiten" : "Neues Lagersystem anlegen");

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
        hoeheField.setPromptText("H\u00F6he");
        hoeheField.getStyleClass().addAll("raum-input", "copy");

        breiteField = new TextField();
        breiteField.setPromptText("Breite");
        breiteField.getStyleClass().addAll("raum-input", "copy");

        HBox dimensionInputs = new HBox(10, hoeheField, breiteField);
        dimensionInputs.setAlignment(Pos.CENTER);

        VBox inputFields = new VBox(10, nameField, dimensionInputs);

        actionButton = new Button(isEditing ? "Speichern" : "Lagersystem anlegen");
        actionButton.getStyleClass().addAll("raum-erstellen-create-button", "copy");

        getChildren().addAll(header, inputFields, actionButton);

        getStylesheets().add(getClass().getResource("/css/globals.css").toExternalForm());
        getStylesheets().add(getClass().getResource("/css/lager.css").toExternalForm());
    }

    public void setValues(String name, int hoehe, int breite) {
        nameField.setText(name);
        hoeheField.setText(String.valueOf(hoehe));
        breiteField.setText(String.valueOf(breite));
    }

    public void clearValues() {
        nameField.clear();
        hoeheField.clear();
        breiteField.clear();
    }

    public void setInvalidInput(TextField field) {
        field.getStyleClass().add("invalid-input");
    }

    public void clearInvalidInput(TextField field) {
        field.getStyleClass().remove("invalid-input");
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

    public Button getActionButton() {
        return actionButton;
    }
}
