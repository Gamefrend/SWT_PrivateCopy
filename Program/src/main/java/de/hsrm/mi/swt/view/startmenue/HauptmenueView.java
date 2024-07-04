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

public class HauptmenueView extends BorderPane {

    private ProfilLadenOverlayView overlay;
    private Button newSystemButton;
    private Button loadProfileButton;
    private Button manageProfileButton;
    private Label titleLabel;
    private Label versionLabel;

    public HauptmenueView() {
        // Header
        titleLabel = new Label("STORAGESHELVES");
        titleLabel.getStyleClass().addAll("h2");

        versionLabel = new Label("v1.0");
        versionLabel.getStyleClass().addAll("h2");

        BorderPane header = new BorderPane();
        header.setLeft(titleLabel);
        header.setRight(versionLabel);

        VBox buttonBox = new VBox(50);
        buttonBox.setAlignment(Pos.CENTER_LEFT);
        buttonBox.getStyleClass().add("button-container");

        Image arrowImage = new Image(getClass().getResourceAsStream("/icons/arrow.png"));
        newSystemButton = createButton("NEUES LAGERSYSTEM", arrowImage);

        loadProfileButton = createButton("LETZTES PROFIL LADEN", arrowImage);

        manageProfileButton = createButton("PROFILMANAGER", arrowImage);

        buttonBox.getChildren().addAll(newSystemButton, loadProfileButton, manageProfileButton);

        setTop(header);
        setCenter(buttonBox);
        setPadding(new Insets(40));

        // CSS laden
        String cssPath = getClass().getResource("/css/style.css").toExternalForm();
        getStylesheets().add(cssPath);

        adjustLayout(getWidth());

        widthProperty().addListener((obs, oldVal, newVal) -> {
            adjustLayout(newVal.doubleValue());
        });
    }

    private Button createButton(String text, Image image) {
        Button button = new Button();
        Label label = new Label(text);
        label.getStyleClass().add("h1");
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
        getStyleClass().removeAll("small-layout", "medium-layout", "large-layout");
        if (width < 600) {
            getStyleClass().add("small-layout");
        } else if (width < 1000) {
            getStyleClass().add("medium-layout");
        } else {
            getStyleClass().add("large-layout");
        }
    }

    public void setOverlay(ProfilLadenOverlayView overlay) {
        this.overlay = overlay;
        getChildren().add(overlay);
    }
    private void showOverlay() {
        if (overlay != null) {
            overlay.setVisible(true);
        }
    }

    public Button getNewSystemButton() {
        return newSystemButton;
    }

    public Button getManageProfileButton() {
        return manageProfileButton;
    }

    public Button getLoadProfileButton() {
        return loadProfileButton;
    }
}
