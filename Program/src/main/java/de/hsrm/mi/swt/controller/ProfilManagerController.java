package main.java.de.hsrm.mi.swt.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import main.java.de.hsrm.mi.swt.app.StorageShelvesApplication;
import main.java.de.hsrm.mi.swt.model.save.Profilauswahl;
import main.java.de.hsrm.mi.swt.model.save.SpeicherProfil;
import main.java.de.hsrm.mi.swt.model.storage.Raum;
import main.java.de.hsrm.mi.swt.view.profilmanager.ProfilManagerView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.stage.Popup;
import javafx.stage.Window;

public class ProfilManagerController {
    private StorageShelvesApplication application;
    private ProfilManagerView profilManagerView;
    private Popup popup;
    private Profilauswahl profilauswahl;
    private ListView<SpeicherProfil> profileView;
    private ObservableList<SpeicherProfil> profiles;
    private Button menueButton;

    public ProfilManagerController(StorageShelvesApplication application) {
        this.application = application;
        this.profilManagerView = new ProfilManagerView();
        this.popup = new Popup();
        this.popup.getContent().add(profilManagerView);
        this.profileView = profilManagerView.getProfileView();
        this.profiles = FXCollections.observableArrayList();
        this.menueButton = profilManagerView.getMenueButton();
        initialize();
    }

    public void initialize() {
        profilauswahl = new Profilauswahl();
        profiles.addAll(profilauswahl.getSpeicherProfile());
        profileView.setItems(profiles);
        profileView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                Raum raum = newValue.load();
                System.out.println("Raum: " + raum.getHoehe() + " | " + raum.getBreite());
                application.setAktuellerRaum(raum);
                application.setAktuellesSpeicherprofil(newValue);
                hidePopup();
            }
        });
        menueButton.addEventHandler(ActionEvent.ACTION, e -> hidePopup());
    }

    public void showPopup(Window owner) {
        if (!popup.isShowing()) {
            popup.show(owner);
        }
    }

    public void hidePopup() {
        popup.hide();
    }
}
