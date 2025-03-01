package com.example;

import java.util.ArrayList;
import java.util.List;

class ComputerPlayerManager {
    private List<ComputerPlayer> computerPlayers;

    public ComputerPlayerManager(int numberOfPlayers) {
        computerPlayers = new ArrayList<>();
        for (int i = 1; i <= numberOfPlayers; i++) {
            computerPlayers.add(new ComputerPlayer("Computer " + i));
        }
    }

    public List<ComputerPlayer> getComputerPlayers() {
        return computerPlayers;
    }

    public void updateComputers(Card drawnCard, WinningCondition condition) {
        for (ComputerPlayer player : computerPlayers) {
            player.markCard(drawnCard);
            if (player.checkWin(condition)) {
                System.out.println(player.getName() + " has won!");
            }
        }
    }
}

