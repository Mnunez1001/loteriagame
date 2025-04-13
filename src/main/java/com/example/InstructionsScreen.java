package com.example;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * The InstructionsScreen class represents a screen displaying the rules and
 * instructions
 * for playing the Lotería game. It includes a title, game rules, and a button
 * to return to the main menu.
 * 
 * @author Miguel Alexander Nunez Palomares
 * @version 1.0
 * @see javafx.scene.Scene, javafx.scene.control.Button,
 *      javafx.scene.layout.VBox, javafx.scene.media.Media,
 *      javafx.scene.media.MediaPlayer,
 */

public class InstructionsScreen extends VBox {
    private MediaPlayer mediaPlayer;

    /**
     * Constructs an InstructionsScreen object with the provided stage and main
     * application.
     *
     * @param stage   The primary stage of the application.
     * @param mainApp The main application driver to navigate back to the
     *                StartScreen.
     */
    public InstructionsScreen(Stage stage, loteriaDriver mainApp) {
        setAlignment(Pos.CENTER);
        setSpacing(40);
        playGameMusic();

        // Set Background Image
        Image backgroundImage = new Image(getClass().getResource("/com/example/blue.jpg").toExternalForm());
        BackgroundImage background = new BackgroundImage(backgroundImage,
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER, new BackgroundSize(100, 100, true, true, false, true));
        this.setBackground(new Background(background));

        // Title
        Text title = new Text("How to Play Lotería");
        title.setFont(Font.font("Luckiest Guy", FontWeight.BOLD, 50));
        title.setFill(Color.WHITE);

        // Game Rules Description
        Text instructions = new Text(
                "Object of the Game:\n" +
                        "Players try to match the drawn cards with images on their Loteria board.\n" +
                        "The first player to complete a winning pattern(e.g., row, column, diagonal, or full board) wins! by clicking the Loteria button\n\n"
                        +
                        "Game Setup:\n" +
                        "Each player receives a Loteria board filled with random images.\n" +
                        "A caller randomly draws cards from a deck and shows them to the players.\n\n" +
                        "Gameplay:\n" +
                        " The game starts by drawing one card at a time from the deck.\n" +
                        "Players look for the drawn card on their boards and mark it with a bean or a chip.\n" +
                        "If a player marks a winning pattern, they click 'Lotería!' to claim victory.\n\n" +
                        "Winning Patterns:\n" +
                        " The game ends when a player achieves a valid pattern and clicks 'Lotería!'.\n" +
                        "Common winning patterns include:\n" +
                        " A full row (horizontal)\n" +
                        " A full column (vertical)\n" +
                        " A diagonal (from corner to corner)\n" +
                        " A full board (all images marked)\n\n" +
                        "Game Difficulty:\n" +
                        " Easy: Cards are drawn slowly, allowing players to keep up. Every 4 seconds\n" +
                        " Medium: Cards are drawn at a moderate pace. Every 2.5 seconds\n" +
                        " Hard: Cards are drawn quickly, increasing the challenge. Every 1 second");
        instructions.setFont(Font.font("Baloo", FontWeight.BOLD, 17));
        instructions.setFill(Color.WHITE);
        instructions.setWrappingWidth(600); // Wrap text for better readability

        // Back Button to return to StartScreen
        Button backButton = new Button("Back to Menu");
        backButton.setFont(Font.font("Baloo", FontWeight.BOLD, 30));
        backButton.setTextFill(Color.WHITE);
        backButton.setStyle("-fx-background-color: #FF5733; -fx-background-radius: 15; -fx-padding: 10 20;");
        backButton.setOnAction(e -> {
            stopMusic(); // Stop the music when going back
            mainApp.showStartScreen();

        });

        // Add components to the layout
        getChildren().addAll(title, instructions, backButton);

    }

    /**
     * Plays background music for the instructions screen.
     * Handles any exceptions if the file cannot be loaded.
     */
    private void playGameMusic() {
        try {
            String musicFile = "/com/example/win2.mp3"; // Adjust path if needed
            Media sound = new Media(getClass().getResource(musicFile).toExternalForm());
            mediaPlayer = new MediaPlayer(sound);
            mediaPlayer.setVolume(0.6); // Set volume (0.0 - 1.0)
            mediaPlayer.setCycleCount(5); // Ensure music plays only once
            mediaPlayer.play();
        } catch (Exception e) {
            System.out.println("Error loading music: " + e.getMessage());
        }
    }

    /**
     * Stops the currently playing background music and releases system resources.
     */
    public void stopMusic() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.dispose(); // Free up system resources
            mediaPlayer = null; // Prevent further unintended use
        }
    }
}
