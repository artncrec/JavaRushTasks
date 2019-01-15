package com.javarush.games.game2048;

import com.javarush.engine.cell.*;

public class Game2048 extends Game {
    private static final int SIDE = 4;
    private int[][] gameField = new int[SIDE][SIDE];

    @Override
    public void initialize() {
        setScreenSize(SIDE, SIDE);
        createGame();
        drawScene();
    }

    private void createGame() {
        createNewNumber();
        createNewNumber();
    }

    private void drawScene() {
        for (int x = 0; x < gameField.length; x++) {
            for (int y = 0; y < gameField[x].length; y++) {
                setCellColoredNumber(x, y, gameField[y][x]);
            }
        }
    }

    private void createNewNumber() {
        int x, y;
        do {
            x = getRandomNumber(SIDE);
            y = getRandomNumber(SIDE);
        }
        while (gameField[x][y] != 0);
        int i = getRandomNumber(10);
        if (i < 9)
            gameField[x][y] = 2;
        else
            gameField[x][y] = 4;
    }

    private Color getColorByValue(int value) {
        switch (value) {
            case 0:
                return Color.SNOW;
            case 2:
                return Color.AQUAMARINE;
            case 4:
                return Color.LIGHTSKYBLUE;
            case 8:
                return Color.LIGHTSEAGREEN;
            case 16:
                return Color.MEDIUMPURPLE;
            case 32:
                return Color.VIOLET;
            case 64:
                return Color.TOMATO;
            case 128:
                return Color.GOLD;
            case 256:
                return Color.MEDIUMSPRINGGREEN;
            case 512:
                return Color.LAWNGREEN;
            case 1024:
                return Color.DARKORANGE;
            case 2048:
                return Color.ORANGERED;
            default:
                return Color.NONE;
        }
    }

    private void setCellColoredNumber(int x, int y, int value) {
        Color color = getColorByValue(value);
        setCellValueEx(x, y, color, value == 0 ? "" : value + "");
    }

    private boolean compressRow(int[] row) {
        boolean result = false;
        for (int i = row.length - 1; i > 0; i--) {
            if (row[i] != 0 && row[i - 1] == 0) {
                int j = i;
                while (j <= row.length - 1) {
                    int x = row[j];
                    row[j] = row[j - 1];
                    row[j - 1] = x;
                    result = true;
                    j++;
                }
            }
        }
        return result;
    }
}