package main.java.de.hsrm.mi.swt.view.startmenue;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class StartmenueView extends BorderPane {

	private Button zumHauptmenue;

	public StartmenueView() {
		Label headerLabel = new Label("----------  Zur Hauptmenü  ----------");
		this.setTop(headerLabel);
		headerLabel.setTextFill(Color.WHITE);

		zumHauptmenue = new Button("Hauptmenü");

		VBox vbox = new VBox(10); // 10 ist der Abstand zwischen den Icons
		vbox.getChildren().addAll(headerLabel, zumHauptmenue);
		vbox.setAlignment(Pos.CENTER);

		// Füge die VBox zum Root (StackPane) hinzu
		StackPane root = new StackPane();
		root.getChildren().add(vbox);

		// Weise das Root dem BorderPane zu
		setCenter(root);
	}

	public Button getZumHauptmenue() {
		return zumHauptmenue;
	}
}
