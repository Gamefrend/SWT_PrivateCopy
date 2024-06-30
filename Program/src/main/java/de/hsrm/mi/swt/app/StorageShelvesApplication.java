// StorageShelvesApplication.java

package main.java.de.hsrm.mi.swt.app;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import java.util.HashMap;
import java.util.Map;

import main.java.de.hsrm.mi.swt.controller.NeuesLagerOderProfilController;
import main.java.de.hsrm.mi.swt.controller.NeueslagerViewController;
import main.java.de.hsrm.mi.swt.view.PrimaryViewName;

public class StorageShelvesApplication extends Application {
	private Stage primaryStage;
	private StackPane rootContainer;
	private HashMap<PrimaryViewName, Pane> primaryViews;
	private Map<String, Pane> scenes;

	Pane StartmenueView;
	Pane mainMenuView;

	@Override
	public void init() {

		scenes = new HashMap<>();
		primaryViews = new HashMap<>();

		NeueslagerViewController Controller = new NeueslagerViewController(this);
		StartmenueView = Controller.getRoot();
		primaryViews.put(PrimaryViewName.StartmenueView, StartmenueView);

		NeuesLagerOderProfilController Controller2 = new NeuesLagerOderProfilController(this);
		mainMenuView = Controller2.getRoot();
		primaryViews.put(PrimaryViewName.NeuesLagerOderProfilView, mainMenuView);

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

		Scene scene = new Scene(root, 1440, 1024);
		scene.getStylesheets().add("main/resources/css/style.css");
		primaryStage.setScene(scene);
		switchView(PrimaryViewName.StartmenueView);

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
