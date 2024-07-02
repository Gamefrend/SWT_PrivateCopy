package main.java.de.hsrm.mi.swt.view.lager;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TestLagerView extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Erstellen Sie eine Instanz der LagerView
        LagerView lagerView = new LagerView();

        // Erstellen Sie eine Szene und fügen Sie die LagerView hinzu
        Scene scene = new Scene(lagerView, 800, 600);

        // Optional: CSS-Styling hinzufügen
        scene.getStylesheets().add("main/resources/css/style.css");

        // Konfigurieren und Anzeigen der Stage
        primaryStage.setTitle("LagerView Test");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
