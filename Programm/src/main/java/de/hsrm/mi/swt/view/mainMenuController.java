package de.hsrm.mi.swt.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import java.util.HashMap;

public class mainMenuController {

    private HashMap<PrimaryViewName, Pane> primaryViews;
    private Stage primaryStage;
    private StackPane rootContainer;
    mainMenuView root;
    Button NeuesLagerBtn;

    private StorageShelvesApplication application;

    public mainMenuController(StorageShelvesApplication application) {
        this.application = application;

        root = new mainMenuView();
        rootContainer = new StackPane();
        NeuesLagerBtn = root.getNewSystemButton();

        initialize();
    }

    // EventHandler als Anonyom Klasse
    public void initialize() {

        NeuesLagerBtn.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                application.switchView(PrimaryViewName.NeuesLagerSystemView);
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
