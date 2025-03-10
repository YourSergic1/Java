package com.github.YourSergic1.datasource.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "game_entity")
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GameEntity {
    @Id
    UUID id;
    int user1Figure;
    int user2Figure;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "field_entity_id", referencedColumnName = "id")
    FieldEntity field;
    UUID user1;
    UUID user2;
    boolean gameWithHuman;
}
