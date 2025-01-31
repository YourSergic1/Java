package com.github.YourSergic1.datasource.model;

import java.util.UUID;

public class CurrentGameEntity {
    private FieldEntity field;
    private UUID id;

    public CurrentGameEntity(UUID id, FieldEntity field) {
        this.id = id;
        this.field = field;
    }

    public UUID getId() {
        return id;
    }

    public FieldEntity getField() {
        return field;
    }
}
