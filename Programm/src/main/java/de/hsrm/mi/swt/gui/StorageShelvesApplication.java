package de.hsrm.mi.swt.gui;

import de.hsrm.mi.swt.hauptview.Hauptview;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class StorageShelvesApplication extends Application {
	@Override
	public void start(Stage primaryStage) throws Exception {

		Hauptview buttons = new Hauptview();

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
