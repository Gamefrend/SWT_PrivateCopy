package de.hsrm.mi.swt.controller;

import de.hsrm.mi.swt.model.save.AddBrettCommand;
import de.hsrm.mi.swt.model.save.AddSaeuleCommand;
import de.hsrm.mi.swt.model.save.Command;
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
import java.util.Stack;

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
    private Button kartonButton;
    private Button deleteButton;
    private Button addKartonButton;
    private boolean saeuleButtonActive = false;
    private KartonErstellenController kartonErstellenController;
    private boolean brettButtonActive = false;

    private Runnable onChange;
    double xPosition = 0;
    private Stack<Command> undoStack = new Stack<>();
    private Stack<Command> redoStack = new Stack<>();

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
        kartonButton = lagerView.getKartonButton();
        deleteButton = lagerView.getDeleteButton();
        kartonErstellenController = new KartonErstellenController();
        addKartonButton = lagerView.getAddKartonButton();
        initialize(application.getAktuellerRaum());
        application.setRaumChangeListener(this::initialize);
    }

    public void initialize(Raum raum) {
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

        brettButton.setOnMouseClicked(e ->  handleBrett());
        saueleButton.setOnMouseClicked(e -> handleSauele());
        kartonButton.addEventHandler(ActionEvent.ACTION, e -> handleKarton());

        deleteButton.setOnMouseClicked(e -> handleDelete());

        addKartonButton.setOnAction(e -> kartonErstellenController.showPopup(lagerView.getScene().getWindow()));

        lagerView.getCenterArea().setOnMouseClicked(event -> {
            if (saeuleButtonActive) {
                double x = event.getX();
                addSaeule(x);
                dragListenerSauleAnmelden();
            }
            if (deleteButtonActive) {
                boolean elementDeleted = false;
                double clickX = event.getX();
                double clickY = event.getY();
                for (Saeule saeule : aktuellerRaum.getRegal().getSaeulen()) {
                    if (isClickInsideSaeule(saeule, clickX, clickY)) {
                        aktuellerRaum.getRegal().getSaeulen().remove(saeule);
                        elementDeleted = true;
                        break;
                    }
                }


                if (!elementDeleted) {
                    for (RegalBrett brett : aktuellerRaum.getRegal().getRegalBretter()) {
                        if (isClickInsideBrett(brett, clickX, clickY)) {
                            aktuellerRaum.getRegal().getRegalBretter().remove(brett);
                            elementDeleted = true;
                            break;
                        }
                    }
                }


                if (!elementDeleted) {
                    for (RegalBrett brett : aktuellerRaum.getRegal().getRegalBretter()) {
                        for (Karton karton : brett.getKartons()) {
                            if (isClickInsideKarton(karton, clickX, clickY)) {
                                brett.getKartons().remove(karton);
                                elementDeleted = true;
                                break;
                            }
                        }
                        if (elementDeleted) {
                            break;
                        }
                    }
                }
                if (!elementDeleted) {
                    System.out.println("No element found to delete.");
                }

            }
            if (brettButtonActive){
                int lueckenIndex = findLueckenIndex(event.getX());
                addBrett(lueckenIndex , event.getY());
            }
            lagerView.redraw(application.getAktuellerRaum());


        });
    }


    public void handleUndo() {
        if (!undoStack.isEmpty()) {
            Command command = undoStack.pop();
            System.out.println("Undoing command");
            command.undo();
            redoStack.push(command);
            lagerView.redraw(aktuellerRaum); // Aktuellen Raum neu zeichnen
        } else {
            System.out.println("Undo stack is empty");
        }
    }


    public void handleRedo() {
        if (!redoStack.isEmpty()) {
            Command command = redoStack.pop();
            System.out.println("Redoing command");
            command.redo();
            undoStack.push(command);
            lagerView.redraw(aktuellerRaum); // Aktuellen Raum neu zeichnen
        } else {
            System.out.println("Redo stack is empty");
        }
    }

    private void handleSave() {
        aktuellesSpeicherprofil.save(aktuellerRaum);
        System.out.println("Save button clicked");
    }

    private void handleSettings() {
        System.out.println("Settings button clicked");
    }

    public void handleBrett() {
        brettButtonActive = !brettButtonActive;
        updateToolButtonStyles();

        if (deleteButtonActive ){
            deleteButtonActive = false ;
            deleteButton.getStyleClass().remove("active-button");
        }
        if( saeuleButtonActive){
            saeuleButtonActive = false;
            saueleButton.getStyleClass().remove("active-button");
        }

    }

    public void addBrett(int lueckenIndex, double yPosition) {
        if (lueckenIndex < 0 || lueckenIndex >= aktuellerRaum.getRegal().getSaeulen().size() - 1) {
            System.out.println("Keine geeignete Lücke für ein Brett vorhanden.");
            return;
        }

        RegalBrett neuesBrett = new RegalBrett((int) yPosition, 10, 1, lueckenIndex);

        aktuellerRaum.getRegal().addBrett(neuesBrett);

        // Redo Undo einbinden
        Command command = new AddBrettCommand(aktuellerRaum.getRegal(), neuesBrett);
        System.out.println("Executing command: Adding Brett at Y-Position " + yPosition + " and Index " + lueckenIndex);
        command.redo();
        undoStack.push(command);
        redoStack.clear();
        lagerView.redraw(aktuellerRaum); // Aktuellen Raum neu zeichnen
    }

    private int findLueckenIndex(double x) {
        Saeule leftSaeule = null;
        Saeule rightSaeule = null;

        // Durchlaufe die Liste der Säulen, um die linke und rechte Säule relativ zu x zu finden
        for (Saeule saeule : aktuellerRaum.getRegal().getSaeulen()) {
            if (saeule.getPositionX() < x) {
                if (leftSaeule == null || saeule.getPositionX() > leftSaeule.getPositionX()) {
                    leftSaeule = saeule;
                }
            } else if (saeule.getPositionX() > x) {
                if (rightSaeule == null || saeule.getPositionX() < rightSaeule.getPositionX()) {
                    rightSaeule = saeule;
                }
            }
        }

        if ( leftSaeule != null && rightSaeule != null){

            return aktuellerRaum.getRegal().getSaeulen().indexOf(leftSaeule);
        }
        else {
            System.out.println("Keine Säule links oder rechts");
            return -1;
        }

    }


    public void handleSauele() {
        saeuleButtonActive = !saeuleButtonActive;
        updateToolButtonStyles();

        if(deleteButtonActive){
            deleteButtonActive = false;
            deleteButton.getStyleClass().remove("active-button");
        }
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
                    if (!deleteButtonActive) {
                        node.setOpacity(0.5); // Make node slightly transparent
                        lagerView.getCenterArea().setCursor(Cursor.E_RESIZE); // Change cursor to move
                    }
                });

                node.setOnMouseReleased(e -> {
                    if (!deleteButtonActive) {
                        if (e.getX() >= lagerView.getCenterArea().getLayoutX() && e.getX() <= lagerView.getLayoutX() + lagerView.getCenterArea().getWidth())
                            node.setOpacity(1.0); // Reset opacity
                        lagerView.getCenterArea().setCursor(Cursor.DEFAULT); // Reset cursor
                        aktuellerRaum.getRegal().verschiebeSaeule(aktuellerRaum.getRegal().getSaeulen().get(node.getId().charAt(node.getId().length() - 1) - '0'), (int) e.getX());
                        dragListenerSauleAnmelden();
                    }
                });
            }
            if (node.getId() != null && node.getId().startsWith("Brett")) {
                node.setCursor(Cursor.HAND);
                node.setOnMousePressed(e -> {
                    if (!deleteButtonActive) {
                        node.setOpacity(0.5); // Make node slightly transparent
                        lagerView.getCenterArea().setCursor(Cursor.H_RESIZE); // Change cursor to move
                    }
                });

                node.setOnMouseReleased(e -> {
                    if (!deleteButtonActive) {
                        if (e.getY() >= lagerView.getCenterArea().getLayoutY() && e.getY() <= lagerView.getLayoutY() + lagerView.getCenterArea().getHeight())
                            node.setOpacity(1.0); // Reset opacity
                        lagerView.getCenterArea().setCursor(Cursor.DEFAULT); // Reset cursor
                        aktuellerRaum.getRegal().getRegalBretter().get(node.getId().charAt(node.getId().length() - 1) - '0').setHoehe((int) e.getY());
                        dragListenerSauleAnmelden();
                    }
                });
            }
        }
    }

    public void addSaeule(double x) {
        int positionX = (int) x;
        Saeule newSaeule = new Saeule(positionX);
        Command command = new AddSaeuleCommand(aktuellerRaum, newSaeule);
        System.out.println("Executing command: Adding Saeule at position " + positionX);
        command.redo();
        undoStack.push(command);
        redoStack.clear(); // Redo-Stack leeren, da eine neue Aktion ausgeführt wurde
        lagerView.redraw(aktuellerRaum); // Aktuellen Raum neu zeichnen
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

    public void handleDelete() {
        deleteButtonActive = !deleteButtonActive;
        updateToolButtonStyles();

        if (deleteButtonActive) {
            if(saeuleButtonActive){
                saeuleButtonActive = false;
                saueleButton.getStyleClass().remove("active-button");
            }
            deleteButton.getStyleClass().add("active-button");
        } else {
            deleteButton.getStyleClass().remove("active-button");
        }



        /*boolean elementDeleted = false;

        if (!aktuellerRaum.getRegal().getSaeulen().isEmpty()) {
            Saeule letzteSaeule = aktuellerRaum.getRegal().getSaeulen().getLast();
            aktuellerRaum.getRegal().getSaeulen().remove(letzteSaeule);
            lagerView.getCenterArea().getChildren().remove(lagerView.getSaeuleRectangle());
            System.out.println("Letzte Säule gelöscht");
            elementDeleted = true;
        }

        if (!elementDeleted && !aktuellerRaum.getRegal().getRegalBretter().isEmpty()) {
            RegalBrett letztesBrett = aktuellerRaum.getRegal().getRegalBretter().getLast();
            aktuellerRaum.getRegal().getRegalBretter().remove(letztesBrett);
            lagerView.getCenterArea().getChildren().remove(lagerView.getBrettRectangle());
            System.out.println("Letztes Brett gelöscht");
            elementDeleted = true;
        }

        if (!elementDeleted) {
            System.out.println("Nichts zu löschen");
        }


         */
    }

    private void updateToolButtonStyles() {
        lagerView.getSaueleButton().getStyleClass().remove("tool-button-selected");
        lagerView.getBrettButton().getStyleClass().remove("tool-button-selected");
        lagerView.getDeleteButton().getStyleClass().remove("tool-button-selected");

        if (saeuleButtonActive) {
            lagerView.getSaueleButton().getStyleClass().add("tool-button-selected");
        }
        if (brettButtonActive) {
            lagerView.getBrettButton().getStyleClass().add("tool-button-selected");
        }
        if (deleteButtonActive) {
            lagerView.getDeleteButton().getStyleClass().add("tool-button-selected");
        }
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
