package com.github.YourSergic1.domain.service;

import com.github.YourSergic1.domain.model.CurrentGame;
import org.springframework.stereotype.Service;

@Service
public class GameServiceImplementation implements GameService {
    private final int computer = 0;
    private final int player = 1;
    private final boolean playerTurn = false;
    private final boolean computerTurn = true;

    @Override
    public int isGameOver(int[][] board) {
        for (int rowCount = 0; rowCount < 3; rowCount++) {
            int computerRowCounter = 0;
            int playerRowCounter = 0;
            int computerColCounter = 0;
            int playerColCounter = 0;
            for (int colCount = 0; colCount < 3; colCount++) {
                if (board[rowCount][colCount] == player) playerRowCounter++;
                else if (board[rowCount][colCount] == computer) computerRowCounter++;

                if (board[colCount][rowCount] == player) playerColCounter++;
                else if (board[colCount][rowCount] == computer) computerColCounter++;
            }
            if (computerRowCounter == 3 || computerColCounter == 3) return 10;
            if (playerRowCounter == 3 || playerColCounter == 3) return -10;
        }

        if (board[0][0] == player && board[1][1] == player && board[2][2] == player ||
                board[0][2] == player && board[1][1] == player && board[2][0] == player)
            return -10;
        if (board[0][0] == computer && board[1][1] == computer && board[2][2] == computer ||
                board[0][2] == computer && board[1][1] == computer && board[2][0] == computer)
            return 10;

        boolean isDraw = true;
        for (int rowCount = 0; rowCount < 3; rowCount++) {
            for (int colCount = 0; colCount < 3; colCount++) {
                if (board[rowCount][colCount] == -1) {
                    isDraw = false;
                    break;
                }
            }
            if (!isDraw) break;
        }
        if (isDraw) return 0;

        return 1;
    }

    @Override
    public boolean validateField(CurrentGame game, int row, int col) {
        return game.getField().getBoard()[row][col] != computer && game.getField().getBoard()[row][col] != player;
    }

    @Override
    public int[] nextMove(CurrentGame game) {
        int row = -1;
        int col = -1;
        int best_score = Integer.MIN_VALUE;
        int[][] board = game.getField().getBoard();
        for (int rowCount = 0; rowCount < 3; rowCount++) {
            for (int colCount = 0; colCount < 3; colCount++) {
                if (board[rowCount][colCount] == -1) {
                    board[rowCount][colCount] = computer;
                    int score = minimax(board, 0, playerTurn);
                    board[rowCount][colCount] = -1;
                    if (score > best_score) {
                        best_score = score;
                        row = rowCount;
                        col = colCount;
                    }
                }
            }
        }
        if (row == -1 && col == -1) return null;
        else return new int[]{row, col};
    }

    private int minimax(int[][] board, int depth, boolean isMaximizing) {
        if (isGameOver(board) != 1) return isGameOver(board);
        int best_score;
        if (isMaximizing) {
            best_score = Integer.MIN_VALUE;
            for (int rowCount = 0; rowCount < 3; rowCount++) {
                for (int colCount = 0; colCount < 3; colCount++) {
                    if (board[rowCount][colCount] == -1) {
                        board[rowCount][colCount] = computer;
                        int score = minimax(board, depth + 1, playerTurn);
                        board[rowCount][colCount] = -1;
                        if (score > best_score) {
                            best_score = score;
                        }
                    }
                }
            }
        } else {
            best_score = Integer.MAX_VALUE;
            for (int rowCount = 0; rowCount < 3; rowCount++) {
                for (int colCount = 0; colCount < 3; colCount++) {
                    if (board[rowCount][colCount] == -1) {
                        board[rowCount][colCount] = player;
                        int score = minimax(board, depth + 1, computerTurn);
                        board[rowCount][colCount] = -1;
                        if (score < best_score) {
                            best_score = score;
                        }
                    }
                }
            }
        }
        return best_score;
    }
}
