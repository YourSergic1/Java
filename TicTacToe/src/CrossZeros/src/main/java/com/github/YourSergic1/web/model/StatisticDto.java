package com.github.YourSergic1.web.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StatisticDto {
    UUID uuid;
    UUID playerId;
    int allGamesCounter;
    int winningCounter;
    int lostCounter;
    int drawsCounter;
}
