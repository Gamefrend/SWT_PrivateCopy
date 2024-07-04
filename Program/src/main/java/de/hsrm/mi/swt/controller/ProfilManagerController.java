package main.java.de.hsrm.mi.swt.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import main.java.de.hsrm.mi.swt.app.StorageShelvesApplication;
import main.java.de.hsrm.mi.swt.model.save.Profilauswahl;
import main.java.de.hsrm.mi.swt.model.save.SpeicherProfil;
import main.java.de.hsrm.mi.swt.model.storage.Raum;
import main.java.de.hsrm.mi.swt.view.PrimaryViewName;
import main.java.de.hsrm.mi.swt.view.profilmanager.ProfilManagerView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.HashMap;

public class ProfilManagerController {
    private HashMap<PrimaryViewName, Pane> primaryViews;
    private Stage primaryStage;
    private StackPane rootContainer;
    private StorageShelvesApplication application;

    private Profilauswahl profilauswahl;

    private ListView<SpeicherProfil> profileView;
    private ProfilManagerView profilManagerView;
    private ObservableList<SpeicherProfil> profiles;

    private Button menueButton;

    public ProfilManagerController(StorageShelvesApplication application, ProfilManagerView profilManagerView) {
        this.application = application;
        rootContainer = new StackPane();
        this.profilManagerView = profilManagerView;
        profileView = profilManagerView.getProfileView();
        profiles = FXCollections.observableArrayList();
        menueButton = profilManagerView.getMenueButton();
        initialize();
    }

    public void initialize() {
        profilauswahl = new Profilauswahl();
        profiles.addAll(profilauswahl.getSpeicherProfile());
        profileView.setItems(profiles);
        profileView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                Raum raum = newValue.load();
                // Hier können wir den Raum zurückgeben oder weiterverarbeiten
                System.out.println("Raum: " + raum.getHoehe() + " | " + raum.getBreite());
            }
        });
        menueButton.addEventHandler(ActionEvent.ACTION, e -> application.switchView(PrimaryViewName.StartmenueView));;
    }


    public void switchView(PrimaryViewName viewName) {
        Scene currentScene = primaryStage.getScene();
        Pane nextView = primaryViews.get(viewName);
        if (nextView != null) {
            currentScene.setRoot(nextView);
        }
    }

    public Pane getProfilManagerView() {
        return profilManagerView;
    }
}
