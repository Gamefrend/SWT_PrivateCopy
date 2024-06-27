// mainMenuView.java

package main.java.de.hsrm.mi.swt.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class mainMenuView extends VBox {

    private Button newSystemButton;
    private Button loadProfileButton;
    private Button manageProfileButton;

    private StackPane rootContainer;

    public mainMenuView(StackPane rootContainer) {
        this.rootContainer = rootContainer;

        Label titleLabel = new Label("STORAGESHELVES");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));

        Label versionLabel = new Label("v1.0");
        versionLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 16));

        HBox header = new HBox(titleLabel, versionLabel);
        header.setSpacing(800); // Adjust this based on your actual UI size
        header.setAlignment(Pos.TOP_LEFT);

        newSystemButton = new Button("NEUES LAGERSYSTEM →");
        newSystemButton.setPrefSize(300, 70);

        loadProfileButton = new Button("PROFIL LADEN →");
        loadProfileButton.setPrefSize(300, 70);

        manageProfileButton = new Button("PROFILMANAGER →");
        manageProfileButton.setPrefSize(300, 70);

        this.getChildren().addAll(header, newSystemButton, loadProfileButton, manageProfileButton);
        this.setSpacing(20);
        this.setPadding(new Insets(20));
        this.setAlignment(Pos.CENTER_LEFT);
    }

    private void openTestView() {
        TestView view = new TestView();
        rootContainer.getChildren().clear();
        rootContainer.getChildren().add(view);
    }
}
