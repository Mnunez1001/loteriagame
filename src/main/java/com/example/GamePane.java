package com.example;

import javafx.application.Platform;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Represents the main game interface for the Lotería game.
 * It handles the game board, deck, drawn cards, computer players, and game
 * logic.
 * It extends BorderPane to allow for easy placement of UI elements.
 * 
 * @author Miguel Alexander Nunez Palomares
 * @version 1.0
 * @see javafx.application.Platform, javafx.geometry.Bounds,
 *      javafx.geometry.Insets, javafx.geometry.Pos,
 *      javafx.scene.Scene, javafx.scene.control.Button,
 *      javafx.scene.image.Image, javafx.scene.image.ImageView,
 *      javafx.scene.layout.BorderPane, javafx.scene.layout.GridPane,
 *      javafx.scene.layout.VBox, javafx.scene.text.Text, javafx.stage.Stage
 */

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
    private loteriaDriver mainApp; // Reference to the main application

    private ComputerPlayerManager computerManager; // Manages AI players
    private VBox computerPlayerBox; // Sidebar for displaying AI players

    /**
     * Constructs the GamePane, initializing the game components and UI elements.
     *
     * @param primaryStage       The main application stage.
     * @param numComputerPlayers The number of computer opponents.
     * @param mainApp            The main application driver.
     */
    public GamePane(Stage primaryStage, int numComputerPlayers, loteriaDriver mainApp) {
        this.mainApp = mainApp;
        this.primaryStage = primaryStage;
        playerBoard = new Board();
        deck = new Deck();
        drawnCards = new ArrayList<>();
        boardGrid = new GridPane();
        loteriaButton = new Button("Lotería");
        drawnCardText = new Text("Next Card:");

        // Background Image for the pane
        Image backgroundImage = new Image(getClass().getResourceAsStream("/com/example/redyellow.jpg"));
        BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(BackgroundSize.AUTO,
                        BackgroundSize.AUTO, false, false, true, true));
        setBackground(new Background(background));

        // Initialize AI players
        computerManager = new ComputerPlayerManager(numComputerPlayers, this);

        // Drawn card image
        drawnCardImageView = new ImageView();
        drawnCardImageView.setFitWidth(200);
        drawnCardImageView.setFitHeight(300);

        StackPane drawnCardPane = new StackPane(drawnCardImageView);

        // // Set border explicitly
        drawnCardPane.setBorder(new Border(new BorderStroke(
                Color.BLACK, // Border color
                BorderStrokeStyle.SOLID, // Border style
                new CornerRadii(5), // Corner radius
                new BorderWidths(3) // Border width
        )));

        // drawnCardPane.setStyle("-fx-min-width: 200px;");

    

        // Winning condition image
        winningConditionImage = new ImageView(new Image(getClass()
                .getResourceAsStream("/com/example/" + WinningCondition.getWinningConditionImage() + ".png")));
        winningConditionImage.setFitWidth(200);
        winningConditionImage.setFitHeight(300);
       
        StackPane winningConditionPane = new StackPane(winningConditionImage);
        winningConditionPane.setBorder(new Border(new BorderStroke(
                Color.BLACK, // Border color
                BorderStrokeStyle.SOLID, // Border style
                new CornerRadii(5), // Corner radius
                new BorderWidths(3) // Border width
        )));
        // winningConditionPane.setStyle("-fx-min-width: 200px;");

        // Create the list of 16 draggable beans
        beans = new ArrayList<>(); /// com/example/mage00.jpg com/example/beanEmoji.png
        for (int i = 0; i < 16; i++) {
            ImageView bean = new ImageView(new Image(getClass().getResourceAsStream("/com/example/bean.png")));
            bean.setFitWidth(30);
            bean.setFitHeight(30);
            makeDraggable(bean);
            
            beans.add(bean);
        }

        setLeft(boardGrid);
        // setRight(drawnCardImageView);

        setupBoard();
        setupLoteriaButton();
        setupComputerPlayersUI();
        startGameLoop();

        // Display the winning condition image
        VBox rightBox = new VBox(10, drawnCardPane, winningConditionPane, computerPlayerBox);
        rightBox.setAlignment(Pos.TOP_RIGHT);
        setRight(rightBox);

    }

    /**
     * Sets up the game board by loading card images and arranging them in a grid.
     * Also initializes the draggable bean markers.
     */
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
                // Add border and shadow effect
                // Create a StackPane to wrap the ImageView
                StackPane cardPane = new StackPane(cardImage);

                // Set border explicitly
                cardPane.setBorder(new Border(new BorderStroke(
                        Color.BLACK, // Border color
                        BorderStrokeStyle.SOLID, // Border style
                        new CornerRadii(5), // Corner radius
                        new BorderWidths(3) // Border width
                )));

                // Apply drop shadow effect
                DropShadow shadow = new DropShadow();
                shadow.setRadius(10);
                shadow.setOffsetX(2);
                shadow.setOffsetY(2);
                shadow.setColor(Color.rgb(0, 0, 0, 0.5)); // Semi-transparent black shadow
                cardPane.setEffect(shadow);

                boardGrid.add(cardPane, col, row);
            }
        }

        // AnchorPane for absolute positioning

        AnchorPane gamePane = new AnchorPane();
        gamePane.getChildren().add(boardGrid);
        double startX = 550; // the x position of the beans
        double startY = 550; // the y position of the beans

        // Add beans directly to the AnchorPane (not VBox)
        for (ImageView bean : beans) {
            gamePane.getChildren().add(bean);
            bean.setLayoutX(startX);
            bean.setLayoutY(startY);
            startY -= 35;
        }

        setLeft(gamePane); // Use gamePane instead of HBox

    }

    /**
     * Makes the given bean image draggable so the player can place markers on the
     * board.
     *
     * @param bean The bean image to be made draggable.
     */
    private void makeDraggable(ImageView bean) {
        final double[] offsetX = new double[1]; // used to store the initial offset (difference)
        final double[] offsetY = new double[1]; // between the bean's position and the mouse click.

        bean.setOnMousePressed(event -> {
            offsetX[0] = event.getSceneX() - bean.getTranslateX(); // It calculates and stores the offset
            offsetY[0] = event.getSceneY() - bean.getTranslateY(); // between where the bean is located and where the
                                                                   // user clicks.
        });

        bean.setOnMouseDragged(event -> {
            bean.setTranslateX(event.getSceneX() - offsetX[0]);// It updates the bean’s position (TranslateX and
                                                               // TranslateY)
            bean.setTranslateY(event.getSceneY() - offsetY[0]);// based on the mouse's new position.
        });

    }

    /**
     * Sets up the "Lotería" button, which allows the player to claim a win.
     */
    private void setupLoteriaButton() {
        loteriaButton.setOnAction(e -> checkWin());

        // Make the button bigger
        // loteriaButton.setStyle("-fx-font-size: 40px; -fx-padding: 10px 10px;");
        // Apply gradient background, bold font, and slight shadow
        loteriaButton.setStyle("-fx-font-size: 50px; " +
                "-fx-padding: 12px 20px; " +
                "-fx-background-color: linear-gradient(to bottom, #ffcc00, #ff6600); " + // Yellow to Orange gradient
                "-fx-text-fill: white; " + // White text color
                "-fx-font-weight: bold; " +
                "-fx-border-radius: 15px;" +
                "-fx-background-radius: 15px;" ); // Bold font
                // "-fx-border-color: black; " + // Black border
                // "-fx-border-width: 3px; " + // Border width
                // "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.5), 10, 0, 2, 2);"); // Subtle shadow



        // Hover effect for button
        loteriaButton.setOnMouseEntered(e -> loteriaButton.setStyle(
                "-fx-font-size: 50px;" +
                        "-fx-background-color: linear-gradient(to bottom, #ff6600, #ffcc00);" +
                        "-fx-text-fill: white;" +
                        "-fx-padding: 12px 20px;" +
                        "-fx-border-radius: 15px;" +
                        "-fx-background-radius: 15px;" +
                        "-fx-font-weight: bold;"));
        loteriaButton.setOnMouseExited(e -> loteriaButton.setStyle(
                "-fx-font-size: 50px;" +
                        "-fx-background-color: linear-gradient(to bottom, #ffcc00, #ff6600);" +
                        "-fx-text-fill: white;" +
                        "-fx-padding: 12px 20px;" +
                        "-fx-border-radius: 15px;" +
                        "-fx-background-radius: 15px;" +
                        "-fx-font-weight: bold;"));


        // loteriaButton.setRotate(90); // Rotate the button text
        loteriaButton.setPrefSize(500, 50); // Set preferred size

        // Wrap the button in a VBox and align it to the left
        VBox bottomBox = new VBox(loteriaButton);
        bottomBox.setAlignment(Pos.CENTER_RIGHT); // Align to bottom Right
        bottomBox.setPadding(new Insets(0, 5, 0, 0)); // Adjust top padding to move it up

        setBottom(bottomBox);
    }

    /**
     * Sets up the user interface for displaying computer player statuses.
     */
    private void setupComputerPlayersUI() {
        computerPlayerBox = new VBox(10);
        computerPlayerBox.setPadding(new Insets(10));
        computerPlayerBox.setAlignment(Pos.CENTER);
        computerPlayerBox.setStyle("-fx-border-color: black; -fx-border-width: 2px; -fx-padding: 10px;");
        //-fx-min-width: 400px;
        

        Text label = new Text("Computer Players");
        computerPlayerBox.getChildren().add(label);

        for (ComputerPlayer player : computerManager.getComputerPlayers()) {
            Text playerText = new Text(player.getName());
            computerPlayerBox.getChildren().add(playerText);
        }

        // setRight(computerPlayerBox);
    }

    /**
     * Starts the game loop, periodically drawing a new card and updating the game
     * state.
     * 
     * timer = new Timer(true);
     * Creates a new Timer object, which is used to schedule tasks that run at
     * specific time intervals.
     * The parameter true makes the timer a daemon thread, meaning it will stop
     * automatically when the program exits.
     * 
     * timer.scheduleAtFixedRate(new TimerTask() { ... }, 0, 500);
     * This schedules a repeating task using scheduleAtFixedRate,
     * meaning it will execute the task at fixed time intervals.
     * 
     * delay: 0 milliseconds (start immediately)
     * period: 500 milliseconds (0.5 seconds)
     */
    private void startGameLoop() {
        timer = new Timer(true);
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> drawCard());
            }
        }, 0, 500);
    }

    /**
     * Draws a card from the deck and updates the game state accordingly.
     * Also checks if any computer players have won.
     */
    private void drawCard() {
        Card drawnCard = deck.drawCard();
        if (drawnCard != null) {
            drawnCards.add(drawnCard);
            drawnCardImageView.setImage(drawnCard.getImage());

            // Update AI players
            computerManager.updateComputers(drawnCard);

            // Check if any AI won
            for (ComputerPlayer player : computerManager.getComputerPlayers()) {
                if (player.checkWin(WinningCondition.getCurrentWinningCondition())) {
                    showWinScreen(player.getName());
                    return;
                }
            }
        }
    }

    /**
     * Checks if the player has won based on the drawn cards and the current winning
     * condition.
     */
    private void checkWin() {
        if (WinningCondition.isWinningBoard(playerBoard, drawnCards)) {
            showWinScreen("You");
        } else {
            System.out.println("Not yet!");
        }
    }

    /**
     * Displays the win screen when a player or AI wins the game.
     *
     * @param winnerName The name of the winning player.
     */
    public void showWinScreen(String winnerName) {
        WinScreen winScreen = new WinScreen(primaryStage, winnerName, mainApp);
        primaryStage.setScene(new Scene(winScreen, 1300, 850));
    }

    /**
     * Updates the UI to reflect the current status of computer players.
     */
    public void updateComputerPlayerUI() {
        computerPlayerBox.getChildren().clear();

        // Add title again
        Text label = new Text("Computer Players");
        computerPlayerBox.getChildren().add(label);

        // Add each computer player with their marked status
        for (ComputerPlayer player : computerManager.getComputerPlayers()) {
            Text playerText = new Text(player.getName() + "-Marked Cards: " + player.getMarkedCardCount());
            computerPlayerBox.getChildren().add(playerText);
        }
    }

    /**
     * Stops the game loop to prevent further card draws.
     */
    public void stopGameLoop() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

}