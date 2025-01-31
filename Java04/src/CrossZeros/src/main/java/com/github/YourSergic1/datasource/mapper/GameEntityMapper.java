package com.github.YourSergic1.datasource.mapper;

import com.github.YourSergic1.datasource.model.CurrentGameEntity;
import com.github.YourSergic1.datasource.model.FieldEntity;
import com.github.YourSergic1.domain.model.CurrentGame;
import com.github.YourSergic1.domain.model.Field;

public class GameEntityMapper {

    public static FieldEntity FieldToEntity(Field field) {
        FieldEntity fieldEntity = new FieldEntity();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                fieldEntity.getField()[i][j] = field.getBoard()[i][j];
            }
        }
        return fieldEntity;
    }

    public static Field EntityToField(FieldEntity fieldEntity) {
        Field field = new Field();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                field.getBoard()[i][j] = fieldEntity.getField()[i][j];
            }
        }
        return field;
    }

    public static CurrentGameEntity GameToEntity(CurrentGame game) {
        return new CurrentGameEntity(game.getId(), FieldToEntity(game.getField()));
    }

    public static CurrentGame EntityToGame(CurrentGameEntity gameEntity) {
        return new CurrentGame(gameEntity.getId(), EntityToField(gameEntity.getField()));
    }
}
