package main.java.de.hsrm.mi.swt.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.BorderPane;
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

        BorderPane header = new BorderPane();
        header.setLeft(titleLabel);
        header.setRight(versionLabel);
        header.setPadding(new Insets(10));

        VBox buttonBox = new VBox(10); // Dynamischer Abstand

        // Laden des Pfeilbildes
        Image arrowImage = new Image(getClass().getResourceAsStream("/icons/arrow.png"));
        ImageView arrowImageView = new ImageView(arrowImage);
        arrowImageView.setFitHeight(40);
        arrowImageView.setFitWidth(40);
        arrowImageView.setPreserveRatio(true);
        arrowImageView.setSmooth(true);
        arrowImageView.maxWidth(40);
        arrowImageView.maxHeight(40);


        // Erstellen der Buttons mit Bild
        newSystemButton = createButton("NEUES LAGERSYSTEM", arrowImageView);
        loadProfileButton = createButton("PROFIL LADEN", arrowImageView);
        manageProfileButton = createButton("PROFILMANAGER", arrowImageView);

        buttonBox.getChildren().addAll(newSystemButton, loadProfileButton, manageProfileButton);
        this.getChildren().addAll(header, buttonBox);
        this.setSpacing(20);
        this.setPadding(new Insets(20));
        this.setAlignment(Pos.CENTER_LEFT);
    }

    private Button createButton(String text, ImageView imageView) {
        Button button = new Button(text, new ImageView(imageView.getImage())); // Stellt sicher, dass jede Instanz ihre eigene ImageView hat
        button.setGraphicTextGap(10); // Abstand zwischen Text und Bild
        button.setMaxWidth(Double.MAX_VALUE); // Ermöglicht es dem Button, sich zu dehnen
        return button;
    }

    private void openTestView() {
        TestView view = new TestView();
        rootContainer.getChildren().clear();
        rootContainer.getChildren().add(view);
    }
}
