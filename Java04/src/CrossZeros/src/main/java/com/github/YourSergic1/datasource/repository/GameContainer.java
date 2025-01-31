package com.github.YourSergic1.datasource.repository;

import com.github.YourSergic1.datasource.model.CurrentGameEntity;
import org.springframework.context.annotation.Bean;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class GameContainer {
    private final Map<UUID, CurrentGameEntity> games = new ConcurrentHashMap<>();

    public Map<UUID, CurrentGameEntity> getGames() {
        return games;
    }
}
