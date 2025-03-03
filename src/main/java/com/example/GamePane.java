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
    private List<ImageView> beans;

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

        // Create the list of 16 draggable beans
        beans = new ArrayList<>();
        for (int i = 0; i < 16; i++) {
            ImageView bean = new ImageView(new Image(getClass().getClassLoader().getResourceAsStream("com/example/bean.jpg")));
            bean.setFitWidth(50);
            bean.setFitHeight(50);
            makeDraggable(bean);
            beans.add(bean);
        }

        setLeft(boardGrid);
        setRight(drawnCardImageView);
        // setBottom(loteriaButton);

        setupBoard();
        setupLoteriaButton();
        startGameLoop();

    }

    private void setupBoard() {
        boardGrid.setPadding(new Insets(10));
        boardGrid.setHgap(5);
        boardGrid.setVgap(5);
    
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                Card card = playerBoard.getCard(row, col);
                ImageView cardImage = new ImageView(new Image(card.getImagePath()));
                cardImage.setFitWidth(100);
                cardImage.setFitHeight(150);
                boardGrid.add(cardImage, col, row);
            }
        }
    
        // Create a vertical box to organize beans on the right side
        VBox beanTray = new VBox(10);
        beanTray.setPadding(new Insets(10));
        beanTray.setAlignment(Pos.CENTER);
    
        for (ImageView bean : beans) {
            beanTray.getChildren().add(bean);
        }
    
        // Use HBox to put the board on the left and the bean tray on the right
        HBox layout = new HBox(20, boardGrid, beanTray);
        layout.setAlignment(Pos.CENTER);
    
        setCenter(layout); // Set the board and bean tray in the center
    }

    private void makeDraggable(ImageView bean) {
        final double[] offsetX = new double[1];
        final double[] offsetY = new double[1];
    
        bean.setOnMousePressed(event -> {
            offsetX[0] = event.getSceneX() - bean.getLayoutX();
            offsetY[0] = event.getSceneY() - bean.getLayoutY();
        });
    
        bean.setOnMouseDragged(event -> {
            bean.setLayoutX(event.getSceneX() - offsetX[0]);
            bean.setLayoutY(event.getSceneY() - offsetY[0]);
        });
    
        bean.setOnMouseReleased(event -> {
            int row = getGridRow(event.getSceneY());
            int col = getGridCol(event.getSceneX());
    
            if (row != -1 && col != -1) {
                Card selectedCard = playerBoard.getCard(row, col);
                
                if (drawnCards.contains(selectedCard)) {
                    // Lock the bean in place over the correct card
                    bean.setLayoutX(boardGrid.getLayoutX() + col * 120 + 30);
                    bean.setLayoutY(boardGrid.getLayoutY() + row * 185 + 30);
                } else {
                    // Reset bean position if incorrect
                    bean.setLayoutX(10 + 60 * beans.indexOf(bean)); // Back to tray
                    bean.setLayoutY(500);
                }
            }
        });
    }

    private int getGridRow(double sceneY) {
        for (int row = 0; row < 4; row++) {
            if (sceneY >= boardGrid.getLayoutY() + row * 185 &&
                sceneY < boardGrid.getLayoutY() + (row + 1) * 185) {
                return row;
            }
        }
        return -1;
    }
    
    private int getGridCol(double sceneX) {
        for (int col = 0; col < 4; col++) {
            if (sceneX >= boardGrid.getLayoutX() + col * 120 &&
                sceneX < boardGrid.getLayoutX() + (col + 1) * 120) {
                return col;
            }
        }
        return -1;
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