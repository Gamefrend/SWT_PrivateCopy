package de.hsrm.mi.swt.view;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

public class Hauptview extends HBox {

    private Button erstesButton;
    private Button zweitesButton;
    private Button drittesButton;

    private StackPane rootContainer;

    public Hauptview(StackPane rootContainer) {
        this.rootContainer = rootContainer; // Übergebenes rootContainer zuweisen

        erstesButton = new Button("NEUES LAGERSYSTEM");
        erstesButton.setPrefSize(110, 40);
        erstesButton.setOnAction(event -> openTestView());

        zweitesButton = new Button("PROFIL LADEN");
        zweitesButton.setPrefSize(110, 40);

        drittesButton = new Button("PROFILMANAGER");
        drittesButton.setPrefSize(110, 40);

        getChildren().addAll(erstesButton, zweitesButton, drittesButton);

        setAlignment(Pos.CENTER);
        setSpacing(15);
        setId("control-view");
    }

    private void openTestView() {
        TestView view = new TestView();
        rootContainer.getChildren().clear(); // Löschen Sie alle aktuellen Inhalte
        rootContainer.getChildren().add(view); // Fügen Sie die neue View hinzu
    }
}
