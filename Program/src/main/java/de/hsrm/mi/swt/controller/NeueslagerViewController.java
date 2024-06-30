package main.java.de.hsrm.mi.swt.controller;

import main.java.de.hsrm.mi.swt.view.PrimaryViewName;
import main.java.de.hsrm.mi.swt.app.StorageShelvesApplication;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import main.java.de.hsrm.mi.swt.view.startmenue.StartmenueView;

public class NeueslagerViewController {

    private StartmenueView root;
    private StorageShelvesApplication application;
    private Button hauptmenue;

    public NeueslagerViewController(StorageShelvesApplication application) {
        this.application = application;
        this.root = new StartmenueView();
        hauptmenue = root.getZumHauptmenue();
        initialize();
    }


    private void initialize() {
        hauptmenue.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                application.switchView(PrimaryViewName.NeuesLagerOderProfilView);
            }
        });
    }

    public Pane getRoot() {
        return root;
    }
}
