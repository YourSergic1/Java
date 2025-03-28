package com.github.YourSergic1.datasource.mapper;

import com.github.YourSergic1.datasource.model.FieldEntity;
import com.github.YourSergic1.domain.model.Field;

import java.util.ArrayList;
import java.util.List;

public class FieldEntityMapper {
    public static FieldEntity FieldToEntity(Field field) {
        List<Integer> data = new ArrayList<>(9);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                data.add(field.getBoard()[i][j]);
            }
        }
        FieldEntity fieldEntity = new FieldEntity();
        fieldEntity.setBoard(data);
        return fieldEntity;
    }

    public static Field EntityToField(FieldEntity fieldEntity) {
        Field field = new Field();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                field.getBoard()[i][j] = fieldEntity.getBoard().get(i * 3 + j);
            }
        }
        return field;
    }
}
