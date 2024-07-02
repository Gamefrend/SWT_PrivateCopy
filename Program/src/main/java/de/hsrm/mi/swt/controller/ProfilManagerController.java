package main.java.de.hsrm.mi.swt.controller;

import main.java.de.hsrm.mi.swt.app.StorageShelvesApplication;
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

    ListView<SpeicherProfil> profileView;
    ProfilManagerView root;
    private ObservableList<SpeicherProfil> profiles;

    public ProfilManagerController(StorageShelvesApplication application, ProfilManagerView profilManagerView) {
        this.application = application;
        rootContainer = new StackPane();
        root = profilManagerView;
        profileView = root.getProfileView();
        profiles = FXCollections.observableArrayList();

        initialize();
    }

    public void initialize() {
        profileView.setItems(profiles);
        profileView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                Raum raum = newValue.load();
                // Hier können wir den Raum zurückgeben oder weiterverarbeiten
                System.out.println("Raum: " + raum.getHoehe() + " | " + raum.getBreite());
            }
        });
        loadProfiles();
    }

    private void loadProfiles() {
        // Hier werden gespeicherte Profile geladen
        SpeicherProfil sp1 = new SpeicherProfil("1");
        SpeicherProfil sp2 = new SpeicherProfil("2");
        sp1.setSaveName("1");
        sp2.setSaveName("2");
        profiles.add(sp1);
        profiles.add(sp2);
    }

    public void switchView(PrimaryViewName viewName) {
        Scene currentScene = primaryStage.getScene();
        Pane nextView = primaryViews.get(viewName);
        if (nextView != null) {
            currentScene.setRoot(nextView);
        }
    }

    public Pane getRoot() {
        return root;
    }
}
