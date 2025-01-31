package com.github.YourSergic1.datasource.service;

import com.github.YourSergic1.datasource.model.CurrentGameEntity;
import com.github.YourSergic1.datasource.repository.GameRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

@Service
public class RepositoryServiceImpl implements RepositoryService {
    private GameRepository gameRepository;

    public RepositoryServiceImpl(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Override
    public void saveGame(CurrentGameEntity gameEntity) {
        gameRepository.saveGame(gameEntity);
    }

    @Override
    public CurrentGameEntity getGame(UUID id) {
        return gameRepository.getCurrentGame(id);
    }

    @Override
    public Collection<CurrentGameEntity> getAllGames() {
        return gameRepository.getAllGames();
    }
}
