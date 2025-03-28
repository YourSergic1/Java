package com.github.YourSergic1.datasource.mapper;

import com.github.YourSergic1.datasource.model.GameEntity;
import com.github.YourSergic1.domain.model.Game;

import static com.github.YourSergic1.datasource.mapper.FieldEntityMapper.EntityToField;
import static com.github.YourSergic1.datasource.mapper.FieldEntityMapper.FieldToEntity;

public class GameEntityMapper {

    public static GameEntity GameToEntity(Game game) {
        GameEntity gameEntity = new GameEntity();
        gameEntity.setId(game.getId());
        gameEntity.setField(FieldToEntity(game.getField()));
        gameEntity.setUser1Figure(game.getPlayer1Figure());
        gameEntity.setUser2Figure(game.getPlayer2Figure());
        gameEntity.setUser2(game.getUser2());
        gameEntity.setUser1(game.getUser1());
        gameEntity.setGameWithHuman(game.isGameWithHuman());
        gameEntity.setCreationDate(game.getCreationDate());
        gameEntity.setInGame(game.isInGame());
        return gameEntity;
    }

    public static Game EntityToGame(GameEntity gameEntity) {
        return new Game(gameEntity.getId(), EntityToField(gameEntity.getField()), gameEntity.getUser2Figure(), gameEntity.getUser1Figure(), gameEntity.getUser1(), gameEntity.getUser2(), gameEntity.isGameWithHuman(), gameEntity.getCreationDate(), gameEntity.isInGame());
    }
}
