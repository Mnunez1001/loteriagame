package com.example;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private static final int BOARD_SIZE = 4; // Assuming a 4x4 grid
    private Card[][] grid;

    public Board() {
        this.grid = new Card[BOARD_SIZE][BOARD_SIZE];
        initializeBoard();
    }
    
    private void initializeBoard() {
        List<Card> deckCopy = new ArrayList<>(Deck.getShuffledDeck()); // Get shuffled cards
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                if (!deckCopy.isEmpty()) {
                    grid[row][col] = deckCopy.remove(0);
                }
            }
        }
    }
    
    public boolean contains(Card card) {
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                if (grid[row][col] != null && grid[row][col].getName().equals(card.getName())) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public void displayBoard() {
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                System.out.print((grid[row][col] != null ? grid[row][col].getName() : "Empty") + " | ");
            }
            System.out.println();
        }
    }



    public Card getCard(int row, int col) {
        return grid[row][col];
    }

    //create a method contains to check if the card is in the board

    



}

