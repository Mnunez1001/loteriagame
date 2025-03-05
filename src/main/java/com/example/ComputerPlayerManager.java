package com.example;

import java.util.ArrayList;
import java.util.List;

import javafx.application.Platform;

class ComputerPlayerManager {
    private List<ComputerPlayer> computerPlayers;
    private GamePane gamePane;

    public ComputerPlayerManager(int numberOfPlayers, GamePane gamePane) {
        this.gamePane = gamePane;
        computerPlayers = new ArrayList<>();
        for (int i = 1; i <= numberOfPlayers; i++) {
            computerPlayers.add(new ComputerPlayer("Computer " + i));
        }
    }

    public List<ComputerPlayer> getComputerPlayers() {
        return computerPlayers;
    }

    public void updateComputers(Card drawnCard) {
        int currentCondition = WinningCondition.getCurrentWinningCondition(); // Get the winning condition as an int
    
        for (ComputerPlayer player : computerPlayers) {
            player.markCard(drawnCard);
            if (player.checkWin(currentCondition)) { // Pass the int instead of WinningCondition
                System.out.println(player.getName() + " has won!");
            }
        }

         Platform.runLater(() -> gamePane.updateComputerPlayerUI()); // Force UI update
    }


}

