package de.hsrm.mi.swt.view;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

public class TestView extends StackPane {

    public TestView() {
        Label label = new Label("Hier ist das neue Lagersystem! -------------------------------------------------");
        getChildren().add(label);

        setAlignment(Pos.CENTER);
        setId("neues-lagersystem-view");
    }
}
