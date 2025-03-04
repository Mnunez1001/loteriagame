package com.example;

import javafx.application.Platform;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

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
    private ImageView winningConditionImage;
    private Stage primaryStage; // Reference to the main application stage

    public GamePane(Stage primaryStage) {
        this.primaryStage = primaryStage;
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

        // Winning condition image
        winningConditionImage = new ImageView(new Image(getClass()
                .getResourceAsStream("/com/example/" + WinningCondition.getWinningConditionImage() + ".png")));
        winningConditionImage.setFitWidth(200);
        winningConditionImage.setFitHeight(350);

        // Create the list of 16 draggable beans
        beans = new ArrayList<>(); /// com/example/mage00.jpg com/example/beanEmoji.png
        for (int i = 0; i < 16; i++) {
            ImageView bean = new ImageView(new Image(getClass().getResourceAsStream("/com/example/bean.png"))); // Adjust
                                                                                                                // path
                                                                                                                // as
                                                                                                                // needed
            bean.setFitWidth(30);
            bean.setFitHeight(30);
            makeDraggable(bean);
            beans.add(bean);
        }

        setLeft(boardGrid);
        setRight(drawnCardImageView);

        setupBoard();
        setupLoteriaButton();
        startGameLoop();

        // Display the winning condition image
        VBox rightBox = new VBox(10, drawnCardImageView, winningConditionImage);
        rightBox.setAlignment(Pos.TOP_RIGHT);
        setRight(rightBox);

    }

    private void setupBoard() {
        boardGrid.setPadding(new Insets(10));
        boardGrid.setHgap(5);
        boardGrid.setVgap(5);

        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                Card card = playerBoard.getCard(row, col);
                String imagePath = card.getImagePath();

                System.out.println("Attempting to load:" + imagePath);
                System.out.println("Resolved path: " + getClass().getResource(imagePath));

                Image image = new Image(getClass().getResourceAsStream(imagePath));
                ImageView cardImage = new ImageView(image);

                cardImage.setFitWidth(120);
                cardImage.setFitHeight(170);
                boardGrid.add(cardImage, col, row);
            }
        }

        // Use an AnchorPane for absolute positioning

        AnchorPane gamePane = new AnchorPane();
        gamePane.getChildren().add(boardGrid);
        double startX = 550; // Adjust as needed
        double startY = 550; // Adjust as needed

        // Add beans directly to the AnchorPane (not VBox)
        for (ImageView bean : beans) {
            gamePane.getChildren().add(bean);
            bean.setLayoutX(startX);
            bean.setLayoutY(startY);
            startY -= 35;
        }

        setLeft(gamePane); // Use gamePane instead of HBox

    }

    private void makeDraggable(ImageView bean) {
        final double[] offsetX = new double[1];
        final double[] offsetY = new double[1];

        bean.setOnMousePressed(event -> {
            offsetX[0] = event.getSceneX() - bean.getTranslateX();
            offsetY[0] = event.getSceneY() - bean.getTranslateY();
        });

        bean.setOnMouseDragged(event -> {
            bean.setTranslateX(event.getSceneX() - offsetX[0]);
            bean.setTranslateY(event.getSceneY() - offsetY[0]);
        });

    }

    private void setupLoteriaButton() {
        loteriaButton.setOnAction(e -> checkWin());

        // Make the button bigger
        loteriaButton.setStyle("-fx-font-size: 40px; -fx-padding: 10px 10px;");

        // loteriaButton.setRotate(90); // Rotate the button text

        // Wrap the button in a VBox and align it to the left
        VBox bottomBox = new VBox(loteriaButton);
        bottomBox.setAlignment(Pos.CENTER_RIGHT); // Align to bottom Right
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
        }, 0, 500);
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
            showWinScreen();
        } else {
            System.out.println("Not yet!");
        }
    }

    private void showWinScreen() {
        WinScreen winScreen = new WinScreen(primaryStage);
        primaryStage.setScene(new Scene(winScreen, 1300, 850));
    }







}