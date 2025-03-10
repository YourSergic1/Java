package com.github.YourSergic1.domain.service;

import com.github.YourSergic1.domain.model.Game;

import java.security.Principal;

public interface GameService {
    int[] nextMove(Game game);

    int isGameOver(Game game, Principal principal);

    boolean validateField(Game game, int row, int col);

    int whoseTurn(Game game);
}
