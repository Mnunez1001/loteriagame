package com.example;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class StartScreen extends VBox {
    public StartScreen(loteriaDriver mainApp) {
        setAlignment(Pos.CENTER);
        setSpacing(20);

        Text title = new Text("Lotería Game");
        title.setFont(new Font(50));

        Button playButton = new Button("Play");
        playButton.setFont(new Font(30));
        playButton.setOnAction(e -> mainApp.startGame());

        getChildren().addAll(title, playButton);
    }
}

