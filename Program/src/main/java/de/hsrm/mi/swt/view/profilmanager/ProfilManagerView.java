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

public class ProfilManagerView extends VBox {
    private Label headerLabel;
    private ListView<SpeicherProfil> profileView;
    private Button closeButton;

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
        profileView.setCellFactory(param -> new ProfilZelle());

        VBox mainLayout = new VBox(10);
        mainLayout.getChildren().addAll(header, profileView);
        VBox.setVgrow(profileView, Priority.ALWAYS);
        mainLayout.getStyleClass().add("profil-manager-view");

        getChildren().add(mainLayout);
        setPadding(new Insets(40));
        setPrefSize(800, 500);

        getStylesheets().add(getClass().getResource("/main/resources/css/globals.css").toExternalForm());
        getStylesheets().add(getClass().getResource("/main/resources/css/profilmanager.css").toExternalForm());
    }

    public ListView<SpeicherProfil> getProfileView() {
        return profileView;
    }

    public Button getCloseButton() {
        return closeButton;
    }
}
