package com.github.YourSergic1.datasource.service;

import com.github.YourSergic1.datasource.model.GameEntity;
import com.github.YourSergic1.datasource.model.FieldEntity;
import com.github.YourSergic1.datasource.model.UserEntity;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RepositoryService {
    void saveGame(GameEntity gameEntity);

    Optional<GameEntity> getGame(UUID id, Principal principal);

    List<GameEntity> getAllGamesWithComputer(Principal principal);

    List<GameEntity> getAllGamesWithHuman(Principal principal);

    void saveField(FieldEntity fieldEntity);

    Optional<FieldEntity> getField(Long id);

    void saveUser(UserEntity userEntity);

    Optional<UserEntity> getUser(UUID id);

    void deleteUser(UUID id);

    List<GameEntity> getAllEmptyGamesWithHuman(Principal principal);

    Optional<UserEntity> getUser(String login);

    Optional<GameEntity> getGame(UUID id);
}
