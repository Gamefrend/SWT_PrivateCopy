package de.hsrm.mi.swt.view.lager;

import de.hsrm.mi.swt.model.storage.*;
import de.hsrm.mi.swt.view.uikomponente.Karton;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;

public class LagerView extends StackPane {
    private Pane centerArea;
    private HBox inventoryBox;
    private Label profileNameField;
    private Button undoButton;
    private Button redoButton;
    private Button saveButton;
    private Button settingsButton;
    private Button menuButton;
    private Button brettButton;
    private Button saueleButton;
    private Button deleteButton;
    private Label inventarTextField;
    private Button kartonButton;
    private final int CENTRALHIGHT;
    private Rectangle brettRectangle;
    private Rectangle saeuleRectangle;
    private ArrayList<Rectangle> allesSeuleRectangle;
    private Rectangle kartonRectangle;
    private Button addKartonButton;

    public LagerView() {
        setId("lager-view");
        setPadding(new Insets(20));

        // Textfeld für den Profilnamen
        profileNameField = new Label("Profilname eingeben");
        profileNameField.setMinWidth(200);

        // Buttons
        undoButton = new Button("Undo");
        redoButton = new Button("Redo");
        saveButton = new Button("Speichern");
        settingsButton = new Button("Einstellungen");
        menuButton = new Button("Menu");
        kartonButton = new Button("Karton");

        setButtonIcon(menuButton, "/icons/home.png");
        setButtonIcon(undoButton, "/icons/undo.png");
        setButtonIcon(redoButton, "/icons/redo.png");
        setButtonIcon(saveButton, "/icons/save.png");
        setButtonIcon(settingsButton, "/icons/settings.png");

        // Buttons in einer HBox anordnen
        HBox unAndRedoBox = new HBox(10, undoButton, redoButton);
        HBox saveAndSettingsBox = new HBox(10, saveButton, settingsButton);
        HBox buttonBox = new HBox(100, unAndRedoBox, saveAndSettingsBox);
        buttonBox.setAlignment(Pos.CENTER);

        HBox headerBox = new HBox(300, menuButton, profileNameField, buttonBox);
        headerBox.setAlignment(Pos.CENTER);

        // Zentrum
        centerArea = new Pane();
        centerArea.setId("Lager-center-Area");
        centerArea.setStyle("-fx-border-color: black; -fx-background-color: white; -fx-min-height: 400;");
        CENTRALHIGHT = Integer.parseInt(centerArea.getStyle().split(": ")[centerArea.getStyle().split(": ").length - 1].replace(";", ""));

        // Create and set up the addKartonButton
        addKartonButton = new Button("+");

        // Inventar-Bereich
        inventarTextField = new Label("Inventar");
        inventoryBox = new HBox(10);
        inventoryBox.setId("inventory-box");
        inventoryBox.setAlignment(Pos.CENTER_LEFT);
        inventoryBox.setPadding(new Insets(10));
        inventoryBox.setStyle("-fx-background-color: #f0f0f0; -fx-border-color: black;");
        inventoryBox.getChildren().addAll(inventarTextField, addKartonButton);

        brettButton = new Button("Brett");
        saueleButton = new Button("Sauele");
        deleteButton = new Button("L\u00F6schen");

        VBox toolBox = new VBox(brettButton, saueleButton, kartonButton,deleteButton);
        toolBox.setAlignment(Pos.CENTER);
        toolBox.setPadding(new Insets(10));
        toolBox.setSpacing(10);

        VBox preMainLayout = new VBox(180, headerBox, centerArea, inventoryBox);
        preMainLayout.setAlignment(Pos.CENTER);

        // Hauptlayout
        HBox mainLayout = new HBox(toolBox, preMainLayout);
        getChildren().add(mainLayout);



        getStylesheets().add(getClass().getResource("/css/globals.css").toExternalForm());
        getStylesheets().add(getClass().getResource("/css/lager.css").toExternalForm());


    }



    public void setProfilname(String name) {
        this.profileNameField.setText(name);
    }

    public void bindModel(Raum raum) {
        raum.setOnChangeListener(() -> {
            System.out.println("Raum geändert");
            redraw(raum);
        });
    }

