package com.github.YourSergic1.datasource.service;

import com.github.YourSergic1.datasource.model.GameEntity;
import com.github.YourSergic1.datasource.model.FieldEntity;
import com.github.YourSergic1.datasource.model.UserEntity;
import com.github.YourSergic1.datasource.repository.FieldRepository;
import com.github.YourSergic1.datasource.repository.GameRepository;
import com.github.YourSergic1.datasource.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RepositoryServiceImpl implements RepositoryService {
    private final GameRepository gameRepository;
    private final UserRepository userRepository;
    private final FieldRepository fieldRepository;

    @Override
    public void saveGame(GameEntity gameEntity) {
        gameRepository.save(gameEntity);
    }

    @Override
    public Optional<GameEntity> getGame(UUID id, Principal principal) {
        UUID mainUserId = userRepository.findByLogin(principal.getName()).get().getId();
        Optional<GameEntity> gameEntity = gameRepository.findById(id);
        if (gameEntity.isEmpty()) return Optional.empty();
        UUID user1 = gameEntity.get().getUser1();
        UUID user2 = gameEntity.get().getUser2();
        if (user1.equals(mainUserId)) return gameEntity;
        if (user2 != null && user2.equals(mainUserId)) return gameEntity;
        return Optional.empty();
    }

    @Override
    public Optional<GameEntity> getGame(UUID id) {
        return gameRepository.findById(id);

    }

    @Override
    public void saveField(FieldEntity fieldEntity) {
        fieldRepository.save(fieldEntity);
    }

    @Override
    public Optional<FieldEntity> getField(Long id) {
        return fieldRepository.findById(id);
    }

    @Override
    public void saveUser(UserEntity userEntity) {
        userRepository.save(userEntity);
    }

    @Override
    public Optional<UserEntity> getUser(UUID id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<UserEntity> getUser(String login) {
        return userRepository.findByLogin(login);
    }

    @Override
    public void deleteUser(UUID id) {
        gameRepository.deleteGamesByUserId(id);
        userRepository.deleteById(id);
    }

    @Override
    public List<GameEntity> getAllGamesWithComputer(Principal principal) {
        UUID mainUserId = userRepository.findByLogin(principal.getName()).get().getId();
        List<GameEntity> allGames = new ArrayList<>(gameRepository.findAllByUser1(mainUserId));
        allGames = allGames.stream().filter(gameEntity -> !gameEntity.isGameWithHuman()).collect(Collectors.toList());
        return allGames;
    }

    @Override
    public List<GameEntity> getAllGamesWithHuman(Principal principal) {
        UUID mainUserId = userRepository.findByLogin(principal.getName()).get().getId();
        List<GameEntity> allGames = new ArrayList<>(gameRepository.findAllByUser1(mainUserId));
        allGames.addAll(gameRepository.findAllByUser2(mainUserId));
        allGames = allGames.stream().filter(GameEntity::isGameWithHuman).collect(Collectors.toList());
        return allGames;
    }

    @Override
    public List<GameEntity> getAllEmptyGamesWithHuman(Principal principal) {
        List<GameEntity> allGames = new ArrayList<>(gameRepository.findAllByGameWithHumanIsTrue());
        UUID mainUserId = userRepository.findByLogin(principal.getName()).get().getId();
        allGames = allGames.stream().filter(gameEntity -> gameEntity.getUser2() == null && !gameEntity.getUser1().equals(mainUserId)).collect(Collectors.toList());
        return allGames;
    }
}