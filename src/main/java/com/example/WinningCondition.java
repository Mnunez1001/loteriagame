package com.example;

import java.util.List;
import java.util.Random;

/**
 * Manages the winning conditions for the Lotería game.
 * It randomly selects a winning condition and checks if a board meets that
 * condition.
 * The winning conditions are: full board, horizontal line, vertical line,
 * center 4, diagonal, 4 corners.
 * 
 * @author Miguel Alexander Nunez Palomares
 * @version 1.0
 * @see java.util.List, java.util.Random, com.example.Board, com.example.Card
 */

public class WinningCondition {

    /**
     * The names of the winning conditions.
     */
    private static final String[] conditionNames = {
            "full", "horizontalLine", "verticalLine", "center4",
            "4Corners", "diagonal", "4InAndOut"
    };

    /**
     * The index of the winning condition currently in use.
     */
    private static final int FULL_BOARD = 0;
    private static final int ROW_WIN = 1;
    private static final int COLUMN_WIN = 2;
    private static final int CENTER_FOUR_WIN = 3;
    private static final int FOUR_CORNERS_WIN = 4;
    private static final int DIAGONAL_WIN = 5;
    private static final int FOUR_IN_AND_OUT_WIN = 6;

    /**
     * The random index of the currently selected winning condition.
     */
    private static int selectedConditionIndex = new Random().nextInt(conditionNames.length);

    /**
     * Checks if the board meets the current winning condition based on the marked
     * cards.
     *
     * @param board       The player's board.
     * @param markedCards The list of cards that have been marked.
     * @return {@code true} if the board satisfies the winning condition, otherwise
     *         {@code false}.
     */
    public static boolean isWinningBoard(Board board, List<Card> markedCards) {
        switch (selectedConditionIndex) {
            case FULL_BOARD:
                return checkFullBoard(markedCards);
            case ROW_WIN:
                return checkRowWin(board, markedCards);
            case COLUMN_WIN:
                return checkColumnWin(board, markedCards);
            case CENTER_FOUR_WIN:
                return checkCenterFourWin(board, markedCards);
            case FOUR_CORNERS_WIN:
                return checkFourCornersWin(board, markedCards);
            case DIAGONAL_WIN:
                return checkDiagonalWin(board, markedCards);
            case FOUR_IN_AND_OUT_WIN:
                return checkFourInAndOutWin(board, markedCards);
            default:
                return false;
        }
    }

    /**
     * Retrieves the name of the current winning condition.
     *
     * @return A string representing the selected winning condition.
     */
    public static String getWinningConditionImage() {
        return conditionNames[selectedConditionIndex];
    }

    /**
     * Randomly generates a new winning condition, ensuring it differs from the
     * previous one.
     */
    public static void generateNewCondition() {
        Random random = new Random();
        int newCondition;

        do {
            newCondition = random.nextInt(conditionNames.length);
        } while (newCondition == selectedConditionIndex); // Ensure it's different from the previous one

        selectedConditionIndex = newCondition;
    }

    /**
     * Retrieves the index of the currently selected winning condition.
     *
     * @return The index representing the current winning condition.
     */
    public static int getCurrentWinningCondition() {
        return selectedConditionIndex;
    }

    /**
     * Checks if a given card is in the list of marked cards.
     *
     * @param markedCards The list of marked cards.
     * @param card        The card to check for.
     * @return {@code true} if the card is in the list, otherwise {@code false}.
     */
    private static boolean containsCard(List<Card> markedCards, Card card) {
        for (Card marked : markedCards) {
            if (marked.getName().equals(card.getName())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if all 16 positions on the board have been marked.
     *
     * @param markedCards The list of marked cards.
     * @return {@code true} if the entire board is marked, otherwise {@code false}.
     */
    private static boolean checkFullBoard(List<Card> markedCards) {
        return markedCards.size() == 16; // Assuming a 4x4 board
    }

    /**
     * Checks if any row on the board has been fully marked.
     *
     * @param board       The player's board.
     * @param markedCards The list of marked cards.
     * @return {@code true} if a full row is marked, otherwise {@code false}.
     */
    private static boolean checkRowWin(Board board, List<Card> markedCards) {
        for (int row = 0; row < 4; row++) {
            boolean rowWin = true;
            for (int col = 0; col < 4; col++) {
                if (!containsCard(markedCards, board.getCard(row, col))) {
                    rowWin = false;
                    break;
                }
            }
            if (rowWin)
                return true;
        }
        return false;
    }

    /**
     * Checks if any column on the board has been fully marked.
     *
     * @param board       The player's board.
     * @param markedCards The list of marked cards.
     * @return {@code true} if a full column is marked, otherwise {@code false}.
     */
    private static boolean checkColumnWin(Board board, List<Card> markedCards) {
        for (int col = 0; col < 4; col++) {
            boolean colWin = true;
            for (int row = 0; row < 4; row++) {
                if (!containsCard(markedCards, board.getCard(row, col))) {
                    colWin = false;
                    break;
                }
            }
            if (colWin)
                return true;
        }
        return false;
    }

    /**
     * Checks if the center 2x2 area of the board is fully marked.
     *
     * @param board       The player's board.
     * @param markedCards The list of marked cards.
     * @return {@code true} if the center four squares are marked, otherwise
     *         {@code false}.
     */
    private static boolean checkCenterFourWin(Board board, List<Card> markedCards) {
        return containsCard(markedCards, board.getCard(1, 1)) &&
                containsCard(markedCards, board.getCard(1, 2)) &&
                containsCard(markedCards, board.getCard(2, 1)) &&
                containsCard(markedCards, board.getCard(2, 2));
    }

    /**
     * Checks if all four corners of the board are marked.
     *
     * @param board       The player's board.
     * @param markedCards The list of marked cards.
     * @return {@code true} if all four corners are marked, otherwise {@code false}.
     */
    private static boolean checkFourCornersWin(Board board, List<Card> markedCards) {
        return containsCard(markedCards, board.getCard(0, 0)) &&
                containsCard(markedCards, board.getCard(0, 3)) &&
                containsCard(markedCards, board.getCard(3, 0)) &&
                containsCard(markedCards, board.getCard(3, 3));
    }

    /**
     * Checks if either diagonal (↘ or ↙) of the board is fully marked.
     *
     * @param board       The player's board.
     * @param markedCards The list of marked cards.
     * @return {@code true} if a diagonal is fully marked, otherwise {@code false}.
     */
    private static boolean checkDiagonalWin(Board board, List<Card> markedCards) {
        boolean mainDiagonal = true;
        boolean antiDiagonal = true;

        for (int i = 0; i < 4; i++) {
            if (!containsCard(markedCards, board.getCard(i, i))) {
                mainDiagonal = false;
            }
            if (!containsCard(markedCards, board.getCard(i, 3 - i))) {
                antiDiagonal = false;
            }
        }
        return mainDiagonal || antiDiagonal;
    }

    /**
     * Checks if both the four corners and the center four squares are marked.
     *
     * @param board       The player's board.
     * @param markedCards The list of marked cards.
     * @return {@code true} if both four corners and center four are marked,
     *         otherwise {@code false}.
     */
    private static boolean checkFourInAndOutWin(Board board, List<Card> markedCards) {
        return checkFourCornersWin(board, markedCards) && checkCenterFourWin(board, markedCards);
    }
}
