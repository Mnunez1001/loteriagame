package com.example;

import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class GamePane extends VBox {
    private Board playerBoard;
    private Deck deck;
    private List<Card> drawnCards;
    private GridPane boardGrid;
    private Button loteriaButton;
    private Text drawnCardText;
    private Timer timer;
    
    public GamePane() {
        playerBoard = new Board();
        deck = new Deck();
        drawnCards = new ArrayList<>();
        boardGrid = new GridPane();
        loteriaButton = new Button("Lotería");
        drawnCardText = new Text("Next Card:");
        
        setupBoard();
        setupLoteriaButton();
        startGameLoop();
    }
    
    private void setupBoard() {
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                Button cardButton = new Button(playerBoard.getCard(row, col).getName());
                int finalRow = row;
                int finalCol = col;
                cardButton.setOnAction(e -> markCard(finalRow, finalCol, cardButton));
                boardGrid.add(cardButton, col, row);
            }
        }
        getChildren().add(boardGrid);
    }
    
    private void markCard(int row, int col, Button cardButton) {
        Card selectedCard = playerBoard.getCard(row, col);
        if (drawnCards.contains(selectedCard)) {
            cardButton.setStyle("-fx-background-color: green;");
        }
    }
    
    private void setupLoteriaButton() {
        loteriaButton.setOnAction(e -> checkWin());
        getChildren().add(loteriaButton);
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
            drawnCardText.setText("Next Card: " + drawnCard.getName());
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
