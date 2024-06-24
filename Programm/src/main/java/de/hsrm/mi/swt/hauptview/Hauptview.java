package de.hsrm.mi.swt.hauptview;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class Hauptview extends HBox {

    public Button erstesButton;
    public Button zweitesButton;
    public Button drittesButton;

    public Hauptview() {
        erstesButton = new Button("NEUES LAGERSYSTEM");
        erstesButton.setPrefSize(110, 40);

        zweitesButton = new Button("PROFIL LADEN");
        zweitesButton.setPrefSize(110, 40);

        drittesButton = new Button("PROFILMANAGER");
        drittesButton.setPrefSize(110, 40);

        getChildren().addAll(erstesButton, zweitesButton, drittesButton);

        setAlignment(Pos.CENTER);
        setSpacing(15);
        setId("control-view");

    }

}
