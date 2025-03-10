package com.github.YourSergic1.domain.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.UUID;


@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Game {
    Field field;
    UUID id;
    int player2Figure = 0;
    int player1Figure = 1;
    UUID user1;
    UUID user2;
    boolean gameWithHuman;

    public Game(UUID id, Field field, int player2Figure, int player1Figure, UUID user1, UUID user2, boolean gameWithHuman) {
        this.field = field;
        this.id = id;
        this.player2Figure = player2Figure;
        this.player1Figure = player1Figure;
        this.user1 = user1;
        this.user2 = user2;
        this.gameWithHuman = gameWithHuman;
    }

    public Game(int player2Figure, int player1Figure, UUID user1, UUID user2, boolean gameWithHuman) {
        this.field = new Field();
        this.id = UUID.randomUUID();
        this.player2Figure = player2Figure;
        this.player1Figure = player1Figure;
        this.user1 = user1;
        this.user2 = user2;
        this.gameWithHuman = gameWithHuman;
    }

    public void setPlayer1(int row, int col) {
        field.getBoard()[row][col] = player1Figure;
    }

    public void setPlayer2(int row, int col) {
        field.getBoard()[row][col] = player2Figure;
    }
}
