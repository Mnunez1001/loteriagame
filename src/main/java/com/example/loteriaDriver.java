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
    public enum Difficulty {
        EASY, MEDIUM, HARD
    }

    private static Scene scene;
    private Stage stage;
    private Difficulty selectedDifficulty = Difficulty.MEDIUM; // Default to MEDIUM

    public void setDifficulty(Difficulty difficulty) {
        this.selectedDifficulty = difficulty;
    }

    public Difficulty getDifficulty() {
        return selectedDifficulty;
    }

    @Override
    public void start(Stage stage) throws IOException {
        this.stage = stage;
        showStartScreen();
        stage.setTitle("Lotería Game");
        stage.show();
    }

    public void showStartScreen() {
        StartScreen startScreen = new StartScreen(this);
        Scene scene = new Scene(startScreen, 1300, 850);
        stage.setScene(scene);
    }

    public void showInstructionsScreen() {
        InstructionsScreen instructionsScreen = new InstructionsScreen(stage, this);
        Scene instructionsScene = new Scene(instructionsScreen, 1300, 850);
        stage.setScene(instructionsScene);
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