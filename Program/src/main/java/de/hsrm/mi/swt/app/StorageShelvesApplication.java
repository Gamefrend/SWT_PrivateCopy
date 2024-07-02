package main.java.de.hsrm.mi.swt.app;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.util.HashMap;
import java.util.Map;

import main.java.de.hsrm.mi.swt.view.PrimaryViewName;
import main.java.de.hsrm.mi.swt.view.profilmanager.ProfilManagerView;
import main.java.de.hsrm.mi.swt.view.startmenue.hauptmenueView;
import main.java.de.hsrm.mi.swt.view.startmenue.ProfilLadenOverlayView;
import main.java.de.hsrm.mi.swt.view.lager.LagerView;

public class StorageShelvesApplication extends Application {
	private Stage primaryStage;
	private Map<PrimaryViewName, Pane> primaryViews;

	@Override
	public void init() {
		primaryViews = new HashMap<>();

		hauptmenueView mainMenuView = new hauptmenueView();
		primaryViews.put(PrimaryViewName.StartmenueView, mainMenuView);

		ProfilManagerView profilManagerView = new ProfilManagerView();
		primaryViews.put(PrimaryViewName.ProfilLadenView, profilManagerView);

		ProfilLadenOverlayView overlayView = new ProfilLadenOverlayView();
		mainMenuView.setOverlay(overlayView);

		LagerView lagerView = new LagerView();
		primaryViews.put(PrimaryViewName.LagerView, lagerView);

		// Andere Views hier hinzufügen...
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;

		Scene scene = new Scene(new Pane(), 1440, 1024);
		scene.getStylesheets().add("/css/style.css");
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
