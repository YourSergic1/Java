package com.github.YourSergic1.datasource.service;

import com.github.YourSergic1.datasource.model.GameEntity;
import com.github.YourSergic1.datasource.model.FieldEntity;
import com.github.YourSergic1.datasource.model.StatisticEntity;
import com.github.YourSergic1.datasource.model.UserEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RepositoryService {
    void saveGame(GameEntity gameEntity);

    void deleteStatisticByUserId(UUID userId);

    Optional<GameEntity> getGame(UUID gameId, UUID userId);

    List<GameEntity> getAllGamesWithComputer(String login);

    List<GameEntity> getAllGamesWithHuman(String login);

    void saveField(FieldEntity fieldEntity);

    Optional<FieldEntity> getField(Long id);

    void saveUser(UserEntity userEntity);

    Optional<UserEntity> getUser(UUID id);

    void deleteUser(UUID id);

    List<GameEntity> getAllEmptyGamesWithHuman(String login);

    Optional<UserEntity> getUser(String login);

    Optional<GameEntity> getGame(UUID id);

    void saveStatistic(StatisticEntity statisticEntity);

    Optional<StatisticEntity> getStatistic(UUID userId);

    List<StatisticEntity> getAllStaticsByLimit(int limit);
}