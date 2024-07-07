package de.hsrm.mi.swt.controller;

import de.hsrm.mi.swt.model.storage.Regal;
import de.hsrm.mi.swt.model.storage.RegalBrett;
import de.hsrm.mi.swt.model.storage.Saeule;
import de.hsrm.mi.swt.view.uikomponente.Karton;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import de.hsrm.mi.swt.app.StorageShelvesApplication;
import de.hsrm.mi.swt.model.save.SpeicherProfil;
import de.hsrm.mi.swt.model.storage.Raum;
import de.hsrm.mi.swt.view.PrimaryViewName;
import de.hsrm.mi.swt.view.lager.LagerView;
import javafx.event.ActionEvent;
import javafx.stage.Window;

import javax.swing.event.ChangeEvent;
import java.awt.event.MouseEvent;
import java.util.Map;

public class LagerController {
    private Map<PrimaryViewName, Pane> primaryViews;
    private Stage primaryStage;
    private LagerView lagerView;
    private Raum aktuellerRaum;
    private SpeicherProfil aktuellesSpeicherprofil;
    private StorageShelvesApplication application;
    private Button menuButton;
    private Button undoButton;
    private Button redoButton;
    private Button saveButton;
    private Button settingsButton;
    private Button brettButton;
    private Button saueleButton;
    private Button skalierenButton;
    private Button moveButton;
    private Button kartonButton;
    private Button deleteButton;
    private boolean saeuleButtonActive = false;
    private KartonErstellenController kartonErstellenController;

    private Runnable onChange;
    double xPosition = 0;

    private boolean deleteButtonActive = false;

    public LagerController(StorageShelvesApplication application, LagerView lagerView) {
        this.application = application;
        this.lagerView = lagerView;
        menuButton = lagerView.getMenuButton();
        undoButton = lagerView.getUndoButton();
        redoButton = lagerView.getRedoButton();
        saveButton = lagerView.getSaveButton();
        settingsButton = lagerView.getSettingsButton();
        brettButton = lagerView.getBrettButton();
        saueleButton = lagerView.getSaueleButton();
        skalierenButton = lagerView.getSkalierenButton();
        moveButton = lagerView.getMoveButton();
        kartonButton = lagerView.getKartonButton();
        deleteButton = lagerView.getDeleteButton();
        kartonErstellenController = new KartonErstellenController();
        initialize(application.getAktuellerRaum());
        application.setRaumChangeListener(this::initialize);
    }

    private void initialize(Raum raum) {
        this.aktuellerRaum = raum;
        setupRoom();
        setupViewBindings();
        setupButtonHandlers();
        lagerView.redraw(aktuellerRaum);

    }

    private void setupRoom() {
        if (application.getAktuellesSpeicherprofil() != null) {
            this.aktuellesSpeicherprofil = application.getAktuellesSpeicherprofil();
        } else {
            System.out.println("Hier kommt Logik hin die ein neuen Raum erstellt");
            application.setAktuellerRaum(new Raum(2000, 3000));
            aktuellerRaum = application.getAktuellerRaum();
            application.setAktuellesSpeicherprofil(new SpeicherProfil("TestProfil1"));
            aktuellesSpeicherprofil = application.getAktuellesSpeicherprofil();
            aktuellerRaum.setRegal(new Regal(new SimpleIntegerProperty(2000)));
        }
        lagerView.getProfileNameField().setText(aktuellesSpeicherprofil.getSaveName());
    }

    private void setupViewBindings() {
        onChange = () -> {
            aktuellerRaum = application.getAktuellerRaum();
            lagerView.redraw(aktuellerRaum);
        };
        aktuellerRaum.setOnChangeListener(onChange);
        lagerView.bindModel(aktuellerRaum);
    }

    private void setupButtonHandlers() {
        undoButton.setOnAction(e -> handleUndo());
        redoButton.setOnAction(e -> handleRedo());
        saveButton.setOnAction(e -> handleSave());
        settingsButton.setOnAction(e -> handleSettings());
        menuButton.setOnAction(e -> {
            System.out.println("Restarted?!?");
            application.restart();
        });
        brettButton.addEventHandler(ActionEvent.ACTION, e -> handleBrett());
        saueleButton.setOnMouseClicked(e -> handleSauele());
        kartonButton.addEventHandler(ActionEvent.ACTION, e -> handleKarton());


        // ----------
        //deleteButton.addEventHandler(ActionEvent.ACTION, e -> handleDelete());

        deleteButton.setOnAction(e -> handleDelete());

        // ------------

        lagerView.getAddKartonButton().setOnAction(e -> kartonErstellenController.showPopup(lagerView.getScene().getWindow()));

        lagerView.getCenterArea().setOnMouseClicked(event -> {
            if (saeuleButtonActive) {
                double x = event.getX();
                addSaeule(x);
                dragListenerSauleAnmelden();
            }
        });
    }


    private void handleUndo() {
        // Undo-Logik muss noch implementiert werden
        System.out.println("Undo button clicked");
    }

    private void handleRedo() {
        // Redo-Logik muss noch implementiert werden
        System.out.println("Redo button clicked");
    }

    private void handleSave() {
        aktuellesSpeicherprofil.save(aktuellerRaum);
        System.out.println("Save button clicked");
    }

    private void handleSettings() {
        System.out.println("Settings button clicked");
    }

    public void handleBrett() {
        RegalBrett neuesBrett = new RegalBrett(100, 10, 1, 0);
        aktuellerRaum.getRegal().getRegalBretter().add(neuesBrett);
        dragListenerSauleAnmelden();
    }

