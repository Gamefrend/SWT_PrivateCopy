package main.java.de.hsrm.mi.swt.view.profilmanager;

import main.java.de.hsrm.mi.swt.model.save.SpeicherProfil;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ProfilCell extends ListCell<SpeicherProfil> {
    private Label name;
    private Label datum;
    private HBox root;
    private VBox infoPane;

    public ProfilCell() {
        root = new HBox();
        infoPane = new VBox();
        name = new Label();
        datum = new Label();
        infoPane.getStyleClass().add("left-padding");
        infoPane.getChildren().addAll(name, datum);
        root.getChildren().add(infoPane);
        setGraphic(root);
    }

    @Override
    protected void updateItem(SpeicherProfil item, boolean empty) {
        super.updateItem(item, empty);
        if (empty || item == null) {
            name.setText(null);
            datum.setText(null);
            setGraphic(null);
        } else {
            name.setText(item.getSaveName());
            datum.setText(item.getDatum().toString()); // Datum kann entsprechend angepasst werden
            setGraphic(root);
        }
    }
}
