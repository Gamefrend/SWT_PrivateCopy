package de.hsrm.mi.swt.view.lager;

import de.hsrm.mi.swt.model.save.SpeicherProfil;
import de.hsrm.mi.swt.model.storage.*;
import de.hsrm.mi.swt.model.storage.Karton;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
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
    private int CENTRALHIGHT;
    private Rectangle brettRectangle;
    private Rectangle saeuleRectangle;
    private ArrayList<Rectangle> allesSeuleRectangle;
    private Rectangle kartonRectangle;
    private Label kartonAnzahl;
    private Button addKartonButton;
    private SpeicherProfil speicherProfil;

    public LagerView() {
        setId("lager-view");
        setPadding(new Insets(20, 20, 0, 20));

        // Textfeld für den Profilnamen
        profileNameField = new Label("Profilname eingeben");
        profileNameField.setMinWidth(200);
        profileNameField.getStyleClass().add("h2");

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
        centerArea.setStyle("-fx-background-color: white; -fx-min-height: 600;");
        CENTRALHIGHT = Integer.parseInt(centerArea.getStyle().split(": ")[centerArea.getStyle().split(": ").length - 1].replace(";", ""));

        addKartonButton = createIconButton("/icons/inventory-add.png", 50, 50);
        addKartonButton.getStyleClass().add("inventory-add-button");

        // tools
        saueleButton = createIconButton("/icons/sauele.png", 42, 42);
        saueleButton.getStyleClass().add("tool-button");

        brettButton = createIconButton("/icons/bretter.png", 42, 42);
        brettButton.getStyleClass().add("tool-button");

        deleteButton = createIconButton("/icons/trash-tool.png", 42, 42);
        deleteButton.getStyleClass().add("tool-button");


        // Create toolbox
        HBox toolBox = new HBox(10, saueleButton, brettButton, deleteButton);
        toolBox.setAlignment(Pos.CENTER_LEFT);
        toolBox.setPadding(new Insets(10));

        // Inventar-Bereich
        inventoryBox = new HBox(10);
        inventoryBox.setId("inventory-box");
        inventoryBox.setAlignment(Pos.CENTER_LEFT);
        inventoryBox.setPadding(new Insets(10));
        inventoryBox.setStyle("-fx-background-color: #f0f0f0;");
        inventoryBox.getChildren().addAll(addKartonButton);

        VBox bottomSection = new VBox(10, toolBox, inventoryBox);
        bottomSection.setAlignment(Pos.CENTER_LEFT);

        VBox preMainLayout = new VBox(50, headerBox, centerArea, bottomSection);
        preMainLayout.setAlignment(Pos.CENTER);

        // Hauptlayout
        VBox mainLayout = new VBox(40);
        mainLayout.getChildren().addAll(headerBox, centerArea, toolBox, inventoryBox);
        mainLayout.setAlignment(Pos.TOP_LEFT);

        StackPane wrapper = new StackPane(mainLayout);
        wrapper.setAlignment(Pos.TOP_LEFT);

        getChildren().add(wrapper);

        getStylesheets().add(getClass().getResource("/css/globals.css").toExternalForm());
        getStylesheets().add(getClass().getResource("/css/lager.css").toExternalForm());

    }

    public LagerView(SpeicherProfil speicherProfil) {
        this.speicherProfil = speicherProfil;
        profileNameField.setText(speicherProfil.getSaveName());
    }

    public void setProfilname(String name) {
        if (name != null) {
            this.profileNameField.setText(name);
        } else {
            this.profileNameField.setText("Kein Profil angelegt");
        }
    }

    public void updateSpeicherProfil(SpeicherProfil speicherProfil) {
        this.speicherProfil = speicherProfil;
        profileNameField.setText(speicherProfil.getSaveName());
    }

    public void bindModel(Raum raum) {
        raum.setOnChangeListener(() -> {
            System.out.println("Raum geändert");
            redraw(raum);
        });
    }

    public void redraw(Raum raum) {
        centerArea.getChildren().clear();
        ArrayList<Node> toBeRemover = new ArrayList<>();
        for(Node node :  inventoryBox.getChildren()){
            if(node != null) {
                if(node.getId() != null) {
                    if (node.getId().contains("Inventar")) {
                        toBeRemover.add(node);
                    }
                }
            }
        }
        for (Node node : toBeRemover){
            inventoryBox.getChildren().remove(node);
        }
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
                        kartonRectangle.setHeight(karton.getHoehe());
                        kartonRectangle.setWidth(karton.getBreite());
                        kartonRectangle.setX(xPosition + karton.getXPosition());
                        kartonRectangle.setY(brett.getHoehe()+(brett.getDicke()/2) - karton.getHoehe());
                        kartonRectangle.setFill(Color.RED);
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
        int countKarton = 0;
        int anzahlCounter = 0;
        for(Karton karton : raum.getRegal().getUebrigesInventar().getKartons()){
            kartonRectangle = new Rectangle();
            kartonAnzahl = new Label();
            kartonRectangle.setHeight(50);
            kartonRectangle.setWidth(50);
            kartonRectangle.setX(countKarton * 50 + 20); // im Inventar immer 'x'px weiter + 'y'px abstand zum nächsten
            kartonRectangle.setY(20);
            if(karton.getWaren().getTyp().isLebensmittelBool()){
                kartonRectangle.setFill(Color.YELLOW);
            } else if (karton.getWaren().getTyp().isGekuehltBool()) {
                kartonRectangle.setFill(Color.LIGHTBLUE);
            } else if (karton.getWaren().getTyp().isGiftigBool()) {
                kartonRectangle.setFill(Color.LIGHTGREEN);
            } else {
                kartonRectangle.setFill(Color.DARKGOLDENROD);
            }
            kartonRectangle.setId(" Inventar:" + " Karton" + countKarton);
            inventoryBox.getChildren().add(kartonRectangle);
            countKarton++;
        }
    }

    private void setButtonIcon(Button button, String iconPath) {
        Image icon = new Image(getClass().getResourceAsStream(iconPath));
        ImageView imageView = new ImageView(icon);
        imageView.setFitWidth(70);
        imageView.setFitHeight(70);
        button.setGraphic(imageView);
        button.getStyleClass().add("icon-button");
    }

    private Button createIconButton(String iconPath, int width, int height) {
        Button button = new Button();
        Image icon = new Image(getClass().getResourceAsStream(iconPath));
        ImageView imageView = new ImageView(icon);
        imageView.setFitWidth(width);
        imageView.setFitHeight(height);
        button.setGraphic(imageView);
        button.getStyleClass().add("icon-button");
        button.setPrefSize(width, height);
        button.setMinSize(width, height);
        button.setMaxSize(width, height);
        return button;
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
