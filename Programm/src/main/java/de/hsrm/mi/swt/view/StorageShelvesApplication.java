// StorageShelvesApplication.java

package de.hsrm.mi.swt.view;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class StorageShelvesApplication extends Application {
	private StackPane rootContainer;

	@Override
	public void start(Stage primaryStage) throws Exception {
		this.rootContainer = new StackPane();
		mainMenuView buttons = new mainMenuView(rootContainer);

		VBox vbox = new VBox(10); // 10 Pixel Abstand zwischen den Elementen
		vbox.getChildren().addAll(buttons); // Fügen Sie die Header-HBox zur VBox hinzu

		Scene scene = new Scene(vbox, 1440, 1024);
		scene.getStylesheets().add("/css/style.css");

		primaryStage.setTitle("StorageShelves");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
