package main.java.de.hsrm.mi.swt.view.profilmanager;

import javafx.scene.control.Button;
import main.java.de.hsrm.mi.swt.model.save.SpeicherProfil;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class ProfilManagerView extends BorderPane {
    private Label headerLabel;
    ListView<SpeicherProfil> profileView;

    Button menueButton;

    public ProfilManagerView() {
        headerLabel = new Label("Profile");
        profileView = new ListView<>();
        profileView.setCellFactory(param -> new ProfilCell());
        this.setCenter(profileView);

        menueButton = new Button("Menu");

        BorderPane.setAlignment(headerLabel, Pos.CENTER);
        BorderPane.setMargin(headerLabel, new Insets(10));
        VBox vbox = new VBox(10);
        vbox.getChildren().addAll(menueButton, headerLabel, profileView);
        vbox.setAlignment(Pos.CENTER);

        StackPane root = new StackPane();
        root.getChildren().add(vbox);

        setCenter(root);
    }

    public ListView<SpeicherProfil> getProfileView() {
        return profileView;
    }

    public Button getMenueButton() {
        return menueButton;
    }
}
