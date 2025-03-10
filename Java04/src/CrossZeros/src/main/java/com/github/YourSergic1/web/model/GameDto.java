package com.github.YourSergic1.web.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GameDto {
    FieldDto field;
    UUID id;
    int player;
    int computer;
    UUID user1;
    UUID user2;
    boolean gameWithHuman;
}
