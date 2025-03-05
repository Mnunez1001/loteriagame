package com.example;

import java.util.List;
import java.util.Random;

public class WinningCondition {
    private static final String[] conditionNames = {
        "full", "horizontalLine", "verticalLine", "center4",
        "4Corners", "diagonal", "4InAndOut"
    };

    private static final int FULL_BOARD = 0;
    private static final int ROW_WIN = 1;
    private static final int COLUMN_WIN = 2;
    private static final int CENTER_FOUR_WIN = 3;
    private static final int FOUR_CORNERS_WIN = 4;
    private static final int DIAGONAL_WIN = 5;
    private static final int FOUR_IN_AND_OUT_WIN = 6;

    private static int selectedConditionIndex = new Random().nextInt(conditionNames.length);

    public static boolean isWinningBoard(Board board, List<Card> markedCards) {
        switch (selectedConditionIndex) {
            case FULL_BOARD: return checkFullBoard(markedCards);
            case ROW_WIN: return checkRowWin(board, markedCards);
            case COLUMN_WIN: return checkColumnWin(board, markedCards);
            case CENTER_FOUR_WIN: return checkCenterFourWin(board, markedCards);
            case FOUR_CORNERS_WIN: return checkFourCornersWin(board, markedCards);
            case DIAGONAL_WIN: return checkDiagonalWin(board, markedCards);
            case FOUR_IN_AND_OUT_WIN: return checkFourInAndOutWin(board, markedCards);
            default: return false;
        }
    }

    public static String getWinningConditionImage() {
        return conditionNames[selectedConditionIndex];
    }

    public static void generateNewCondition() {
        Random random = new Random();
        int newCondition;
        
        do {
            newCondition = random.nextInt(conditionNames.length);
        } while (newCondition == selectedConditionIndex); // Ensure it's different from the previous one
    
        selectedConditionIndex = newCondition;
    }

    public static int getCurrentWinningCondition() {
        return selectedConditionIndex;
    }
    
    

    /** Helper method to check if a card exists in markedCards based on name comparison */
    private static boolean containsCard(List<Card> markedCards, Card card) {
        for (Card marked : markedCards) {
            if (marked.getName().equals(card.getName())) {
                return true;
            }
        }
        return false;
    }

    /** Checks if all 16 board positions have been marked */
    private static boolean checkFullBoard(List<Card> markedCards) {
        return markedCards.size() == 16; // Assuming a 4x4 board
    }

    /** Checks if any row has been fully marked */
    private static boolean checkRowWin(Board board, List<Card> markedCards) {
        for (int row = 0; row < 4; row++) {
            boolean rowWin = true;
            for (int col = 0; col < 4; col++) {
                if (!containsCard(markedCards, board.getCard(row, col))) {
                    rowWin = false;
                    break;
                }
            }
            if (rowWin) return true;
        }
        return false;
    }

    /** Checks if any column has been fully marked */
    private static boolean checkColumnWin(Board board, List<Card> markedCards) {
        for (int col = 0; col < 4; col++) {
            boolean colWin = true;
            for (int row = 0; row < 4; row++) {
                if (!containsCard(markedCards, board.getCard(row, col))) {
                    colWin = false;
                    break;
                }
            }
            if (colWin) return true;
        }
        return false;
    }

    /** Checks if the center 2x2 area is marked */
    private static boolean checkCenterFourWin(Board board, List<Card> markedCards) {
        return containsCard(markedCards, board.getCard(1, 1)) &&
               containsCard(markedCards, board.getCard(1, 2)) &&
               containsCard(markedCards, board.getCard(2, 1)) &&
               containsCard(markedCards, board.getCard(2, 2));
    }

    /** Checks if all four corners are marked */
    private static boolean checkFourCornersWin(Board board, List<Card> markedCards) {
        return containsCard(markedCards, board.getCard(0, 0)) &&
               containsCard(markedCards, board.getCard(0, 3)) &&
               containsCard(markedCards, board.getCard(3, 0)) &&
               containsCard(markedCards, board.getCard(3, 3));
    }

    /** Checks if either diagonal (↘ or ↙) is fully marked */
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

    /** Checks if both four corners and center four are marked */
    private static boolean checkFourInAndOutWin(Board board, List<Card> markedCards) {
        return checkFourCornersWin(board, markedCards) && checkCenterFourWin(board, markedCards);
    }
}
