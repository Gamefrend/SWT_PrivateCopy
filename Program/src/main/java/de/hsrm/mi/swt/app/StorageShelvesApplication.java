package de.hsrm.mi.swt.app;

import de.hsrm.mi.swt.controller.RaumErstellenController;
import de.hsrm.mi.swt.view.lager.RaumErstellenView;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

import de.hsrm.mi.swt.controller.HauptmenueController;
import de.hsrm.mi.swt.controller.LagerController;
import de.hsrm.mi.swt.controller.ProfilManagerController;
import de.hsrm.mi.swt.model.save.Profilauswahl;
import de.hsrm.mi.swt.model.save.SpeicherProfil;
import de.hsrm.mi.swt.model.storage.Raum;
import de.hsrm.mi.swt.view.PrimaryViewName;
import de.hsrm.mi.swt.view.profilmanager.ProfilManagerView;
import de.hsrm.mi.swt.view.startmenue.HauptmenueView;
import de.hsrm.mi.swt.view.lager.LagerView;

public class StorageShelvesApplication extends Application {
    private Stage primaryStage;
    private Map<PrimaryViewName, Pane> primaryViews;

    private Raum aktuellerRaum;
    private RaumChangeListener raumChangeListener;

    private SpeicherProfil aktuellesSpeicherprofil;
    private Profilauswahl profilauswahl;
    private ProfilManagerController profilManagerController;
    private LagerController lagerController;
    private RaumErstellenController raumErstellenController;

    private RaumErstellenView raumErstellenView;
    private LagerView lagerView;



    @Override
    public void init() {
        primaryViews = new HashMap<>();
        profilauswahl = new Profilauswahl();
        // Kommentar wegmachen um TestSaves zu erstellen
        // profilauswahl.createTestProfile();

        this.aktuellesSpeicherprofil = new SpeicherProfil("Standardprofil");
        this.aktuellerRaum = new Raum(2000, 3000);
        lagerView = new LagerView();
        lagerController = new LagerController(this, lagerView);

        raumErstellenView = new RaumErstellenView(false);
        raumErstellenController = new RaumErstellenController(this);

        HauptmenueView mainMenuView = new HauptmenueView(this);
        HauptmenueController hauptmenueController = new HauptmenueController(this, mainMenuView);
        primaryViews.put(PrimaryViewName.StartmenueView, mainMenuView);

        ProfilManagerView profilManagerView = new ProfilManagerView();
        profilManagerController = new ProfilManagerController(this);
        primaryViews.put(PrimaryViewName.ProfilLadenView, profilManagerView);

        primaryViews.put(PrimaryViewName.LagerView, lagerView);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;

        Scene scene = new Scene(new Pane(), 1440, 1024);
        scene.getStylesheets().add(getClass().getResource("/css/globals.css").toExternalForm());
        scene.getStylesheets().add(getClass().getResource("/css/hauptmenue.css").toExternalForm());
        scene.getStylesheets().add(getClass().getResource("/css/profilmanager.css").toExternalForm());
        primaryStage.setScene(scene);

        switchView(PrimaryViewName.StartmenueView);

        primaryStage.setTitle("StorageShelves");
        primaryStage.show();
    }

    public void showRaumErstellenPopup(boolean isEditing) {
        raumErstellenController.showPopup(primaryStage, isEditing);
    }

    public void ladeNeustesSpeicherprofil() {
        aktuellesSpeicherprofil = profilauswahl.getNeustesProfil();
        Raum loadedRaum = aktuellesSpeicherprofil.load();
        if (loadedRaum != null) {
            aktuellerRaum = loadedRaum;
            if (raumChangeListener != null) {
                raumChangeListener.onRaumChange(aktuellerRaum);
            }
        } else {
            System.err.println("Letzten Raum laden Fehlgeschlagen");
        }
    }

    public void switchView(PrimaryViewName viewName) {
        Scene currentScene = primaryStage.getScene();
        Pane nextView = primaryViews.get(viewName);
        if (viewName == PrimaryViewName.LagerView) {
            lagerController.initialize(aktuellerRaum);
        }
        if (nextView != null) {
            currentScene.setRoot(nextView);
        }
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public RaumErstellenController getRaumErstellenController() {
        return raumErstellenController;
    }

    public LagerController getLagerController() {
        return lagerController;
    }

    public void setLagerController(LagerController lagerController) {
        this.lagerController = lagerController;
    }

    public LagerView getLagerView() {
        return lagerView;
    }

    public void setLagerView(LagerView lagerView) {
        this.lagerView = lagerView;
    }

    public Raum getAktuellerRaum() {
        return aktuellerRaum;
    }

    public void setAktuellerRaum(Raum aktuellerRaum) {
        this.aktuellerRaum = aktuellerRaum;
        if (raumChangeListener != null) {
            raumChangeListener.onRaumChange(aktuellerRaum);
        }
    }

    public SpeicherProfil getAktuellesSpeicherprofil() {
        return aktuellesSpeicherprofil;
    }


    public void showProfilManager() {
        profilManagerController.showPopup(primaryStage);
    }

    public void setAktuellesSpeicherprofil(SpeicherProfil speicherProfil) {
        this.aktuellesSpeicherprofil = speicherProfil;
        if (lagerController != null) {
            lagerController.setAktuellesSpeicherprofil(speicherProfil);
        }
    }

    public Profilauswahl getProfilauswahl() {
        return profilauswahl;
    }

    public void restart() {
        primaryStage.close();
        Platform.runLater(() -> {
            try {
                StorageShelvesApplication newApp = new StorageShelvesApplication();
                Stage newStage = new Stage();
                newApp.init();
                newApp.start(newStage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public void setRaumChangeListener(RaumChangeListener listener) {
        this.raumChangeListener = listener;
    }

    public static void main(String[] args) {
        launch(args);
    }

    public interface RaumChangeListener {
        void onRaumChange(Raum newRaum);
    }
}
