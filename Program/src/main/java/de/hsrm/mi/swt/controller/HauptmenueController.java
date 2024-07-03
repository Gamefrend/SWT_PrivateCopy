package main.java.de.hsrm.mi.swt.controller; //main.java.

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import main.java.de.hsrm.mi.swt.view.startmenue.HauptmenueView; //main.java.
import main.java.de.hsrm.mi.swt.view.PrimaryViewName;
import main.java.de.hsrm.mi.swt.app.StorageShelvesApplication;

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
        neuesLagerBtn.addEventHandler(ActionEvent.ACTION, e -> application.switchView(PrimaryViewName.LagerView));
        manageProfileButton.addEventHandler(ActionEvent.ACTION, e -> application.switchView(PrimaryViewName.ProfilLadenView));
        loadProfileButton.addEventHandler(ActionEvent.ACTION, e ->{
                application.ladeNeustesSpeicherprofil();
                application.switchView(PrimaryViewName.ProfilLadenView);});

    }
}
