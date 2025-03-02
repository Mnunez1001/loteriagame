package com.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX loteriaDriver
 *
 */
public class loteriaDriver extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        GamePane gamePane = new GamePane();
        scene = new Scene(gamePane, 900, 600);
        stage.setTitle("Lotería Game");
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(loteriaDriver.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}