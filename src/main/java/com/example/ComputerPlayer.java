package com.example;

import java.util.ArrayList;
import java.util.List;

public class ComputerPlayer {
    private String name;
    private Board board;
    private List<Card> markedCards;
    
    public ComputerPlayer(String name) {
        this.name = name;
        this.board = new Board(); // Each computer gets its own board
        this.markedCards = new ArrayList<>();
    }
    
    public String getName() {
        return name;
    }
    
    public Board getBoard() {
        return board;
    }
    
    public void markCard(Card drawnCard) {
        if (board.contains(drawnCard)) {
            markedCards.add(drawnCard);
        }
    }
    
    public boolean checkWin(WinningCondition condition) {
        return condition.isWinningBoard(board, markedCards);
    }
}

