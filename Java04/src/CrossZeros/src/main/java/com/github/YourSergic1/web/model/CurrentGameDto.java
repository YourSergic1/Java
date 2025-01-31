package com.github.YourSergic1.web.model;

import java.util.UUID;

public class CurrentGameDto {
    private FieldDto field;
    private UUID id;

    public CurrentGameDto(UUID id, FieldDto field) {
        this.id = id;
        this.field = field;
    }

    public UUID getId() {
        return id;
    }

    public FieldDto getField() {
        return field;
    }
}
