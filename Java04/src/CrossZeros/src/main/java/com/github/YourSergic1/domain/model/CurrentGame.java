package com.github.YourSergic1.domain.model;

import com.github.YourSergic1.domain.service.GameServiceImplementation;

import java.util.UUID;

public class CurrentGame {
    private Field field;
    private UUID id;
    private final int computer = 0;
    private final int player = 1;

    public CurrentGame() {
        this.field = new Field();
        this.id = UUID.randomUUID();
    }

    public CurrentGame(UUID id, Field field) {
        this.field = field;
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public Field getField() {
        return field;
    }

    public void setPlayer(int row, int col) {
        field.getBoard()[row][col] = player;
    }

    public void setComputer(int row, int col) {
        field.getBoard()[row][col] = computer;
    }

    public void print() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (this.field.getBoard()[i][j] == 1) System.out.print("X ");
                else if (this.field.getBoard()[i][j] == 0) System.out.print("O ");
                else System.out.print("- ");
            }
            System.out.println();
        }
    }
}
