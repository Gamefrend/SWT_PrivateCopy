// StorageShelvesApplication.java

package main.java.de.hsrm.mi.swt.view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.HashMap;
import java.util.Map;

import main.java.de.hsrm.mi.swt.view.neueslagersystemview.NeuesLagerSystemView;
import main.java.de.hsrm.mi.swt.view.neueslagersystemview.NeueslagerViewController;

public class StorageShelvesApplication extends Application {
	private Stage primaryStage;
	private StackPane rootContainer;
	private HashMap<PrimaryViewName, Pane> primaryViews;
	private Map<String, Pane> scenes;

	Pane NeuesLagerSystemView;
	Pane mainMenuView;

	@Override
	public void init() {

		scenes = new HashMap<>();
		primaryViews = new HashMap<>();

		NeueslagerViewController Controller = new NeueslagerViewController(this);
		NeuesLagerSystemView = Controller.getRoot();
		primaryViews.put(PrimaryViewName.NeuesLagerSystemView, NeuesLagerSystemView);

		mainMenuController Controller2 = new mainMenuController(this);
		mainMenuView = Controller2.getRoot();
		primaryViews.put(PrimaryViewName.Hauptmenue, mainMenuView);

		scenes.putIfAbsent("firstView", Controller.getRoot());
		scenes.putIfAbsent("secondView", Controller2.getRoot());

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
		this.rootContainer = new StackPane();
		// mainMenuView buttons = new mainMenuView(rootContainer);

		// Fügen Sie die Header-HBox zur VBox hinzu
		Pane root = new Pane();

		Scene scene = new Scene(root, 1440, 500);
		scene.getStylesheets().add("main/resources/css/style.css");
		primaryStage.setScene(scene);
		switchView(PrimaryViewName.NeuesLagerSystemView);

		primaryStage.setTitle("StorageShelves");
		primaryStage.show();
	}

	public void switchView(PrimaryViewName viewName) {
		Scene currentScene = primaryStage.getScene();

		Pane nextView = primaryViews.get(viewName);
		if (nextView != null) {
			currentScene.setRoot(nextView);
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
