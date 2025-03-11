package com.example;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Represents the win screen displayed when a player wins the game.
 * Shows a message announcing the winner and provides a button to return to the main menu.
 * It extends VBox to allow for easy vertical alignment of its children.
 * 
 * @author Miguel Alexander Nunez Palomares
 * @version 1.0
 * @see javafx.geometry.Pos, javafx.scene.Scene, javafx.scene.control.Button, javafx.scene.layout.VBox, 
 * javafx.scene.text.Font, javafx.scene.text.Text, javafx.stage.Stage
 */

public class WinScreen extends VBox {


    /**
     * Constructs a WinScreen displaying the winner's name and a button to return to the menu.
     *
     * @param primaryStage The main stage where scenes are set.
     * @param winnerName The name of the player who won the game.
     * @param mainApp The main application driver, used to return to the start screen.
     */
    public WinScreen(Stage primaryStage, String winnerName, loteriaDriver mainApp) {
        setSpacing(20);
        setAlignment(Pos.CENTER);

        // Display the winning message
        Text winMessage = new Text(winnerName + " won the game!");
        winMessage.setStyle("-fx-font-size: 40px; -fx-font-weight: bold;");

        // Button to return to the main menu
        Button backButton = new Button("Back to Menu");
        backButton.setStyle("-fx-font-size: 20px;");
        backButton.setOnAction(e -> {
            StartScreen startScreen = new StartScreen(mainApp);
            primaryStage.setScene(new Scene(startScreen, 1300, 850));
        });

        // Add elements to the layout
        getChildren().addAll(winMessage, backButton);
    }
}
