package de.hsrm.mi.swt.controller;

import de.hsrm.mi.swt.model.save.SpeicherProfil;
import de.hsrm.mi.swt.model.storage.Raum;
import de.hsrm.mi.swt.view.lager.LagerView;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import de.hsrm.mi.swt.view.startmenue.HauptmenueView;
import de.hsrm.mi.swt.view.PrimaryViewName;
import de.hsrm.mi.swt.app.StorageShelvesApplication;

import java.util.HashMap;

public class HauptmenueController {

    private HashMap<PrimaryViewName, Pane> primaryViews;
    private Stage primaryStage;
    private StackPane rootContainer;
    HauptmenueView hauptmenueView;
    Button neuesLagerBtn;

    Button loadProfileButton;
    Button manageProfileButton;

    private StorageShelvesApplication application;

    public HauptmenueController(StorageShelvesApplication application, HauptmenueView hauptmenueView) {

        this.application = application;
        this.hauptmenueView = hauptmenueView;
        rootContainer = new StackPane();
        neuesLagerBtn = hauptmenueView.getNewSystemButton();
        manageProfileButton = hauptmenueView.getManageProfileButton();
        loadProfileButton = hauptmenueView.getLoadProfileButton();

        initialize();
    }

    public void initialize() {
        neuesLagerBtn.addEventHandler(ActionEvent.ACTION, e -> {
            application.setAktuellerRaum(new Raum(100,200));
            application.setAktuellesSpeicherprofil(new SpeicherProfil("TestProfile0"));
            application.switchView(PrimaryViewName.LagerView);
        });
        manageProfileButton.addEventHandler(ActionEvent.ACTION, e -> application.showProfilManager());
        loadProfileButton.addEventHandler(ActionEvent.ACTION, e ->{
                application.ladeNeustesSpeicherprofil();
                application.switchView(PrimaryViewName.LagerView);});

    }
}
