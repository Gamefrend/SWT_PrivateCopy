package main.java.de.hsrm.mi.swt.view.startmenue;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

public class hauptmenueView extends BorderPane {

    private Button newSystemButton;
    private Button loadProfileButton;
    private Button manageProfileButton;

    public hauptmenueView() {
        // Header (unverändert)
        Label titleLabel = new Label("STORAGESHELVES");
        titleLabel.getStyleClass().add("header-label");

        Label versionLabel = new Label("v1.0");
        versionLabel.getStyleClass().add("header-label");

        BorderPane header = new BorderPane();
        header.setLeft(titleLabel);
        header.setRight(versionLabel);

        // Buttons
        VBox buttonBox = new VBox(50);
        buttonBox.setAlignment(Pos.CENTER_LEFT);
        buttonBox.getStyleClass().add("button-container");

        Image arrowImage = new Image(getClass().getResourceAsStream("/main/resources/icons/arrow.png"));

        newSystemButton = createButton("NEUES LAGERSYSTEM", arrowImage);
        loadProfileButton = createButton("PROFIL LADEN", arrowImage);
        manageProfileButton = createButton("PROFILMANAGER", arrowImage);

        buttonBox.getChildren().addAll(newSystemButton, loadProfileButton, manageProfileButton);

        // Layout
        setTop(header);
        setCenter(buttonBox);
        setPadding(new Insets(40));

        // CSS laden
        String cssPath = getClass().getResource("/main/resources/css/style.css").toExternalForm();
        getStylesheets().add(cssPath);

        // Fügen Sie einen Listener für Größenänderungen hinzu
        widthProperty().addListener((obs, oldVal, newVal) -> {
            adjustLayout(newVal.doubleValue());
        });
    }

    private Button createButton(String text, Image image) {
        Button button = new Button();
        Label label = new Label(text);
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(40);
        imageView.setFitWidth(40);

        HBox content = new HBox(10);
        content.setAlignment(Pos.CENTER_LEFT);
        content.getChildren().addAll(label, imageView);

        button.setGraphic(content);
        button.getStyleClass().add("custom-button");
        return button;
    }

    private void adjustLayout(double width) {
        if (width < 600) {
            getStyleClass().add("small-layout");
        } else {
            getStyleClass().remove("small-layout");
        }
    }

    public Button getNewSystemButton() {
        return newSystemButton;
    }
}
