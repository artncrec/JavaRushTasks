package com.javarush.games.game2048;

import com.javarush.engine.cell.*;

public class Game2048 extends Game {
    private static final int SIDE = 4;
    private int[][] gameField = new int[SIDE][SIDE];
    private boolean isGameStopped = false;
    private int score = 0;

    @Override
    public void initialize() {
        setScreenSize(SIDE, SIDE);
        createGame();
        drawScene();
    }

    private void createGame() {
        score = 0;
        setScore(score);
        gameField = new int[SIDE][SIDE];
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
        if (getMaxTileValue()==2048)
            win();
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

    @Override
    public void onKeyPress(Key key) {
        if (isGameStopped) {
            if (key == Key.SPACE) {
                isGameStopped = false;
                createGame();
                drawScene();
            }
        } else if (!canUserMove()) {
            gameOver();
        } else {
            if (key == Key.RIGHT) {
                moveRight();
                drawScene();
            }
            if (key == Key.LEFT) {
                moveLeft();
                drawScene();
            }
            if (key == Key.UP) {
                moveUp();
                drawScene();
            }
            if (key == Key.DOWN) {
                moveDown();
                drawScene();
            }
        }
    }

    private void moveRight() {
        rotateClockwise();
        rotateClockwise();
        moveLeft();
        rotateClockwise();
        rotateClockwise();
    }

    private void moveLeft() {
        boolean change, needNewNumber = false;
        for (int i = 0; i < gameField.length; i++) {
            change = false;
            change = compressRow(gameField[i]);
            if (change)
                needNewNumber = true;
            change = mergeRow(gameField[i]);
            if (change) {
                compressRow(gameField[i]);
                needNewNumber = true;
            }
        }
        if (needNewNumber)
            createNewNumber();
    }

    private void moveUp() {
        rotateClockwise();
        rotateClockwise();
        rotateClockwise();
        moveLeft();
        rotateClockwise();
    }

    private void moveDown() {
        rotateClockwise();
        moveLeft();
        rotateClockwise();
        rotateClockwise();
        rotateClockwise();
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

    private boolean mergeRow(int[] row) {
        boolean result = false;
        for (int i = 0; i < row.length - 1; i++) {
            if (row[i] == row[i + 1] && row[i] != 0) {
                score += row[i] * 2;
                setScore(score);
                row[i] *= 2;
                row[i + 1] = 0;
                result = true;
            }
        }
        return result;
    }

    private void rotateClockwise(){
        int [][] rotatedMatrix = new int[gameField.length][gameField[0].length];
        for (int i = 0; i < gameField.length; i++) {
            for (int j = 0; j < gameField[i].length; j++) {
                rotatedMatrix[j][gameField[i].length - 1 - i] = gameField[i][j];
            }
        }
        gameField = rotatedMatrix;
    }

    private int getMaxTileValue() {
        int max = 0;
        for (int i = 0; i < gameField.length; i++) {
            for (int j = 0; j < gameField[i].length; j++) {
                if (gameField[i][j] > max)
                    max = gameField[i][j];
            }
        }
        return max;
    }

    private void win(){
        isGameStopped = true;
        showMessageDialog(Color.NONE, "YOU WIN!!!", Color.DARKRED, 75);
    }

    private void gameOver(){
        isGameStopped=true;
        showMessageDialog(Color.DARKRED, "YOU LOSE!!!", Color.KHAKI, 75);
    }

    private boolean canUserMove() {
        for (int i = 0; i < gameField.length; i++) {
            for (int j = 0; j < gameField[i].length; j++) {
                if (gameField[i][j] == 0)
                    return true;
                if (i < gameField.length - 1 && gameField[i][j] == gameField[i + 1][j])
                    return true;
                if (j < gameField[i].length - 1 && gameField[i][j] == gameField[i][j + 1])
                    return true;
            }
        }
        return false;
    }
}