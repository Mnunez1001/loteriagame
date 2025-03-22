package com.example;

import java.util.ArrayList;
import java.util.List;

import javafx.application.Platform;

/**
 * Manages multiple computer-controlled players in the Lotería game.
 * Handles updating players with drawn cards, checking for wins,
 * and interacting with the game UI.
 * 
 * @author Miguel Alexander Nunez Palomares
 * @version 1.0
 * @see java.util.List, javafx.application.Platform, java.utils.ArrayList,
 *      com.example.ComputerPlayer,
 *      com.example.GamePane
 */
class ComputerPlayerManager {
    /**
     * The list of computer players being managed.
     */
    private List<ComputerPlayer> computerPlayers;
    /**
     * The game UI pane to interact with for game updates.
     */
    private GamePane gamePane;

    /**
     * Constructs a ComputerPlayerManager with the specified number of computer
     * players.
     * Each computer player is initialized with a unique name.
     *
     * @param numberOfPlayers The number of computer-controlled players.
     * @param gamePane        The game UI pane to interact with for game updates.
     */
    public ComputerPlayerManager(int numberOfPlayers, GamePane gamePane) {
        this.gamePane = gamePane;
        computerPlayers = new ArrayList<>();
        for (int i = 1; i <= numberOfPlayers; i++) {
            computerPlayers.add(new ComputerPlayer("Computer " + i));
        }
    }

    /**
     * Retrieves the list of computer players being managed.
     *
     * @return A list of {@code ComputerPlayer} objects.
     */
    public List<ComputerPlayer> getComputerPlayers() {
        return computerPlayers;
    }

    /**
     * Updates all computer players with the drawn card.
     * Each player marks the card if it is on their board, and the method checks if
     * any player has won.
     * If a player wins, the game loop is stopped, and the win screen is displayed.
     *
     * @param drawnCard The card that was drawn in the game.
     */
    public void updateComputers(Card drawnCard) {
        int currentCondition = WinningCondition.getCurrentWinningCondition(); // Get the winning condition as an int

        for (ComputerPlayer player : computerPlayers) {
            player.markCard(drawnCard);
            if (player.checkWin(currentCondition)) { // Pass the int instead of WinningCondition
                System.out.println(player.getName() + " has won!");

                // Stop the game loop and show win screen
                Platform.runLater(() -> {
                    gamePane.stopGameLoop(); // Ensure game loop is stopped
                    gamePane.stopMusic(); // Stop the background music
                    gamePane.showWinScreen(player.getName()); // Show win screen
                });
                return; // Exit loop after first win
            }
        }

        // Update the UI to reflect changes in the computer players' boards
        Platform.runLater(() -> gamePane.updateComputerPlayerUI());
    }

}
