package com.github.YourSergic1.domain.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@Data
@Builder
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Statistic {
    UUID uuid;
    UUID playerId;
    int allGamesCounter;
    int winningCounter;
    int lostCounter;
    int drawsCounter;

    public Statistic(UUID playerId) {
        this.uuid = UUID.randomUUID();
        this.playerId = playerId;
        this.allGamesCounter = 0;
        this.winningCounter = 0;
        this.lostCounter = 0;
        this.drawsCounter = 0;
    }

    public Statistic(UUID uuid, UUID playerId, int allGamesCounter, int winningCounter, int lostCounter, int drawsCounter) {
        this.uuid = uuid;
        this.playerId = playerId;
        this.allGamesCounter = allGamesCounter;
        this.winningCounter = winningCounter;
        this.lostCounter = lostCounter;
        this.drawsCounter = drawsCounter;
    }
}

