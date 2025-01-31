package com.github.YourSergic1.domain.model;

public class Field {
    private int[][] board;

    public Field() {
        this.board = new int[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                this.board[i][j] = -1;
            }
        }
    }

    public int[][] getBoard() {
        return board;
    }
}
