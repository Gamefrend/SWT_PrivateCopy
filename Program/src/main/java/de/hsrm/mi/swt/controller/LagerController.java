package de.hsrm.mi.swt.controller;

import de.hsrm.mi.swt.model.save.AddBrettCommand;
import de.hsrm.mi.swt.model.save.AddSaeuleCommand;
import de.hsrm.mi.swt.model.save.Command;
import de.hsrm.mi.swt.model.storage.Regal;
import de.hsrm.mi.swt.model.storage.RegalBrett;
import de.hsrm.mi.swt.model.storage.Saeule;
import de.hsrm.mi.swt.model.storage.Karton;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class LagerController {
    private Map<PrimaryViewName, Pane> primaryViews;
    private Stage primaryStage;
    private LagerView lagerView;
    private Raum aktuellerRaum;
    private RaumErstellenController raumErstellenController;
    private SpeicherProfil aktuellesSpeicherprofil;
    private StorageShelvesApplication application;
    private Button menuButton;
    private Button undoButton;
    private Button redoButton;
    private Button saveButton;
    private Button settingsButton;
    private Button brettButton;
    private Button saueleButton;
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
        this.aktuellesSpeicherprofil = application.getAktuellesSpeicherprofil();
//        f();

        if (this.aktuellesSpeicherprofil == null) {
            this.aktuellesSpeicherprofil = new SpeicherProfil("Standardprofil");
            application.setAktuellesSpeicherprofil(this.aktuellesSpeicherprofil);
        }

        lagerView.setProfilname(this.aktuellesSpeicherprofil.getSaveName());

        menuButton = lagerView.getMenuButton();
        undoButton = lagerView.getUndoButton();
        redoButton = lagerView.getRedoButton();
        saveButton = lagerView.getSaveButton();
        settingsButton = lagerView.getSettingsButton();
        brettButton = lagerView.getBrettButton();
        saueleButton = lagerView.getSaueleButton();
        deleteButton = lagerView.getDeleteButton();
        kartonErstellenController = new KartonErstellenController(application);
        addKartonButton = lagerView.getAddKartonButton();

        setupButtonHandlers();
        initialize(application.getAktuellerRaum());
        application.setRaumChangeListener(this::initialize);
    }

    public void initialize(Raum raum) {
        if (raum != null) {
            this.aktuellerRaum = raum;
            setupRoom();
            setupViewBindings();
            lagerView.redraw(aktuellerRaum);
            dragListenerSauleAnmelden();
        } else {
            System.out.println("Warning: Trying to initialize LagerController with null Raum");
        }
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
            aktuellerRaum.setRegal(new Regal(2000));
        }
        lagerView.getProfileNameField().setText(aktuellesSpeicherprofil.getSaveName());
    }

    private void setupViewBindings() {
        onChange = () -> {
            aktuellerRaum = application.getAktuellerRaum();
            lagerView.redraw(aktuellerRaum);
            dragListenerSauleAnmelden();
        };
        aktuellerRaum.setOnChangeListener(onChange);
        lagerView.bindModel(aktuellerRaum);
    }

    private void setupButtonHandlers() {
        undoButton.setOnAction(e -> handleUndo());
        redoButton.setOnAction(e -> handleRedo());
        saveButton.setOnAction(e -> handleSave());
//        settingsButton.setOnAction(e -> handleSettings());
        lagerView.getSettingsButton().setOnAction(e -> handleSettings());
        menuButton.setOnAction(e -> {
            System.out.println("Restarted?!?");
            application.restart();
        });

        brettButton.setOnMouseClicked(e ->  handleBrett());
        saueleButton.setOnMouseClicked(e -> handleSauele());
        deleteButton.setOnMouseClicked(e -> handleDelete());

        addKartonButton.setOnAction(e -> kartonErstellenController.showPopup(lagerView.getScene().getWindow()));

        lagerView.getCenterArea().setOnMouseClicked(event -> {
            if (saeuleButtonActive) {
                double x = event.getX();
                addSaeule(x);

                int lueckenIndex = findLueckenIndex(x);

                System.out.println(lueckenIndex+"-----------------------------------------------");
                for( RegalBrett brett : aktuellerRaum.getRegal().getRegalBretter()){

                    if( lueckenIndex<= 0){
                        brett.setLueckenIndex(brett.getLueckenIndex() + 1);
                    }
                    else if ( lueckenIndex < brett.getLueckenIndex()){
                        brett.setLueckenIndex(brett.getLueckenIndex() + 1);
                    }
                }

                dragListenerSauleAnmelden();
            }
            if (deleteButtonActive) {
                boolean elementDeleted = false;
                double clickX = event.getX();
                double clickY = event.getY();
                for (Saeule saeule : aktuellerRaum.getRegal().getSaeulen()) {
                    if (isClickInsideSaeule(saeule, clickX, clickY)) {




                        int saeulenIndex = aktuellerRaum.getRegal().getSaeulen().indexOf(saeule);
                        // removen der Bretter

                        if (saeulenIndex >= 0 && saeulenIndex < aktuellerRaum.getRegal().getSaeulen().size()) {
                            // Sammle die zu entfernenden Bretter in einer separaten Liste
                            List<RegalBrett> bretterToRemove = new ArrayList<>();

                            // Bretter links
                            if (saeulenIndex > 0) {
                                int leftLueckenIndex = saeulenIndex - 1;
                                for (RegalBrett brett : aktuellerRaum.getRegal().getRegalBretter()) {
                                    if (brett.getLueckenIndex() == leftLueckenIndex) {
                                        bretterToRemove.add(brett);
                                    }
                                }
                            }
                            // Bretter rechts
                            if (saeulenIndex < aktuellerRaum.getRegal().getSaeulen().size() - 1) {
                                for (RegalBrett brett : aktuellerRaum.getRegal().getRegalBretter()) {
                                    if (brett.getLueckenIndex() == saeulenIndex) {
                                        bretterToRemove.add(brett);
                                    }
                                }
                            }

                            // Entferne die gesammelten Bretter
                            aktuellerRaum.getRegal().getRegalBretter().removeAll(bretterToRemove);
                        }


                        // bretter sind removed


                        for (RegalBrett brett : aktuellerRaum.getRegal().getRegalBretter()) {
                            if (brett.getLueckenIndex() > saeulenIndex) {
                                brett.setLueckenIndex(brett.getLueckenIndex() - 1);
                            }
                        }

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
            dragListenerSauleAnmelden();


        });
    }


    public void handleUndo() {
        if (!undoStack.isEmpty()) {
            Command command = undoStack.pop();
            System.out.println("Undoing command");
            command.undo();
            redoStack.push(command);
            lagerView.redraw(aktuellerRaum); // Aktuellen Raum neu zeichnen
            dragListenerSauleAnmelden();
        } else {
            System.out.println("Undo stack is empty");
        }
    }

    public void updateProfileName() {
        if (aktuellesSpeicherprofil != null) {
            lagerView.setProfilname(aktuellesSpeicherprofil.getSaveName());
        }
    }

    public void handleRedo() {
        if (!redoStack.isEmpty()) {
            Command command = redoStack.pop();
            System.out.println("Redoing command");
            command.redo();
            undoStack.push(command);
            lagerView.redraw(aktuellerRaum); // Aktuellen Raum neu zeichnen
            dragListenerSauleAnmelden();
        } else {
            System.out.println("Redo stack is empty");
        }
    }

    private void handleSave() {
        aktuellesSpeicherprofil.save(aktuellerRaum);
        System.out.println("Save button clicked");
    }

    private void handleSettings() {
        application.showRaumErstellenPopup(true);
//        lagerView.updateSpeicherProfil(aktuellesSpeicherprofil);
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

    public void setAktuellesSpeicherprofil(SpeicherProfil speicherProfil) {
        this.aktuellesSpeicherprofil = speicherProfil;
        updateProfileName();
    }


    public void addBrett(int lueckenIndex, double yPosition) {
        if (lueckenIndex < 0 || lueckenIndex >= aktuellerRaum.getRegal().getSaeulen().size() - 1) {
            System.out.println("Keine geeignete L\u00FCcke f\u00FCr ein Brett vorhanden.");
            return;
        }

        RegalBrett neuesBrett = new RegalBrett((int) yPosition, 10, 1, lueckenIndex);
        Command command = new AddBrettCommand(aktuellerRaum.getRegal(), neuesBrett);
        System.out.println("Executing command: Adding Brett at Y-Position " + yPosition + " and Index " + lueckenIndex);
        command.redo();
        undoStack.push(command);
        redoStack.clear();


        lagerView.redraw(aktuellerRaum); // Aktuellen Raum neu zeichnen
        dragListenerSauleAnmelden();
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
        else if ( leftSaeule !=null && rightSaeule == null){
            return aktuellerRaum.getRegal().getSaeulen().size()-1;
        }
        else {
            System.out.println("Keine S\u00E4ule links oder rechts");
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
        if ( brettButtonActive){
            brettButtonActive = false;
            brettButton.getStyleClass().remove("active-button");

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





        lagerView.redraw(aktuellerRaum);// Aktuellen Raum neu zeichnen
        dragListenerSauleAnmelden();
    }


    public boolean isSaeuleButtonActive() {
        return saeuleButtonActive;
    }


    public void handleDelete() {
        deleteButtonActive = !deleteButtonActive;
        updateToolButtonStyles();

        if (deleteButtonActive) {
            if(saeuleButtonActive){
                saeuleButtonActive = false;
                saueleButton.getStyleClass().remove("active-button");
            }
            if(brettButtonActive){
                brettButtonActive = false;
                brettButton.getStyleClass().remove("active-button");
            }
            deleteButton.getStyleClass().add("active-button");
        } else {
            deleteButton.getStyleClass().remove("active-button");
        }

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
        return (brettX  == findLueckenIndex(clickX) && Math.abs(brettY - clickY) <= tolerance);
    }

    private boolean isClickInsideKarton(Karton karton, double clickX, double clickY) {
        double kartonX = karton.getXPosition();
       // double kartonY = karton.getYPosition();
        //double kartonWidth = karton.getWidth();
        //double kartonHeight = karton.getHeight();

        return false;
       // return (clickX >= kartonX && clickX <= kartonX + kartonWidth &&
         //       clickY >= kartonY && clickY <= kartonY + kartonHeight);
    }

    public LagerView getRoot() {
        return lagerView;
    }


}
