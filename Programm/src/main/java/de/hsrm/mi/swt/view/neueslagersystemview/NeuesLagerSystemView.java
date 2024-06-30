package main.java.de.hsrm.mi.swt.view.neueslagersystemview;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class NeuesLagerSystemView extends BorderPane {

	Button halloButton;

	public NeuesLagerSystemView() {
		Label headerLabel = new Label("----------  Zur Hauptmenü  ----------");
		this.setTop(headerLabel);
		headerLabel.setTextFill(Color.WHITE);

		halloButton = new Button("Hauptmenü");

		VBox vbox = new VBox(10); // 10 ist der Abstand zwischen den Icons
		vbox.getChildren().addAll(headerLabel, halloButton);
		vbox.setAlignment(Pos.CENTER);

		// Füge die VBox zum Root (StackPane) hinzu
		StackPane root = new StackPane();
		root.getChildren().add(vbox);

		// Weise das Root dem BorderPane zu
		setCenter(root);
	}

}
