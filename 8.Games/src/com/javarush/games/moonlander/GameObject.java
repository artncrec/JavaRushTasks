package com.javarush.games.moonlander;

import com.javarush.engine.cell.*;

public class GameObject {
    public double x, y;
    public int width, height;
    public int[][] matrix;

    public GameObject(double x, double y, int[][] matrix) {
        this.x = x;
        this.y = y;
        this.matrix = matrix;
        width = matrix[0].length;
        height = matrix.length;
    }

    public void draw(Game game){
        if (matrix!=null) {
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[i].length; j++) {
                    game.setCellColor((int) x + j, (int) y + i, Color.values()[matrix[i][j]]);
                }
            }
        }
    }
}
