package de.hsrm.mi.swt.gui;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class StorageShelvesApplication extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {

		VBox vbox = new VBox();
		vbox.setAlignment(Pos.CENTER);

		primaryStage.setScene(new Scene(vbox));
		primaryStage.setTitle("Storage Shelves App");
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
