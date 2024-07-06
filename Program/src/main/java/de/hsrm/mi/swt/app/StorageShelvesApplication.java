package de.hsrm.mi.swt.app;

import javafx.application.Application;
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
import de.hsrm.mi.swt.view.startmenue.ProfilLadenOverlayView;
import de.hsrm.mi.swt.view.lager.LagerView;

public class StorageShelvesApplication extends Application {
    private Stage primaryStage;
    private Map<PrimaryViewName, Pane> primaryViews;

    private Raum aktuellerRaum;

    private SpeicherProfil aktuellesSpeicherprofil;
    private Profilauswahl profilauswahl;
    private ProfilManagerController profilManagerController;

    @Override
    public void init() {
        primaryViews = new HashMap<>();
        profilauswahl = new Profilauswahl();
        //Kommentar wegmachen um TestSaves zu erstellen
        //profilauswahl.ceateTestProfile();

        HauptmenueView mainMenuView = new HauptmenueView();
        HauptmenueController hauptmenueController = new HauptmenueController(this, mainMenuView);
        primaryViews.put(PrimaryViewName.StartmenueView, mainMenuView);

        ProfilManagerView profilManagerView = new ProfilManagerView();
        profilManagerController = new ProfilManagerController(this);
        primaryViews.put(PrimaryViewName.ProfilLadenView, profilManagerView);

        ProfilLadenOverlayView overlayView = new ProfilLadenOverlayView();
        mainMenuView.setOverlay(overlayView);


        LagerView lagerView = new LagerView();
        SpeicherProfil speicherProfil = new SpeicherProfil("1");
        LagerController lagerController = new LagerController(this, lagerView);
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

    public void switchView(PrimaryViewName viewName) {
        Scene currentScene = primaryStage.getScene();
        Pane nextView = primaryViews.get(viewName);
        if (nextView != null) {
            currentScene.setRoot(nextView);
        }
    }

    public Raum getAktuellerRaum() {
        return aktuellerRaum;
    }

    public void setAktuellerRaum(Raum aktuellerRaum) {
        this.aktuellerRaum = aktuellerRaum;
    }

    public SpeicherProfil getAktuellesSpeicherprofil() {
        return aktuellesSpeicherprofil;
    }

    public void setAktuellesSpeicherprofil(SpeicherProfil aktuellesSpeicherprofil) {
        this.aktuellesSpeicherprofil = aktuellesSpeicherprofil;
    }

    public void ladeNeustesSpeicherprofil() {
        aktuellesSpeicherprofil = profilauswahl.getNeustesProfil();
        aktuellerRaum = aktuellesSpeicherprofil.load();
    }

    public void showProfilManager() {
        profilManagerController.showPopup(primaryStage);
    }

    public Profilauswahl getProfilauswahl() {
        return profilauswahl;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
