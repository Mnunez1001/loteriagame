package com.example;

import java.io.File;
import java.util.Random;


import javafx.animation.AnimationTimer;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;



/**
 * Represents the win screen displayed when a player wins the game.
 * Shows a message announcing the winner and provides a button to return to the
 * main menu.
 * It extends VBox to allow for easy vertical alignment of its children.
 * 
 * @author Miguel Alexander Nunez Palomares
 * @version 1.0
 * @see javafx.geometry.Pos, javafx.scene.Scene, javafx.scene.control.Button,
 *      javafx.scene.layout.VBox,
 *      javafx.scene.text.Font, javafx.scene.text.Text, javafx.stage.Stage
 */

public class WinScreen extends VBox {
    private static final int CONFETTI_COUNT = 100;
    private final Confetti[] confettiArray = new Confetti[CONFETTI_COUNT];
    

    /**
     * Constructs a WinScreen displaying the winner's name and a button to return to
     * the menu.
     *
     * @param primaryStage The main stage where scenes are set.
     * @param winnerName   The name of the player who won the game.
     * @param mainApp      The main application driver, used to return to the start
     *                     screen.
     */
    public WinScreen(Stage primaryStage, String winnerName, loteriaDriver mainApp) {
        setSpacing(20);
        setAlignment(Pos.CENTER);

        // Display the winning message
        // Text winMessage = new Text(winnerName + " won the game!");
        // winMessage.setStyle("-fx-font-size: 40px; -fx-font-weight: bold;");

        // Button to return to the main menu
        // Button backButton = new Button("Back to Menu");
        // backButton.setStyle("-fx-font-size: 20px;");
        // backButton.setOnAction(e -> {
        // StartScreen startScreen = new StartScreen(mainApp);
        // primaryStage.setScene(new Scene(startScreen, 1300, 850));
        // });

        // Enhanced Winning Message
        Text winMessage = new Text(winnerName + " won the game! 🎉");
        winMessage.setFont(Font.font("Arial Black", 50));
        winMessage.setTextAlignment(TextAlignment.CENTER);
        winMessage.setFill(new LinearGradient(
                0, 0, 1, 0, true, null,
                new Stop(0, Color.GOLD), new Stop(1, Color.ORANGE)));
        winMessage.setEffect(new DropShadow(10, Color.BLACK)); // Shadow for emphasis

        // Enhanced Back to Menu Button
        Button backButton = new Button("Back to Menu");
        backButton.setStyle(
                "-fx-font-size: 22px;" +
                        "-fx-background-color: linear-gradient(to bottom, #ff9d00, #ff6100);" +
                        "-fx-text-fill: white;" +
                        "-fx-padding: 12px 20px;" +
                        "-fx-border-radius: 15px;" +
                        "-fx-background-radius: 15px;" +
                        "-fx-font-weight: bold;");

        // Hover effect for button
        backButton.setOnMouseEntered(e -> backButton.setStyle(
                "-fx-font-size: 22px;" +
                        "-fx-background-color: linear-gradient(to bottom, #ff6100, #ff9d00);" +
                        "-fx-text-fill: white;" +
                        "-fx-padding: 12px 20px;" +
                        "-fx-border-radius: 15px;" +
                        "-fx-background-radius: 15px;" +
                        "-fx-font-weight: bold;"));
        backButton.setOnMouseExited(e -> backButton.setStyle(
                "-fx-font-size: 22px;" +
                        "-fx-background-color: linear-gradient(to bottom, #ff9d00, #ff6100);" +
                        "-fx-text-fill: white;" +
                        "-fx-padding: 12px 20px;" +
                        "-fx-border-radius: 15px;" +
                        "-fx-background-radius: 15px;" +
                        "-fx-font-weight: bold;"));

        backButton.setOnAction(e -> {
            StartScreen startScreen = new StartScreen(mainApp);
            primaryStage.setScene(new Scene(startScreen, 1300, 850));
        });

        // // Add elements to the layout
        // getChildren().addAll(winMessage, backButton);
        // Layout for text and button
        VBox layout = new VBox(20, winMessage, backButton);
        layout.setAlignment(Pos.CENTER);
        layout.setTranslateY(-450); // Move the VBox up by 200 pixels

        // Create a canvas for confetti animation
        Canvas canvas = new Canvas(1300, 850);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        initializeConfetti();

        // Animation loop for confetti
        AnimationTimer confettiAnimation = new AnimationTimer() {
            @Override
            public void handle(long now) {
                updateConfetti(gc, canvas.getWidth(), canvas.getHeight());
            }
        };
        confettiAnimation.start();

        // Add elements to the screen
        getChildren().addAll(canvas, layout);

        // Set background color with gradient
        // setStyle("-fx-background-color: linear-gradient(to bottom, #001f3f,
        // #004080);");

        setStyle("-fx-background-color: linear-gradient(to bottom, #22004d, #5a189a);");

    }

    /**
     * Initializes confetti pieces with random positions, colors, and velocities.
     */
    private void initializeConfetti() {
        Random random = new Random();
        for (int i = 0; i < CONFETTI_COUNT; i++) {
            confettiArray[i] = new Confetti(random.nextDouble() * 1300, random.nextDouble() * 850);
        }
    }

    /** Updates and renders confetti animation. */
    private void updateConfetti(GraphicsContext gc, double width, double height) {
        gc.clearRect(0, 0, width, height);
        for (Confetti confetti : confettiArray) {
            confetti.update();
            confetti.draw(gc);
        }
    }

     

}
