package com.github.YourSergic1.web.mapper;

import com.github.YourSergic1.domain.model.CurrentGame;
import com.github.YourSergic1.domain.model.Field;
import com.github.YourSergic1.web.model.CurrentGameDto;
import com.github.YourSergic1.web.model.FieldDto;

public class GameDtoMapper {

    public static FieldDto toDto(Field field) {
        FieldDto fieldDto = new FieldDto();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                fieldDto.getField()[i][j] = field.getBoard()[i][j];
            }
        }
        return fieldDto;
    }

    public static Field toField(FieldDto fieldDto) {
        Field field = new Field();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                field.getBoard()[i][j] = fieldDto.getField()[i][j];
            }
        }
        return field;
    }

    public static CurrentGameDto toDto(CurrentGame currentGame) {
        return new CurrentGameDto(currentGame.getId(), toDto(currentGame.getField()));
    }

    public static CurrentGame toCurrentGame(CurrentGameDto currentGameDto) {
        return new CurrentGame(currentGameDto.getId(), toField(currentGameDto.getField()));
    }
}
