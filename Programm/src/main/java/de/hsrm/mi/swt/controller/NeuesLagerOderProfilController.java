package main.java.de.hsrm.mi.swt.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import main.java.de.hsrm.mi.swt.view.startmenue.hauptmenueView;
import main.java.de.hsrm.mi.swt.view.PrimaryViewName;
import main.java.de.hsrm.mi.swt.app.StorageShelvesApplication;

import java.util.HashMap;

public class NeuesLagerOderProfilController {

    private HashMap<PrimaryViewName, Pane> primaryViews;
    private Stage primaryStage;
    private StackPane rootContainer;
    hauptmenueView root;
    Button neuesLagerBtn;

    private StorageShelvesApplication application;

    public NeuesLagerOderProfilController(StorageShelvesApplication application) {
        this.application = application;

        root = new hauptmenueView();
        rootContainer = new StackPane();
        neuesLagerBtn = root.getNewSystemButton();

        initialize();
    }

    public void initialize() {
        neuesLagerBtn.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                application.switchView(PrimaryViewName.StartmenueView);
            }
        });

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
