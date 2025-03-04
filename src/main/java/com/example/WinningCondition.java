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
            case FULL_BOARD: checkFullBoard(markedCards);
            case ROW_WIN: checkRowWin(board, markedCards);
            case COLUMN_WIN: checkColumnWin(board, markedCards);
            case CENTER_FOUR_WIN: checkCenterFourWin(board, markedCards);
            case FOUR_CORNERS_WIN: checkFourCornersWin(board, markedCards);
            case DIAGONAL_WIN:  checkDiagonalWin(board, markedCards);
            case FOUR_IN_AND_OUT_WIN: checkFourInAndOutWin(board, markedCards);
            default: return false;
        }
    }

    public static String getWinningConditionImage() {
        return conditionNames[selectedConditionIndex];
    }
    
    private static boolean checkFullBoard(List<Card> markedCards) {
        return markedCards.size() == 16; // Assuming a 4x4 board
    }
    
    private static boolean checkRowWin(Board board, List<Card> markedCards) {
        for (int row = 0; row < 4; row++) {
            boolean rowWin = true;
            for (int col = 0; col < 4; col++) {
                if (!markedCards.contains(board.getCard(row, col))) {
                    rowWin = false;
                    break;
                }
            }
            if (rowWin) return true;
        }
        return false;
    }
    
    private static boolean checkColumnWin(Board board, List<Card> markedCards) {
        for (int col = 0; col < 4; col++) {
            boolean colWin = true;
            for (int row = 0; row < 4; row++) {
                if (!markedCards.contains(board.getCard(row, col))) {
                    colWin = false;
                    break;
                }
            }
            if (colWin) return true;
        }
        return false;
    }
    
    private static boolean checkCenterFourWin(Board board, List<Card> markedCards) {
        return markedCards.contains(board.getCard(1, 1)) && markedCards.contains(board.getCard(1, 2)) &&
               markedCards.contains(board.getCard(2, 1)) && markedCards.contains(board.getCard(2, 2));
    }
    
    private static boolean checkFourCornersWin(Board board, List<Card> markedCards) {
        return markedCards.contains(board.getCard(0, 0)) && markedCards.contains(board.getCard(0, 3)) &&
               markedCards.contains(board.getCard(3, 0)) && markedCards.contains(board.getCard(3, 3));
    }
    
    private static boolean checkDiagonalWin(Board board, List<Card> markedCards) {
        boolean mainDiagonal = true;
        boolean antiDiagonal = true;
        
        for (int i = 0; i < 4; i++) {
            if (!markedCards.contains(board.getCard(i, i))) {
                mainDiagonal = false;
            }
            if (!markedCards.contains(board.getCard(i, 3 - i))) {
                antiDiagonal = false;
            }
        }
        return mainDiagonal || antiDiagonal;
    }
    
    private static boolean checkFourInAndOutWin(Board board, List<Card> markedCards) {
        return checkFourCornersWin(board, markedCards) && checkCenterFourWin(board, markedCards);
    }
}