    public void handleSauele() {
        saeuleButtonActive = !saeuleButtonActive;
        if (saeuleButtonActive) {
            saueleButton.getStyleClass().add("active-button");
        } else {
            saueleButton.getStyleClass().remove("active-button");
        }

    }

    public void dragListenerSauleAnmelden() {

        for (Node node : lagerView.getCenterArea().getChildren()) {
            if (node.getId() != null && node.getId().startsWith("Saeule")) {
                node.setCursor(Cursor.HAND);
                node.setOnMousePressed(e -> {
                    node.setOpacity(0.5); // Make node slightly transparent
                    lagerView.getCenterArea().setCursor(Cursor.E_RESIZE); // Change cursor to move
                });

                node.setOnMouseReleased(e -> {
                    if (e.getX() >= lagerView.getCenterArea().getLayoutX() && e.getX() <= lagerView.getLayoutX() + lagerView.getCenterArea().getWidth())
                        node.setOpacity(1.0); // Reset opacity
                    lagerView.getCenterArea().setCursor(Cursor.DEFAULT); // Reset cursor
                    aktuellerRaum.getRegal().verschiebeSaeule(aktuellerRaum.getRegal().getSaeulen().get(node.getId().charAt(node.getId().length() - 1) - '0'), (int) e.getX());
                    dragListenerSauleAnmelden();
                });
            }
            if (node.getId() != null && node.getId().startsWith("Brett")) {
                node.setCursor(Cursor.HAND);
                node.setOnMousePressed(e -> {
                    node.setOpacity(0.5); // Make node slightly transparent
                    lagerView.getCenterArea().setCursor(Cursor.H_RESIZE); // Change cursor to move
                });

                node.setOnMouseReleased(e -> {
                    if (e.getY() >= lagerView.getCenterArea().getLayoutY() && e.getY() <= lagerView.getLayoutY() + lagerView.getCenterArea().getHeight())
                        node.setOpacity(1.0); // Reset opacity
                    lagerView.getCenterArea().setCursor(Cursor.DEFAULT); // Reset cursor
                    aktuellerRaum.getRegal().getRegalBretter().get(node.getId().charAt(node.getId().length() - 1) - '0').setHoehe((int) e.getY());
                    dragListenerSauleAnmelden();
                });
            }
        }
    }

    public void addSaeule(double x) {
        int positionX = (int) x;
        Saeule newSaeule = new Saeule(positionX);
        aktuellerRaum.getRegal().addSaeule(newSaeule);
    }

    public boolean isSaeuleButtonActive() {
        return saeuleButtonActive;
    }

    public void handleKarton() {
        RegalBrett regalBrett = aktuellerRaum.getRegal().getRegalBretter().get(0);
        Karton karton = new Karton(50, 50, Color.FIREBRICK, 100, 0, 0, null);
        regalBrett.getKartons().add(karton);
        lagerView.getCenterArea().getChildren().add(karton.getRectangle()); // Rechteck zu der Ansicht hinzufügen
    }

    private void handleDelete(MouseEvent event) {

        deleteButtonActive = !deleteButtonActive;

        if ( deleteButtonActive ){
            double clickX = event.getX();
            double clickY = event.getY();

            boolean elementDeleted = false;

            if ( !elementDeleted){
                if ( )


            }





        }



        /*boolean elementDeleted = false;

        if (!aktuellerRaum.getRegal().getSaeulen().isEmpty()) {
            Saeule letzteSaeule = aktuellerRaum.getRegal().getSaeulen().getLast();
            aktuellerRaum.getRegal().getSaeulen().remove(letzteSaeule);
            lagerView.getCenterArea().getChildren().remove(lagerView.getSaeuleRectangle()); // Assuming there's a method to get the graphical representation
            System.out.println("Letzte Säule gelöscht");
            elementDeleted = true;
        }

        if (!elementDeleted && !aktuellerRaum.getRegal().getRegalBretter().isEmpty()) {
            RegalBrett letztesBrett = aktuellerRaum.getRegal().getRegalBretter().getLast();
            aktuellerRaum.getRegal().getRegalBretter().remove(letztesBrett);
            lagerView.getCenterArea().getChildren().remove(lagerView.getBrettRectangle()); // Assuming there's a method to get the graphical representation
            System.out.println("Letztes Brett gelöscht");
            elementDeleted = true;
        }

        if (!elementDeleted) {
            System.out.println("Nichts zu löschen");
        }


         */
    }

    private boolean isClickInsideSaeule(Saeule saeule, double clickX, double clickY) {
        double saeuleX = saeule.getPositionX();
        double tolerance = 10;
        return (Math.abs(saeuleX - clickX) <= tolerance);
    }

    private boolean isClickInsideBrett(RegalBrett brett, double clickX, double clickY) {
        double brettX = brett.getLueckenIndex();
        double brettY = brett.getHoehe();
        double tolerance = 10;
        return (Math.abs(brettX - clickX) <= tolerance && Math.abs(brettY - clickY) <= tolerance);
    }

    private boolean isClickInsideKarton(Karton karton, double clickX, double clickY) {
        double kartonX = karton.getXPosition();
        double kartonY = karton.getYPosition();
        double kartonWidth = karton.getWidth();
        double kartonHeight = karton.getHeight();


        return (clickX >= kartonX && clickX <= kartonX + kartonWidth &&
                clickY >= kartonY && clickY <= kartonY + kartonHeight);
    }


    public LagerView getRoot() {
        return lagerView;
    }




}
