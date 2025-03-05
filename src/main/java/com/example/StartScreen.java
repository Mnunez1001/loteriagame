package com.example;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class StartScreen extends VBox {
    private int numComputerPlayers = 1; // Default to 1 computer player

    public StartScreen(loteriaDriver mainApp) {
        setAlignment(Pos.CENTER);
        setSpacing(20);

        Text title = new Text("Lotería Game");
        title.setFont(new Font(50));

        Label sliderLabel = new Label("Number of computer players:");
        sliderLabel.setFont(new Font(20));

        Slider playerSlider = new Slider(0, 4, 1);
        playerSlider.setMajorTickUnit(1);
        playerSlider.setMinorTickCount(0);
        playerSlider.setSnapToTicks(true);
        playerSlider.setShowTickLabels(true);
        playerSlider.setShowTickMarks(true);

        playerSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
            numComputerPlayers = newVal.intValue();
        });


        Button playButton = new Button("Play");
        playButton.setFont(new Font(30));
        playButton.setOnAction(e -> mainApp.startGame( (Stage)getScene().getWindow(), numComputerPlayers));

        getChildren().addAll(title, playButton, sliderLabel, playerSlider);
    }
}

