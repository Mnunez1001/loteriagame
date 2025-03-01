package com.example;

//import java.time.Duration;
import javafx.util.Duration;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

class GamePane extends BorderPane {
    //Represents the player’s board where their Lotería card will be displayed.
    private GridPane playerBoard;
    //Label that displays the card that was drawn from the deck.
    private Label drawnCardLabel;
    //Button that the player can press when they think they have Lotería.
    private Button loteriaButton;
    //The deck of cards that will be used in the game.
    private Deck deck;
    //Timer that will automatically draw a card every 5 seconds.
    private Timeline cardDrawTimer;

    public GamePane() {
        deck = new Deck();

        // Player board setup
        playerBoard = new GridPane();
        playerBoard.setHgap(5);
        playerBoard.setVgap(5); //The grid has horizontal and vertical gaps of 5 pixels.
        playerBoard.setAlignment(Pos.CENTER); //It is centered within the main pane.
        this.setCenter(playerBoard);
        
        // Drawn card display
        drawnCardLabel = new Label("Next card will appear here");
        drawnCardLabel.setFont(new Font("Arial", 20));
        drawnCardLabel.setTextFill(Color.DARKBLUE);
        StackPane cardDisplay = new StackPane(drawnCardLabel);
        cardDisplay.setMinHeight(100);
        this.setTop(cardDisplay);
        
        // Lotería button
        loteriaButton = new Button("Lotería!");
        loteriaButton.setFont(new Font("Arial", 16));
        loteriaButton.setOnAction(e -> checkForWin());
        StackPane buttonPane = new StackPane(loteriaButton);
        buttonPane.setAlignment(Pos.CENTER);
        this.setBottom(buttonPane);
        
        // Start automatic card drawing every 5 seconds
        cardDrawTimer = new Timeline(new KeyFrame(Duration.seconds(5), e -> drawNextCard())); //The KeyFrame is set to trigger every 5 seconds.
        cardDrawTimer.setCycleCount(Timeline.INDEFINITE); //ensures that it keeps repeating indefinitely.
        cardDrawTimer.play(); //starts the timer when the game begins.
    }

    private void drawNextCard() {
        Card nextCard = deck.drawCard();
        if (nextCard != null) {
            drawnCardLabel.setText("Drawn: " + nextCard.getName());
        } else {
            drawnCardLabel.setText("No more cards left.");
            cardDrawTimer.stop();
        }
    }

    private void checkForWin() {
        // Placeholder: Implement win condition checking later
        System.out.println("Player pressed Lotería! (Winning logic not yet implemented)");
    }
}
