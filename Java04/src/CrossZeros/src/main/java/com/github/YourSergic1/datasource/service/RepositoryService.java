package com.github.YourSergic1.datasource.service;

import com.github.YourSergic1.datasource.model.CurrentGameEntity;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

public interface RepositoryService {
    void saveGame(CurrentGameEntity gameEntity);

    CurrentGameEntity getGame(UUID id);

    Collection<CurrentGameEntity> getAllGames();
}
