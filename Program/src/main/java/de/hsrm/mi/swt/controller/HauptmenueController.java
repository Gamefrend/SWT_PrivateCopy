package de.hsrm.mi.swt.controller;

import de.hsrm.mi.swt.app.StorageShelvesApplication;
import de.hsrm.mi.swt.model.save.SpeicherProfil;
import de.hsrm.mi.swt.model.storage.Raum;
import de.hsrm.mi.swt.view.startmenue.HauptmenueView;
import de.hsrm.mi.swt.view.PrimaryViewName;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.util.HashMap;

public class HauptmenueController {

    private HashMap<PrimaryViewName, Pane> primaryViews;
    private Stage primaryStage;
    private StackPane rootContainer;
    private HauptmenueView hauptmenueView;
    private Button neuesLagerBtn;
    private Button loadProfileButton;
    private Button manageProfileButton;
    private StorageShelvesApplication application;
    private RaumErstellenController raumErstellenController;

    public HauptmenueController(StorageShelvesApplication application, HauptmenueView hauptmenueView) {
        this.application = application;
        this.hauptmenueView = hauptmenueView;
        rootContainer = new StackPane();
        neuesLagerBtn = hauptmenueView.getNewSystemButton();
        manageProfileButton = hauptmenueView.getManageProfileButton();
        loadProfileButton = hauptmenueView.getLoadProfileButton();

        raumErstellenController = application.getRaumErstellenController();

        initialize();
    }

    public void initialize() {
        neuesLagerBtn.addEventHandler(ActionEvent.ACTION, e -> raumErstellenController.showPopup(application.getPrimaryStage()));
        manageProfileButton.addEventHandler(ActionEvent.ACTION, e -> application.showProfilManager());
        loadProfileButton.addEventHandler(ActionEvent.ACTION, e -> {
            application.ladeNeustesSpeicherprofil();
            application.switchView(PrimaryViewName.LagerView);
        });
    }
}
