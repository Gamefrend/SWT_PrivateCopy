package de.hsrm.mi.swt.view.lager;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class KartonErstellenView extends VBox {
    private Label headerLabel;
    private Button closeButton;
    private TextField nameField, anzahlField, breiteField, hoeheField, gewichtField, mhdField;
    private CheckBox lebensmittelCheck, giftigCheck, gekuehltCheck;
    private Button createButton;

    public KartonErstellenView() {
        setSpacing(40);
        setPadding(new Insets(40));
        setStyle("-fx-background-color: white;");

        headerLabel = new Label("Neuen Artikel erstellen");
        headerLabel.getStyleClass().add("h3");

        closeButton = new Button("X");
        closeButton.getStyleClass().addAll("h3", "raum-erstellen-close-button");

        HBox header = new HBox();
        header.getChildren().addAll(headerLabel, new javafx.scene.layout.Region(), closeButton);
        header.setAlignment(Pos.CENTER_LEFT);
        HBox.setHgrow(headerLabel, Priority.ALWAYS);
        HBox.setHgrow(header.getChildren().get(1), Priority.ALWAYS);
        header.getStyleClass().add("raum-erstellen-header");

        nameField = createTextField("Name");
        anzahlField = createTextField("Anzahl");
        breiteField = createTextField("Breite");
        hoeheField = createTextField("Höhe");
        gewichtField = createTextField("Gewicht");
        mhdField = createTextField("MHD (YYYY-MM-DD)");

        HBox row1 = new HBox(10, nameField, anzahlField);
        HBox row2 = new HBox(10, breiteField, hoeheField);
        HBox row3 = new HBox(10, gewichtField, mhdField);

        VBox inputs = new VBox(10, row1, row2, row3);

        Label eigenschaften = new Label("Eigenschaften:");
        eigenschaften.getStyleClass().addAll("copy", "h3");

        lebensmittelCheck = createCheckBox("Lebensmittel");
        giftigCheck = createCheckBox("Giftig");
        gekuehltCheck = createCheckBox("Gekühlt");

        VBox checkboxes = new VBox(10, eigenschaften,
                createCheckBoxWithLabel(lebensmittelCheck, "Lebensmittel"),
                createCheckBoxWithLabel(giftigCheck, "Giftig"),
                createCheckBoxWithLabel(gekuehltCheck, "Gekühlt")
        );

        createButton = new Button("Artikel erstellen");
        createButton.getStyleClass().addAll("raum-erstellen-create-button", "copy");

        getChildren().addAll(header, inputs, checkboxes, createButton);

        getStylesheets().add(getClass().getResource("/css/globals.css").toExternalForm());
        getStylesheets().add(getClass().getResource("/css/lager.css").toExternalForm());
    }

    private TextField createTextField(String prompt) {
        TextField field = new TextField();
        field.setPromptText(prompt);
        field.getStyleClass().addAll("raum-input", "copy");
        return field;
    }

    private CheckBox createCheckBox(String text) {
        CheckBox checkBox = new CheckBox();
        checkBox.getStyleClass().add("custom-checkbox");
        return checkBox;
    }

    private HBox createCheckBoxWithLabel(CheckBox checkBox, String text) {
        Label label = new Label(text);
        label.getStyleClass().add("copy");

        HBox hbox = new HBox(10, checkBox, label);
        return hbox;
    }

    public Button getCloseButton() { return closeButton; }
    public TextField getNameField() { return nameField; }
    public TextField getAnzahlField() { return anzahlField; }
    public TextField getBreiteField() { return breiteField; }
    public TextField getHoeheField() { return hoeheField; }
    public TextField getGewichtField() { return gewichtField; }
    public TextField getMhdField() { return mhdField; }
    public CheckBox getLebensmittelCheck() { return lebensmittelCheck; }
    public CheckBox getGiftigCheck() { return giftigCheck; }
    public CheckBox getGekuehltCheck() { return gekuehltCheck; }
    public Button getCreateButton() { return createButton; }
}
