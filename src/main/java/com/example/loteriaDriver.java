package com.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

/**
 * JavaFX loteriaDriver
 *
 */
public class loteriaDriver extends Application {

    private static Scene scene;
    private Stage stage;

    @Override
    public void start(Stage stage) throws IOException {
        this.stage = stage;
        showStartScreen();
        stage.setTitle("Lotería Game");
        stage.show();
    }

    private void showStartScreen() {
        StartScreen startScreen = new StartScreen(this);
        Scene scene = new Scene(startScreen, 1300, 850);
        stage.setScene(scene);
    }

    public void startGame(Stage primaryStage, int numComputerPlayers) {
        WinningCondition.generateNewCondition(); // Generate a new winning condition
        primaryStage.setScene(new Scene(new GamePane(primaryStage, numComputerPlayers, this), 1300, 850));
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