package main.java.de.hsrm.mi.swt.view.lager;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class LagerView extends StackPane {

    private Pane centerArea;
    private HBox inventoryBox;

    public LagerView() {
        setId("lager-view");
        setPadding(new Insets(20));

        // Zentrum
        centerArea = new Pane();
        centerArea.setId("Lager-center-Area");
        centerArea.setStyle("-fx-border-color: black; -fx-background-color: white; -fx-min-height: 400px;");

        // Inventar-Bereich
        inventoryBox = new HBox(10);
        inventoryBox.setId("inventory-box");
        inventoryBox.setAlignment(Pos.CENTER);
        inventoryBox.setPadding(new Insets(10));
        inventoryBox.setStyle("-fx-background-color: #f0f0f0; -fx-border-color: black;");

        // Hauptlayout
        VBox mainLayout = new VBox(20, centerArea, inventoryBox);
        mainLayout.setAlignment(Pos.CENTER);

        getChildren().add(mainLayout);
    }

    public Pane getCenterArea() {
        return centerArea;
    }

    public HBox getInventoryBox() {
        return inventoryBox;
    }
}
