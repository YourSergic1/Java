package com.github.YourSergic1.domain.service;

import com.github.YourSergic1.datasource.service.RepositoryServiceImpl;
import com.github.YourSergic1.domain.model.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class GameService {
    private final RepositoryServiceImpl repositoryServiceImpl;
    private final boolean playerTurn = false;
    private final boolean computerTurn = true;

    @Autowired
    GameService(RepositoryServiceImpl repositoryService) {
        this.repositoryServiceImpl = repositoryService;
    }

    public int isGameOver(Game game, String login) {
        int result;
        if (!game.isGameWithHuman()) result = isGameOverWithComputer(game);
        else result = isGameOverWithHuman(game, login);
        return result;
    }

    public int isGameOverWithHuman(Game game, String login) {
        int[][] board = game.getField().getBoard();
        UUID userId = repositoryServiceImpl.getUser(login).get().getId();
        int player1 = game.getPlayer1Figure();
        int player2 = game.getPlayer2Figure();

        // Проверяем, кто текущий пользователь

        boolean isUserPlayer1 = game.getUser1().equals(userId);
        boolean isUserPlayer2 = false;
        if (game.getUser2() != null) isUserPlayer2 = game.getUser2().equals(userId);

        // Проверка строк и столбцов
        for (int rowCount = 0; rowCount < 3; rowCount++) {
            if (board[rowCount][0] == board[rowCount][1] && board[rowCount][1] == board[rowCount][2]) {
                if (board[rowCount][0] == player1 && isUserPlayer1) return -10;
                if (board[rowCount][0] == player1 && isUserPlayer2) return 10;
                if (board[rowCount][0] == player2 && isUserPlayer2) return -10;
                if (board[rowCount][0] == player2 && isUserPlayer1) return 10;
            }
            if (board[0][rowCount] == board[1][rowCount] && board[1][rowCount] == board[2][rowCount]) {
                if (board[0][rowCount] == player1 && isUserPlayer1) return -10;
                if (board[0][rowCount] == player1 && isUserPlayer2) return 10;
                if (board[0][rowCount] == player2 && isUserPlayer2) return -10;
                if (board[0][rowCount] == player2 && isUserPlayer1) return 10;
            }
        }

        // Проверка диагоналей
        if (board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
            if (board[0][0] == player1 && isUserPlayer1) return -10;
            if (board[0][0] == player1 && isUserPlayer2) return 10;
            if (board[0][0] == player2 && isUserPlayer2) return -10;
            if (board[0][0] == player2 && isUserPlayer1) return 10;
        }
        if (board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
            if (board[0][2] == player1 && isUserPlayer1) return -10;
            if (board[0][2] == player1 && isUserPlayer2) return 10;
            if (board[0][2] == player2 && isUserPlayer2) return -10;
            if (board[0][2] == player2 && isUserPlayer1) return 10;
        }

        // Проверка на ничью
        for (int rowCount = 0; rowCount < 3; rowCount++) {
            for (int colCount = 0; colCount < 3; colCount++) {
                if (board[rowCount][colCount] == -1) {
                    return 1; // Игра продолжается
                }
            }
        }

        return 0; // Ничья
    }

    public int isGameOverWithComputer(Game game) {
        int[][] board = game.getField().getBoard();
        int player = game.getPlayer1Figure();
        int computer = game.getPlayer2Figure();
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

    public boolean validateField(Game game, int row, int col) {
        int player = game.getPlayer1Figure();
        int computer = game.getPlayer2Figure();
        return game.getField().getBoard()[row][col] != computer && game.getField().getBoard()[row][col] != player;
    }

    public int[] nextMove(Game game) {
        int computer = game.getPlayer2Figure();
        int row = -1;
        int col = -1;
        int best_score = Integer.MIN_VALUE;
        int[][] board = game.getField().getBoard();
        for (int rowCount = 0; rowCount < 3; rowCount++) {
            for (int colCount = 0; colCount < 3; colCount++) {
                if (board[rowCount][colCount] == -1) {
                    board[rowCount][colCount] = computer;
                    int score = minimax(game, 0, playerTurn);
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

    private int minimax(Game game, int depth, boolean isMaximizing) {
        int[][] board = game.getField().getBoard();
        int player = game.getPlayer1Figure();
        int computer = game.getPlayer2Figure();
        if (isGameOverWithComputer(game) != 1) return isGameOverWithComputer(game);
        int best_score;
        if (isMaximizing) {
            best_score = Integer.MIN_VALUE;
            for (int rowCount = 0; rowCount < 3; rowCount++) {
                for (int colCount = 0; colCount < 3; colCount++) {
                    if (board[rowCount][colCount] == -1) {
                        board[rowCount][colCount] = computer;
                        int score = minimax(game, depth + 1, playerTurn);
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
                        int score = minimax(game, depth + 1, computerTurn);
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

    public int whoseTurn(Game game) {
        int[][] board = game.getField().getBoard();
        int xCounter = 0;
        int oCounter = 0;
        for (int rowCount = 0; rowCount < 3; rowCount++) {
            for (int colCount = 0; colCount < 3; colCount++) {
                if (board[rowCount][colCount] == 1) {
                    xCounter++;
                }
                if (board[rowCount][colCount] == 0) {
                    oCounter++;
                }
            }
        }
        if (xCounter == oCounter) return 1;
        else return 0;
    }

    public boolean isPlayerTurn(Game game, String login) {
        UUID userId = repositoryServiceImpl.getUser(login).get().getId();
        int whoseTurn = whoseTurn(game);
        boolean isUserPlayer1 = game.getUser1().equals(userId);
        if (isUserPlayer1) return game.getPlayer1Figure() == whoseTurn;
        else return game.getPlayer2Figure() == whoseTurn;
    }

    public int playerFigure(Game game, String login) {
        UUID userId = repositoryServiceImpl.getUser(login).get().getId();
        boolean isUserPlayer1 = game.getUser1().equals(userId);
        if (isUserPlayer1) return game.getPlayer1Figure();
        else return game.getPlayer2Figure();
    }
}