    public void redraw(Raum raum) {
        centerArea.getChildren().clear();
        int raumHoehe = raum.getHoehe();
        int countBretter = 0;
        for (RegalBrett brett : raum.getRegal().getRegalBretter()) {
            if(brett!=null) {
                int xPosition = 0;
                xPosition = raum.getRegal().getSaeulen().get(brett.getLueckenIndex()).getPositionX();
                brettRectangle = new Rectangle();
                brettRectangle.setWidth(raum.getRegal().getSaeulen().get(brett.getLueckenIndex() + 1).getPositionX() - raum.getRegal().getSaeulen().get(brett.getLueckenIndex()).getPositionX());
                brettRectangle.setHeight(brett.getDicke());
                brettRectangle.setFill(Color.BROWN);
                brettRectangle.setY(brett.getHoehe());
                brettRectangle.setX(xPosition);
                brettRectangle.setId("Brett " + countBretter);
                centerArea.getChildren().add(brettRectangle);
                countBretter++;

                int countKarton = 0;
                for (Karton karton : brett.getKartons()) {
                    if (karton != null) {
                        kartonRectangle = new Rectangle();
                        kartonRectangle.setHeight(karton.getHeight()); // Höhe der Säule
                        kartonRectangle.setWidth(karton.getWidth()); // Breite der Säule
                        kartonRectangle.setX(xPosition + karton.getXPosition()); // Position der Säule
                        kartonRectangle.setY(brett.getHoehe() - karton.getHeight()); // Start bei 0 Y-Achse
                        kartonRectangle.setFill(Color.RED); // Farbe der Säule
                        kartonRectangle.setId(" Brett " + countBretter +" Karton" + countKarton);
                        centerArea.getChildren().add(kartonRectangle);
                        countKarton++;
                    }
                }
            }
        }
        int countSaeulen = 0;
        for (Saeule saeule : raum.getRegal().getSaeulen()) {
            int positionX = saeule.getPositionX();
            // Create a rectangle for each Säule
            saeuleRectangle = new Rectangle();
            saeuleRectangle.setHeight(CENTRALHIGHT);
            saeuleRectangle.setWidth(10);
            saeuleRectangle.setX(positionX);
            saeuleRectangle.setY(5);
            saeuleRectangle.setFill(Color.GRAY);
            saeuleRectangle.setId("Saeule "+countSaeulen);
            centerArea.getChildren().add(saeuleRectangle);
            countSaeulen++;
        }
        System.out.println("Anzahl: "+ countSaeulen);
    }

    private void setButtonIcon(Button button, String iconPath) {
        Image icon = new Image(getClass().getResourceAsStream(iconPath));
        ImageView imageView = new ImageView(icon);
        imageView.setFitWidth(70);
        imageView.setFitHeight(70);
        button.setGraphic(imageView);
        button.getStyleClass().add("icon-button");
    }

    public ArrayList<Rectangle> getAllesSeuleRectangle() {
        return allesSeuleRectangle;
    }

    public Pane getCenterArea() {
        return centerArea;
    }

    public HBox getInventoryBox() {
        return inventoryBox;
    }

    public Button getAddKartonButton() {
        return addKartonButton;
    }

    public Label getProfileNameField() {
        return profileNameField;
    }

    public Button getUndoButton() {
        return undoButton;
    }

    public Button getRedoButton() {
        return redoButton;
    }

    public Button getSaveButton() {
        return saveButton;
    }

    public Button getSettingsButton() {
        return settingsButton;
    }

    public Label getInventarTextField() {
        return inventarTextField;
    }

    public Rectangle getBrettRectangle() {
        return brettRectangle;
    }
    public Rectangle getSaeuleRectangle() {
        return saeuleRectangle;
    }
    public Rectangle getKartonRectangle() {
        return kartonRectangle;
    }

    public Button getMenuButton() {
        return menuButton;
    }

    public Button getBrettButton() {
        return brettButton;
    }

    public Button getSaueleButton() {
        return saueleButton;
    }

    public Button getKartonButton() {
        return kartonButton;
    }

    public int getCENTRALHIGHT() {
        return CENTRALHIGHT;
    }
    public Button getDeleteButton() {
        return deleteButton;
    }
}
