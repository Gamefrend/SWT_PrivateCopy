package de.hsrm.mi.swt.view.neueslagersystemview;

import de.hsrm.mi.swt.view.PrimaryViewName;
import de.hsrm.mi.swt.view.StorageShelvesApplication;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class NeueslagerViewController {

    private NeuesLagerSystemView root;
    private StorageShelvesApplication application;
    private Button Hauptmenue;

    public NeueslagerViewController(StorageShelvesApplication application) {
        this.application = application;
        this.root = new NeuesLagerSystemView();
        Hauptmenue = root.halloButton;
        initialize();
    }

    private void initialize() {
        Hauptmenue.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                application.switchView(PrimaryViewName.Hauptmenue);
            }
        });
    }

    public Pane getRoot() {
        return root;
    }
}
