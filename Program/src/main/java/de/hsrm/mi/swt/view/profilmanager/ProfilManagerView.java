package main.java.de.hsrm.mi.swt.view.profilmanager;

import javafx.scene.control.Button;
import main.java.de.hsrm.mi.swt.model.save.SpeicherProfil;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

public class ProfilManagerView extends VBox {
    private Label headerLabel;
    private ListView<SpeicherProfil> profileView;
    private Button closeButton;

    public ProfilManagerView() {
        // Header erstellen
        headerLabel = new Label("Profilmanager");
        headerLabel.getStyleClass().add("profil-manager-title");

        closeButton = new Button("X");
        closeButton.getStyleClass().add("profil-manager-close-button");

        HBox header = new HBox();
        header.getChildren().addAll(headerLabel, closeButton);
        header.setAlignment(Pos.CENTER_LEFT);
        HBox.setHgrow(headerLabel, Priority.ALWAYS);
        header.setSpacing(10);
        header.getStyleClass().add("profil-manager-header");

        // ListView erstellen
        profileView = new ListView<>();
        profileView.setCellFactory(param -> new ProfilZelle());

        // Hauptlayout
        VBox mainLayout = new VBox(10);
        mainLayout.getChildren().addAll(header, profileView);
        VBox.setVgrow(profileView, Priority.ALWAYS);
        mainLayout.getStyleClass().add("profil-manager-view");

        getChildren().add(mainLayout);
        setPadding(new Insets(40));
        setPrefSize(800, 500);

        // Laden der CSS-Datei
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
