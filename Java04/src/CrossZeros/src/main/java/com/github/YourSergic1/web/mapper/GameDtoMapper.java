package com.github.YourSergic1.web.mapper;

import com.github.YourSergic1.domain.model.Game;
import com.github.YourSergic1.web.model.GameDto;

public class GameDtoMapper {

    public static GameDto toDto(Game game) {
        return new GameDto(FieldDtoMapper.toDto(game.getField()), game.getId(), game.getPlayer1Figure(), game.getPlayer2Figure(), game.getUser1(), game.getUser2(), game.isGameWithHuman());
    }

    public static Game toCurrentGame(GameDto gameDto) {
        return new Game(gameDto.getId(), FieldDtoMapper.toField(gameDto.getField()), gameDto.getPlayer(), gameDto.getComputer(), gameDto.getUser1(), gameDto.getUser2(), gameDto.isGameWithHuman());
    }
}
