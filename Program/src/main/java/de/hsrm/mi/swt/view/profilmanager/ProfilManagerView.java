package de.hsrm.mi.swt.view.profilmanager;

import javafx.scene.control.Button;
import de.hsrm.mi.swt.model.save.SpeicherProfil;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import java.util.function.BiConsumer;
import java.util.function.Consumer;



public class ProfilManagerView extends VBox {
    private Label headerLabel;
    private ListView<SpeicherProfil> profileView;
    private Button closeButton;
    private Consumer<SpeicherProfil> onDeleteAction;
    private BiConsumer<SpeicherProfil, String> onEditAction;

    public ProfilManagerView() {
        headerLabel = new Label("Profilmanager");
        headerLabel.getStyleClass().addAll("h3");

        closeButton = new Button("X");
        closeButton.getStyleClass().addAll("profil-manager-close-button", "h3");

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        HBox header = new HBox();
        header.getChildren().addAll(headerLabel, spacer, closeButton);
        header.setAlignment(Pos.CENTER_LEFT);
        header.setSpacing(10);
        header.getStyleClass().add("profil-manager-header");

        profileView = new ListView<>();
        profileView.setCellFactory(param -> new ProfilZelle(this::handleDelete, this::handleEdit));

        VBox mainLayout = new VBox(10);
        mainLayout.getChildren().addAll(header, profileView);
        VBox.setVgrow(profileView, Priority.ALWAYS);
        mainLayout.getStyleClass().add("profil-manager-view");

        getChildren().add(mainLayout);
        setPadding(new Insets(40));
        setPrefSize(800, 500);

        getStylesheets().add(getClass().getResource("/css/globals.css").toExternalForm());
        getStylesheets().add(getClass().getResource("/css/profilmanager.css").toExternalForm());
    }

    public ListView<SpeicherProfil> getProfileView() {
        return profileView;
    }

    public Button getCloseButton() {
        return closeButton;
    }

    public void setOnDeleteAction(Consumer<SpeicherProfil> onDeleteAction) {
        this.onDeleteAction = onDeleteAction;
    }

    public void setOnEditAction(BiConsumer<SpeicherProfil, String> onEditAction) {
        this.onEditAction = onEditAction;
    }

    private void handleDelete(SpeicherProfil profile) {
        if (onDeleteAction != null) {
            onDeleteAction.accept(profile);
        }
    }

    private void handleEdit(SpeicherProfil profile, String newName) {
        if (onEditAction != null) {
            onEditAction.accept(profile, newName);
        }
    }
}
