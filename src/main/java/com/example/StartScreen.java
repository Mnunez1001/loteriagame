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

/**
 * Represents the start screen of the Lotería game.
 * This screen allows the player to select the number of computer opponents and
 * start the game.
 * 
 * @author Miguel Alexander Nunez Palomares
 * @version 1.0
 * @see javafx.geometry.Pos, javafx.scene.Scene, javafx.scene.control.Button,
 *      javafx.scene.control.Label,
 *      javafx.scene.control.Slider, javafx.scene.layout.VBox,
 *      javafx.scene.text.Font, javafx.scene.text.Text, javafx.stage.Stage
 */
public class StartScreen extends VBox {
    private int numComputerPlayers = 1; // Default to 1 computer player

    /**
     * Constructs the StartScreen UI and initializes its components.
     *
     * @param mainApp The main application driver that manages game flow.
     */
    public StartScreen(loteriaDriver mainApp) {
        setAlignment(Pos.CENTER);
        setSpacing(20);

        // Title text
        Text title = new Text("Lotería Game");
        title.setFont(new Font(50));

        // Slider label
        Label sliderLabel = new Label("Number of computer players:");
        sliderLabel.setFont(new Font(20));

        // Slider to select number of computer players (1-4) + decorations
        Slider playerSlider = new Slider(0, 4, 1);
        playerSlider.setMajorTickUnit(1);
        playerSlider.setMinorTickCount(0);
        playerSlider.setSnapToTicks(true);
        playerSlider.setShowTickLabels(true);
        playerSlider.setShowTickMarks(true);

        /**
         * playerSlider.valueProperty() gets the current value of the slider.
         * .addListener((obs, oldVal, newVal) -> { ... }) adds a listener that reacts
         * whenever the slider’s value changes.
         * 
         * .intValue() converts that double to an integer, ensuring numComputerPlayers holds an integer value.
         * 
         */
        playerSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
            numComputerPlayers = newVal.intValue();
        });

        // Play button to start the game
        Button playButton = new Button("Play");
        playButton.setFont(new Font(30));
        playButton.setOnAction(e -> mainApp.startGame((Stage) getScene().getWindow(), numComputerPlayers));

        // Add components to the layout
        getChildren().addAll(title, playButton, sliderLabel, playerSlider);
    }
}
