package de.hsrm.mi.swt.view.profilmanager;

import de.hsrm.mi.swt.model.save.SpeicherProfil;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;

import java.util.function.Consumer;

public class ProfilZelle extends ListCell<SpeicherProfil> {
    private Label name;
    private Label datum;
    private HBox root;
    private VBox infoPane;
    private Button editButton;
    private Button deleteButton;
    private Consumer<SpeicherProfil> onDeleteAction;

    public ProfilZelle(Consumer<SpeicherProfil> onDeleteAction) {
        this.onDeleteAction = onDeleteAction;
        root = new HBox();
        root.getStyleClass().add("profil-zelle");

        infoPane = new VBox();
        infoPane.getStyleClass().add("info-pane");

        name = new Label();
        datum = new Label();

        editButton = new Button();
        deleteButton = new Button();

        // Set icons for buttons
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

        datum.getStyleClass().add("copy");
        name.getStyleClass().add("h2");

        // Create a spacer to push buttons to the right
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        HBox buttonBox = new HBox(editButton, deleteButton);
        buttonBox.getStyleClass().add("button-box");

        HBox nameBox = new HBox(name, spacer, buttonBox);
        nameBox.getStyleClass().add("name-box");
        HBox.setHgrow(name, Priority.ALWAYS);
        HBox.setHgrow(buttonBox, Priority.NEVER);

        infoPane.getChildren().addAll(datum, nameBox);
        root.getChildren().add(infoPane);

        // Bind the width of the root to the width of the ListView
        root.prefWidthProperty().bind(this.widthProperty());

        setGraphic(root);
    }

    @Override
    protected void updateItem(SpeicherProfil item, boolean empty) {
        super.updateItem(item, empty);
        if (empty || item == null) {
            name.setText(null);
            datum.setText(null);
            setGraphic(null);
        } else {
            name.setText(item.getSaveName());
            datum.setText(item.getFormattedDatum());
            setGraphic(root);
        }
    }
}