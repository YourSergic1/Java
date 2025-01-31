package com.github.YourSergic1.domain.service;

import com.github.YourSergic1.domain.model.CurrentGame;

public interface GameService {

    int[] nextMove(CurrentGame game);

    int isGameOver(int[][] board);

    boolean validateField(CurrentGame game, int row, int col);
}
