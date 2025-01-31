package com.github.YourSergic1.datasource.repository;

import com.github.YourSergic1.datasource.model.CurrentGameEntity;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

public class GameRepository {
    private final GameContainer gameContainer;

    public GameRepository(GameContainer gameContainer) {
        this.gameContainer = gameContainer;
    }

    public void saveGame(CurrentGameEntity gameEntity) {
        gameContainer.getGames().put(gameEntity.getId(), gameEntity);
    }

    public CurrentGameEntity getCurrentGame(UUID id) {
        return gameContainer.getGames().get(id);
    }

    public Collection<CurrentGameEntity> getAllGames() {
        return gameContainer.getGames().values();
    }
}