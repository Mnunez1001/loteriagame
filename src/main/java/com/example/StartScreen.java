package com.example;

//import java.time.Duration;

import javafx.animation.ScaleTransition;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

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

    private MediaPlayer mediaPlayer;

    /**
     * Constructs the StartScreen UI and initializes its components.
     *
     * @param mainApp The main application driver that manages game flow.
     */
    public StartScreen(loteriaDriver mainApp) {
        setAlignment(Pos.CENTER);
        setSpacing(20);

        // Play intro music
        playIntroMusic();

        // Set Background Image
        Image backgroundImage =  new Image(getClass().getResource("/com/example/picado.jpg").toExternalForm());
        BackgroundImage background = new BackgroundImage(backgroundImage,
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER, new BackgroundSize(100, 100, true, true, false, true));
        this.setBackground(new Background(background));

        // Title text with drop shadow
        Text title = new Text("Lotería Game");
        title.setFont(Font.font("Luckiest Guy", FontWeight.BOLD, 60));
        title.setFill(Color.WHITE);
        title.setStyle("-fx-effect: dropshadow( gaussian, black, 3, 0.3, 0, 2);"); // Drop shadow effect

        // Slider label
        Label sliderLabel = new Label("Number of computer players:");
        //sliderLabel.setFont(new Font(20));
        sliderLabel.setFont(Font.font("Luckiest Guy", FontWeight.BOLD, 30));
        sliderLabel.setTextFill(Color.WHITE);

        // Slider to select number of computer players (1-4) + decorations
        Slider playerSlider = new Slider(0, 4, 1);
        playerSlider.setMajorTickUnit(1);
        playerSlider.setMinorTickCount(0);
        playerSlider.setSnapToTicks(true);
        playerSlider.setShowTickLabels(true);
        playerSlider.setShowTickMarks(true);
        playerSlider.setStyle("-fx-font-size: 28px; -fx-text-fill: white;");


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
        Button playButton = createStyledButton("Play");
        playButton.setOnAction(e ->{
            stopMusic();
            
            mainApp.startGame((Stage) getScene().getWindow(), numComputerPlayers);
        
        });

        // Add components to the layout
        getChildren().addAll(title, playButton, sliderLabel, playerSlider);
    }


    private Button createStyledButton(String text) {
        Button button = new Button(text);
        button.setFont(Font.font("Baloo", FontWeight.BOLD, 30));
        button.setTextFill(Color.WHITE);
        button.setStyle("-fx-background-color: #FF5733; -fx-background-radius: 15; -fx-padding: 10 20;");
        
        // Hover Effect - Scale Up
        button.setOnMouseEntered(e -> scaleButton(button, 1.1));
        button.setOnMouseExited(e -> scaleButton(button, 1.0));

        return button;
    }

    private void scaleButton(Button button, double scale) {
        ScaleTransition st = new ScaleTransition(Duration.seconds(0.15), button);
        st.setToX(scale);
        st.setToY(scale);
        st.play();
    }

    private void playIntroMusic() {
        try {
            String musicFile = "/com/example/intro.mp3"; // Adjust path if needed
            Media sound = new Media(getClass().getResource(musicFile).toExternalForm());
            mediaPlayer = new MediaPlayer(sound);
            mediaPlayer.setVolume(0.6); // Set volume (0.0 - 1.0)
            mediaPlayer.play();
        } catch (Exception e) {
            System.out.println("Error loading music: " + e.getMessage());
        }
    }

    public void stopMusic() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.dispose(); // Free up system resources
            mediaPlayer = null; // Prevent further unintended use
        }
    }







}
