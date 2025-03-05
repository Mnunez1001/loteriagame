package com.example;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class WinScreen extends VBox {
    public WinScreen(Stage primaryStage, String winnerName, loteriaDriver mainApp) {
        setSpacing(20);
        setAlignment(Pos.CENTER);

        Text winMessage = new Text(winnerName + " won the game!");
        winMessage.setStyle("-fx-font-size: 40px; -fx-font-weight: bold;");

        Button backButton = new Button("Back to Menu");
        backButton.setStyle("-fx-font-size: 20px;");
        backButton.setOnAction(e -> {
            StartScreen startScreen = new StartScreen(mainApp);
            primaryStage.setScene(new Scene(startScreen, 1300, 850));
        });

        getChildren().addAll(winMessage, backButton);
    }
}
