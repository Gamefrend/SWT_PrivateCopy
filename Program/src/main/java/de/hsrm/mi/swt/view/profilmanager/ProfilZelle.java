package de.hsrm.mi.swt.view.profilmanager;

import de.hsrm.mi.swt.model.save.SpeicherProfil;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class ProfilZelle extends ListCell<SpeicherProfil> {
    private Label name;
    private Label datum;
    private HBox root;
    private VBox infoPane;
    private Button editButton;
    private Button deleteButton;
    private TextField nameField;
    private Consumer<SpeicherProfil> onDeleteAction;
    private BiConsumer<SpeicherProfil, String> onEditAction;

    public ProfilZelle(Consumer<SpeicherProfil> onDeleteAction, BiConsumer<SpeicherProfil, String> onEditAction) {
        this.onDeleteAction = onDeleteAction;
        this.onEditAction = onEditAction;

        root = new HBox();
        root.getStyleClass().add("profil-zelle");

        infoPane = new VBox();
        infoPane.getStyleClass().add("info-pane");

        name = new Label();
        datum = new Label();
        nameField = new TextField();
        nameField.setVisible(false);

        editButton = new Button();
        deleteButton = new Button();

        ImageView editIcon = new ImageView(new Image(getClass().getResourceAsStream("/icons/edit.png")));
        ImageView deleteIcon = new ImageView(new Image(getClass().getResourceAsStream("/icons/delete.png")));
        editIcon.setFitWidth(44);
        editIcon.setFitHeight(44);
        deleteIcon.setFitWidth(44);
        deleteIcon.setFitHeight(44);

        editButton.setGraphic(editIcon);
        deleteButton.setGraphic(deleteIcon);
        editButton.getStyleClass().add("edit-button");
        deleteButton.getStyleClass().add("delete-button");

        deleteButton.setOnAction(event -> {
            SpeicherProfil item = getItem();
            if (item != null) {
                onDeleteAction.accept(item);
            }
        });

        editButton.setOnAction(event -> startEdit());

        nameField.setOnAction(e -> commitEdit());

        datum.getStyleClass().add("copy");
        name.getStyleClass().add("h2");

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        HBox buttonBox = new HBox(editButton, deleteButton);
        buttonBox.getStyleClass().add("button-box");

        HBox nameBox = new HBox(name, nameField, spacer, buttonBox);
        nameBox.getStyleClass().add("name-box");
        HBox.setHgrow(name, Priority.ALWAYS);
        HBox.setHgrow(buttonBox, Priority.NEVER);

        infoPane.getChildren().addAll(datum, nameBox);
        root.getChildren().add(infoPane);

        root.prefWidthProperty().bind(this.widthProperty());

        setGraphic(root);
    }

    public void startEdit() {
        SpeicherProfil item = getItem();
        if (item != null) {
            nameField.setText(item.getSaveName());
            name.setVisible(false);
            nameField.setVisible(true);
            nameField.requestFocus();
        }
    }

    private void commitEdit() {
        SpeicherProfil item = getItem();
        if (item != null) {
            String newName = nameField.getText();
            if (!newName.isEmpty()) {
                onEditAction.accept(item, newName);
            }
            name.setVisible(true);
            nameField.setVisible(false);
        }
    }

    @Override
    protected void updateItem(SpeicherProfil item, boolean empty) {
        super.updateItem(item, empty);
        if (empty || item == null) {
            name.setText(null);
            datum.setText(null);
            nameField.setVisible(false);
            setGraphic(null);
        } else {
            name.setText(item.getSaveName());
            datum.setText(item.getFormattedDatum());
            nameField.setVisible(false);
            setGraphic(root);
        }
    }
}
