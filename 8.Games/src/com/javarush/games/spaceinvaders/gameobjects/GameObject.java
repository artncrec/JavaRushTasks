package com.javarush.games.spaceinvaders.gameobjects;

import com.javarush.engine.cell.*;

public class GameObject {
    public double x, y;
    public int[][] matrix;
    public int width, height;

    public GameObject(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void setMatrix(int[][] matrix) {
        width = matrix[0].length;
        height = matrix.length;
        this.matrix = matrix;
    }

    public void draw(Game game){
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                game.setCellValueEx( j + (int)this.x,  i + (int)this.y, Color.values()[matrix[i][j]], "");
            }
        }
    }
}
