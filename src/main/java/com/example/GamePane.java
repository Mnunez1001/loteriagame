package com.example;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class GamePane extends BorderPane {
    private Board playerBoard;
    private Deck deck;
    private List<Card> drawnCards;
    private GridPane boardGrid;
    private Button loteriaButton;
    private Text drawnCardText;
    private Timer timer;
    private ImageView drawnCardImageView;

    public GamePane() {
        playerBoard = new Board();
        deck = new Deck();
        drawnCards = new ArrayList<>();
        boardGrid = new GridPane();
        loteriaButton = new Button("Lotería");
        drawnCardText = new Text("Next Card:");

        // Drawn card image
        drawnCardImageView = new ImageView();
        drawnCardImageView.setFitWidth(200);
        drawnCardImageView.setFitHeight(350);

        setLeft(boardGrid);
        setRight(drawnCardImageView);
        // setBottom(loteriaButton);

        setupBoard();
        setupLoteriaButton();
        startGameLoop();

    }

    private void setupBoard() {
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                Card card = playerBoard.getCard(row, col);
                ImageView imageView = new ImageView(card.getImage());
                imageView.setFitWidth(120);
                imageView.setFitHeight(185);

                Button cardButton = new Button();
                cardButton.setGraphic(imageView);

                StackPane stack = new StackPane();
                stack.getChildren().add(cardButton); // Add button to stack

                // Store the StackPane inside the GridPane
                boardGrid.add(stack, col, row);

                int finalRow = row;
                int finalCol = col;
                cardButton.setOnAction(e -> markCard(finalRow, finalCol, stack));

                // boardGrid.add(cardButton, col, row);
            }
        }
    }

    private void markCard(int row, int col, StackPane stack) {
        Card selectedCard = playerBoard.getCard(row, col);

        // Check if the card has been drawn before marking
        if (drawnCards.contains(selectedCard)) {
            // Load the bean image
            ImageView beanImage = new ImageView(new Image("/com/example/bean.jpg")); // Adjust path as needed
                                                                                                       
            beanImage.setFitWidth(50);
            beanImage.setFitHeight(50);

            // Add the bean image on top of the button
            stack.getChildren().add(beanImage);

            // // Get the current image of the card
            // ImageView cardImageView = (ImageView) cardButton.getGraphic();

            // // Create a StackPane to overlay the bean on the card
            // StackPane stack = new StackPane();
            // stack.getChildren().addAll(cardImageView, beanImage);

            // // Set the new stack as the button's graphic
            // cardButton.setGraphic(stack);
        }
    }

    private void setupLoteriaButton() {
        loteriaButton.setOnAction(e -> checkWin());

        // Make the button bigger
        loteriaButton.setStyle("-fx-font-size: 40px; -fx-padding: 10px 10px;");

        // loteriaButton.setRotate(90); // Rotate the button text

        // Wrap the button in a VBox and align it to the left
        VBox bottomBox = new VBox(loteriaButton);
        bottomBox.setAlignment(Pos.CENTER_RIGHT); // Align to bottom left
        bottomBox.setPadding(new Insets(0, 5, 0, 0)); // Adjust top padding to move it up

        setBottom(bottomBox);
    }

    private void startGameLoop() {
        timer = new Timer(true);
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> drawCard());
            }
        }, 0, 5000);
    }

    private void drawCard() {
        Card drawnCard = deck.drawCard();
        if (drawnCard != null) {
            drawnCards.add(drawnCard);
            drawnCardImageView.setImage(drawnCard.getImage());
        }
    }

    private void checkWin() {
        if (WinningCondition.isWinningBoard(playerBoard, drawnCards)) {
            System.out.println("You won!");
        } else {
            System.out.println("Not yet!");
        }
    }
}