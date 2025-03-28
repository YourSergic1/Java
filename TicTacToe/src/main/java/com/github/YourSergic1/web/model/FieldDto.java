package com.github.YourSergic1.web.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FieldDto {
    int[][] board;

    public FieldDto() {
        this.board = new int[3][3];
    }
}
