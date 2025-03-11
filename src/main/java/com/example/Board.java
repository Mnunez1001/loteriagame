package com.example;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a 4x4 Lotería board for a player. The board is randomly populated
 * with cards
 * from a shuffled deck at the start of the game.
 * 
 * @author Miguel Alexander Nunez Palomares
 * @version 1.0
 * @see java.util.List, java.util.ArrayList, com.example.Card, com.example.Deck
 */

public class Board {
    private static final int BOARD_SIZE = 4; // Assuming a 4x4 grid
    private Card[][] grid;

    /**
     * Constructs a new Board and initializes it with random Lotería cards.
     */
    public Board() {
        this.grid = new Card[BOARD_SIZE][BOARD_SIZE];
        initializeBoard();
    }

    /**
     * Fills the board with a shuffled selection of cards from the deck.
     */
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

    /**
     * Checks if a given card is present on the board.
     *
     * @param card The card to check for.
     * @return {@code true} if the card is found on the board, otherwise
     *         {@code false}.
     */
    public boolean contains(Card card) {
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                if (grid[row][col] != null && grid[row][col].getImagePath().equals(card.getImagePath())) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Displays the board in a textual format by printing card names.
     * Used for debugging purposes.
     */
    public void displayBoard() {
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                System.out.print((grid[row][col] != null ? grid[row][col].getName() : "Empty") + " | ");
            }
            System.out.println();
        }
    }

    /**
     * Retrieves the card at a specific row and column on the board.
     *
     * @param row The row index.
     * @param col The column index.
     * @return The card at the specified position, or {@code null} if the position
     *         is empty.
     */
    public Card getCard(int row, int col) {
        return grid[row][col];
    }

}
