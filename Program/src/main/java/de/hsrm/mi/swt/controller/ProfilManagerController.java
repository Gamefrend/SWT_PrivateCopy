package de.hsrm.mi.swt.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import de.hsrm.mi.swt.app.StorageShelvesApplication;
import de.hsrm.mi.swt.model.save.Profilauswahl;
import de.hsrm.mi.swt.model.save.SpeicherProfil;
import de.hsrm.mi.swt.model.storage.Raum;
import de.hsrm.mi.swt.view.PrimaryViewName;
import de.hsrm.mi.swt.view.profilmanager.ProfilManagerView;
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
    private Button closeButton;

    public ProfilManagerController(StorageShelvesApplication application) {
        this.application = application;
        this.profilManagerView = new ProfilManagerView();
        this.popup = new Popup();
        this.popup.getContent().add(profilManagerView);
        this.profileView = profilManagerView.getProfileView();
        this.profiles = FXCollections.observableArrayList();
        this.closeButton = profilManagerView.getCloseButton();
        this.profilauswahl = application.getProfilauswahl();
        initialize();
    }

    public void initialize() {
        profiles.addAll(profilauswahl.getSpeicherProfile());
        profileView.setItems(profiles);
        closeButton.addEventHandler(ActionEvent.ACTION, e -> hidePopup());
        profileView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                Raum raum = newValue.load();
                System.out.println("Raum: " + raum.getHoehe() + " | " + raum.getBreite());
                application.setAktuellerRaum(raum);
                application.setAktuellesSpeicherprofil(newValue);
                hidePopup();
            }
        });
        profilManagerView.setOnDeleteAction(this::deleteProfile);
        profilManagerView.setOnEditAction(this::editProfile);
    }

    private void deleteProfile(SpeicherProfil profile) {
        profilauswahl.delProfile(profile);
        profiles.remove(profile);
    }

    private void editProfile(SpeicherProfil profile, String newName) {
        profilauswahl.renameProfile(profile, newName);
        profilauswahl = new Profilauswahl();
        profileView.refresh();
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
