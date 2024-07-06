package de.hsrm.mi.swt.view.lager;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
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
        headerLabel = new Label("Neues Lagersystem anlegen");
        headerLabel.getStyleClass().add("h3");

        closeButton = new Button("X");
        closeButton.getStyleClass().add("raum-erstellen-close-button");

        HBox header = new HBox(headerLabel, closeButton);
        header.setAlignment(Pos.CENTER_LEFT);
        HBox.setHgrow(headerLabel, javafx.scene.layout.Priority.ALWAYS);

        nameField = new TextField();
        nameField.setPromptText("Name des Lagersystems");

        hoeheField = new TextField();
        hoeheField.setPromptText("Höhe");

        breiteField = new TextField();
        breiteField.setPromptText("Breite");

        createButton = new Button("Lagersystem anlegen");
        createButton.getStyleClass().add("raum-erstellen-create-button");

        getChildren().addAll(header, nameField, hoeheField, breiteField, createButton);
        setPadding(new Insets(40));
        setPrefSize(800, 500);

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
