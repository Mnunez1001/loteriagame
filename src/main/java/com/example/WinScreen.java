package com.example;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class WinScreen extends VBox {
    public WinScreen(Stage primaryStage) {
        setAlignment(Pos.CENTER);
        setSpacing(20);

        Text message = new Text("Congratulations! You have won the game!");
        message.setFont(new Font(30));

        Button playAgainButton = new Button("Play a new match");
        playAgainButton.setFont(new Font(20));
        //playAgainButton.setOnAction(e -> primaryStage.setScene(new Scene(new GamePane(primaryStage), 1300, 850)));

        playAgainButton.setOnAction(e -> {
            WinningCondition.generateNewCondition(); // Force a new condition
            primaryStage.setScene(new Scene(new GamePane(primaryStage), 1300, 850));
        });
        


        getChildren().addAll(message, playAgainButton);
    }
}
