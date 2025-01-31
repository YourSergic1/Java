package com.github.YourSergic1.datasource.model;

public class FieldEntity {
    private int[][] board;

    public FieldEntity() {
        this.board = new int[3][3];
    }

    public int[][] getField() {
        return board;
    }
}
