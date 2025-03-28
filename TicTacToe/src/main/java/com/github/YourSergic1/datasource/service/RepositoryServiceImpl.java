package com.github.YourSergic1.datasource.service;

import com.github.YourSergic1.datasource.model.GameEntity;
import com.github.YourSergic1.datasource.model.FieldEntity;
import com.github.YourSergic1.datasource.model.StatisticEntity;
import com.github.YourSergic1.datasource.model.UserEntity;
import com.github.YourSergic1.datasource.repository.FieldRepository;
import com.github.YourSergic1.datasource.repository.GameRepository;
import com.github.YourSergic1.datasource.repository.StatisticRepository;
import com.github.YourSergic1.datasource.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional
public class RepositoryServiceImpl implements RepositoryService {
    private final GameRepository gameRepository;
    private final UserRepository userRepository;
    private final FieldRepository fieldRepository;
    private final StatisticRepository statisticRepository;

    @Override
    public void saveGame(GameEntity gameEntity) {
        gameRepository.save(gameEntity);
    }

    @Override
    public void deleteStatisticByUserId(UUID userId) {
        statisticRepository.deleteByPlayerId(userId);
    }

    @Override
    public Optional<GameEntity> getGame(UUID gameId, UUID userId) {
        Optional<GameEntity> gameEntity = gameRepository.findById(gameId);
        if (gameEntity.isEmpty()) return Optional.empty();
        UUID user1 = gameEntity.get().getUser1();
        UUID user2 = gameEntity.get().getUser2();
        if (user1.equals(userId)) return gameEntity;
        if (user2 != null && user2.equals(userId)) return gameEntity;
        return Optional.empty();
    }

    @Override
    public Optional<GameEntity> getGame(UUID id) {
        return gameRepository.findById(id);
    }

    @Override
    public void saveStatistic(StatisticEntity statisticEntity) {
        statisticRepository.save(statisticEntity);
    }

    @Override
    public Optional<StatisticEntity> getStatistic(UUID playerId) {
        return statisticRepository.findByPlayerId(playerId);
    }

    @Override
    public List<StatisticEntity> getAllStaticsByLimit(int limit) {
        return statisticRepository.findTopByWinning(limit);
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
    public List<GameEntity> getAllGamesWithComputer(String login) {
        UUID mainUserId = userRepository.findByLogin(login).get().getId();
        List<GameEntity> allGames = new ArrayList<>(gameRepository.findAllByUser1(mainUserId));
        allGames = allGames.stream().filter(gameEntity -> !gameEntity.isGameWithHuman()).collect(Collectors.toList());
        return allGames;
    }

    @Override
    public List<GameEntity> getAllGamesWithHuman(String login) {
        UUID mainUserId = userRepository.findByLogin(login).get().getId();
        List<GameEntity> allGames = new ArrayList<>(gameRepository.findAllByUser1(mainUserId));
        allGames.addAll(gameRepository.findAllByUser2(mainUserId));
        allGames = allGames.stream().filter(GameEntity::isGameWithHuman).collect(Collectors.toList());
        return allGames;
    }

    @Override
    public List<GameEntity> getAllEmptyGamesWithHuman(String login) {
        List<GameEntity> allGames = new ArrayList<>(gameRepository.findAllByGameWithHumanIsTrue());
        UUID mainUserId = userRepository.findByLogin(login).get().getId();
        allGames = allGames.stream().filter(gameEntity -> gameEntity.getUser2() == null && !gameEntity.getUser1().equals(mainUserId)).collect(Collectors.toList());
        return allGames;
    }
}