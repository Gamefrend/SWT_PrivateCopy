package de.hsrm.mi.swt.view.profilmanager;

import de.hsrm.mi.swt.model.save.SpeicherProfil;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class ProfilZelle extends ListCell<SpeicherProfil> {
    private Label name;
    private Label datum;
    private BorderPane root;
    private Button editButton;
    private Button deleteButton;
    private Consumer<SpeicherProfil> onDeleteAction;
    private BiConsumer<SpeicherProfil, String> onEditAction;

    public ProfilZelle(Consumer<SpeicherProfil> onDeleteAction, BiConsumer<SpeicherProfil, String> onEditAction) {
        this.onDeleteAction = onDeleteAction;
        this.onEditAction = onEditAction;

        root = new BorderPane();
        root.getStyleClass().add("profil-zelle");

        name = new Label();
        datum = new Label();

        editButton = createButton("/icons/edit.png");
        deleteButton = createButton("/icons/delete.png");

        editButton.setOnAction(event -> startEdit());
        deleteButton.setOnAction(event -> {
            SpeicherProfil item = getItem();
            if (item != null) {
                onDeleteAction.accept(item);
            }
        });

        datum.getStyleClass().add("copy");
        name.getStyleClass().add("h2");

        HBox buttonBox = new HBox(5, editButton, deleteButton);
        buttonBox.setAlignment(Pos.CENTER_RIGHT);

        VBox nameBox = new VBox(10, datum, name);
        nameBox.setAlignment(Pos.CENTER_LEFT);

        root.setLeft(nameBox);
        root.setRight(buttonBox);

        setGraphic(root);
    }

    private Button createButton(String iconPath) {
        Button button = new Button();
        ImageView icon = new ImageView(new Image(getClass().getResourceAsStream(iconPath)));
        icon.setFitWidth(42);
        icon.setFitHeight(42);
        button.setGraphic(icon);
        button.getStyleClass().add("icon-button");
        return button;
    }

    public void startEdit() {
        SpeicherProfil item = getItem();
        if (item != null) {
            TextInputDialog dialog = new TextInputDialog(item.getSaveName());
            dialog.setTitle("Profil umbenennen");
            dialog.setHeaderText("Geben Sie einen neuen Namen für das Profil ein");
            dialog.setContentText("Neuer Name:");

            Optional<String> result = dialog.showAndWait();
            result.ifPresent(newName -> {
                if (!newName.isEmpty()) {
                    onEditAction.accept(item, newName);
                }
            });
        }
    }

    @Override
    protected void updateItem(SpeicherProfil item, boolean empty) {
        super.updateItem(item, empty);
        if (empty || item == null) {
            setGraphic(null);
        } else {
            name.setText(item.getSaveName());
            datum.setText(item.getFormattedDatum());
            setGraphic(root);
        }
    }
}
