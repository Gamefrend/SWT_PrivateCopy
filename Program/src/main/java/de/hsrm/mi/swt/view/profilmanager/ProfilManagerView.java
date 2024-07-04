package main.java.de.hsrm.mi.swt.view.profilmanager;

import javafx.scene.control.Button;
import main.java.de.hsrm.mi.swt.model.save.SpeicherProfil;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

public class ProfilManagerView extends VBox {
    private Label headerLabel;
    ListView<SpeicherProfil> profileView;
    Button menueButton;

    public ProfilManagerView() {
        headerLabel = new Label("Profile");
        profileView = new ListView<>();
        profileView.setCellFactory(param -> new ProfilCell());
        menueButton = new Button("Schließen");

        VBox vbox = new VBox(10);
        vbox.getChildren().addAll(headerLabel, profileView, menueButton);
        vbox.setAlignment(Pos.CENTER);

        getChildren().add(vbox);
        setPadding(new Insets(20));
        setPrefSize(800, 500);
    }

    public ListView<SpeicherProfil> getProfileView() {
        return profileView;
    }

    public Button getMenueButton() {
        return menueButton;
    }
}
