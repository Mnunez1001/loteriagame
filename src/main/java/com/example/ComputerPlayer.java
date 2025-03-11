package com.example;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a computer-controlled player in the Lotería game.
 * Each computer player has a unique name, its own game board,
 * and keeps track of marked cards.
 * 
 * @author Miguel Alexander Nunez Palomares
 * @version 1.0
 * @see java.util.List, java.util.ArrayList, com.example.Board, com.example.Card
 */
public class ComputerPlayer {

    /**
     * The name of the computer player.
     */
    private String name;

    /**
     * The board assigned to the computer player.
     */
    private Board board;

    /**
     * The list of cards marked by the computer player.
     */
    private List<Card> markedCards;

    /**
     * Constructs a ComputerPlayer with the given name.
     * Initializes a new game board and an empty list of marked cards.
     *
     * @param name The name of the computer player.
     */
    public ComputerPlayer(String name) {
        this.name = name;
        this.board = new Board(); // Each computer gets its own board
        this.markedCards = new ArrayList<>();
    }

    /**
     * Retrieves the name of the computer player.
     *
     * @return The name of the player.
     */
    public String getName() {
        return name;
    }

    /**
     * Retrieves the board assigned to the computer player.
     *
     * @return The player's board.
     */
    public Board getBoard() {
        return board;
    }

    /**
     * Marks a card on the player's board if it is present.
     * The card is added to the markedCards list.
     *
     * @param drawnCard The card that was drawn and needs to be marked.
     */
    public void markCard(Card drawnCard) {
        if (board.contains(drawnCard)) {
            markedCards.add(drawnCard);
        }
    }

    /**
     * Checks if the computer player has won based on the current winning condition.
     *
     * @param conditionIndex The index of the current winning condition.
     * @return True if the player meets the winning condition, false otherwise.
     */
    public boolean checkWin(int conditionIndex) {
        return WinningCondition.isWinningBoard(board, markedCards); // No need to pass conditionIndex here
    }

    /**
     * Retrieves the number of cards that have been marked on the player's board.
     *
     * @return The count of marked cards.
     */
    public int getMarkedCardCount() {
        return markedCards.size();
    }

}
