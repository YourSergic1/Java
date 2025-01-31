package com.github.YourSergic1.web.model;

public class FieldDto {
    private int[][] board;

    public FieldDto() {
        this.board = new int[3][3];
    }

    public int[][] getField() {
        return board;
    }
}
