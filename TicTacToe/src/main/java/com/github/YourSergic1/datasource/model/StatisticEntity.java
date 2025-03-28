package com.github.YourSergic1.datasource.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "statistic_entity")
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StatisticEntity {
    @Id
    UUID uuid;
    UUID playerId;
    int allGamesCounter;
    int winningCounter;
    int lostCounter;
    int drawsCounter;
}
