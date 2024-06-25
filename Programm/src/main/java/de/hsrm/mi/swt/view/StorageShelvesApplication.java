package de.hsrm.mi.swt.view;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class StorageShelvesApplication extends Application {
	private StackPane rootContainer;

	@Override
	public void start(Stage primaryStage) throws Exception {

		this.rootContainer = new StackPane();
		Hauptview buttons = new Hauptview(rootContainer);

		VBox vbox = new VBox(10); // 10 Pixel Abstand zwischen den Buttons
		vbox.setAlignment(Pos.CENTER); // Zentriert die Buttons in der VBox
		vbox.getChildren().addAll(buttons);

		Scene scene = new Scene(vbox, 400, 250);

		primaryStage.setTitle("JavaFX Button");

		primaryStage.setScene(scene);

		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
