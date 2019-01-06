package com.javarush.games.minesweeper;

import com.javarush.engine.cell.*;

import java.util.ArrayList;
import java.util.List;

public class MinesweeperGame extends Game {
    private static final int SIDE = 9;
    private static final String MINE = "\uD83D\uDCA3", FLAG = "\uD83D\uDEA9";
    private boolean isGameStopped;
    private int countMinesOnField = 0, countFlags, countClosedTiles = SIDE * SIDE, score;
    private GameObject[][] gameField = new GameObject[SIDE][SIDE];

    @Override
    public void initialize() {
        setScreenSize(SIDE, SIDE);
        createGame();
    }

    private void createGame() {
        for (int i = 0; i < gameField.length; i++) {
            for (int j = 0; j < gameField[i].length; j++) {
                if (getRandomNumber(10) == 5) {
                    gameField[i][j] = new GameObject(j, i, true);
                    countMinesOnField++;
                }
                else {
                    gameField[i][j] = new GameObject(j, i, false);
                }
                setCellColor(i, j, Color.BURLYWOOD);
                setCellValue(i, j, "");
            }
        }
        countFlags = countMinesOnField;
        countMineNeighbors();
    }

    private void gameOver() {
        isGameStopped = true;
        showMessageDialog(Color.NONE, "GAME OVER!", Color.BLACK, 75);
    }

    private void win() {
        isGameStopped = true;
        showMessageDialog(Color.NONE, "YOU WIN!", Color.HOTPINK, 75);
    }

    private void restart() {
        isGameStopped = false;
        countClosedTiles = SIDE * SIDE;
        score = 0;
        countMinesOnField = 0;
        setScore(score);
        createGame();
    }

    private List<GameObject> getNeighbors(GameObject gameObject) {
        List<GameObject> gameObjects = new ArrayList<>();
        int j = gameObject.x;
        int i = gameObject.y;
        if (i - 1 >= 0) {
            if (j - 1 >= 0)
                gameObjects.add(gameField[i - 1][j - 1]);
            if (j + 1 < SIDE)
                gameObjects.add(gameField[i - 1][j + 1]);
            gameObjects.add(gameField[i - 1][j]);
        }
        if (i + 1 < SIDE) {
            if (j - 1 >= 0)
                gameObjects.add(gameField[i + 1][j - 1]);
            if (j + 1 < SIDE)
                gameObjects.add(gameField[i + 1][j + 1]);
            gameObjects.add(gameField[i + 1][j]);
        }
        if (j - 1 >= 0)
            gameObjects.add(gameField[i][j - 1]);
        if (j + 1 < SIDE)
            gameObjects.add(gameField[i][j + 1]);
        return gameObjects;
    }

    private void countMineNeighbors() {
        for (int i = 0; i < gameField.length; i++) {
            for (int j = 0; j < gameField[i].length; j++) {
                if (!gameField[i][j].isMine)
                    for (GameObject o : getNeighbors(gameField[i][j]))
                        if (o.isMine)
                            gameField[i][j].countMineNeighbors++;
            }
        }
    }

    private void openTile(int x, int y) {
        if (!gameField[y][x].isOpen && !gameField[y][x].isFlag && !isGameStopped)
            if (gameField[y][x].isMine) {
                setCellValueEx(x, y, Color.RED, MINE);
                gameOver();
            }
            else {
                if (gameField[y][x].countMineNeighbors == 0) {
                    setCellValue(x, y, "");
                    setCellColor(x, y, Color.LEMONCHIFFON);
                    gameField[y][x].isOpen = true;
                    score += 5;
                    countClosedTiles--;
                    for (GameObject o : getNeighbors(gameField[y][x]))
                        if (!o.isOpen)
                            openTile(o.x, o.y);
                }
                else {
                    setCellNumber(x, y, gameField[y][x].countMineNeighbors);
                    gameField[y][x].isOpen = true;
                    score += 5;
                    countClosedTiles--;
                    setCellColor(x, y, Color.LEMONCHIFFON);
                }
            }
        setScore(score);
        if (countClosedTiles == countMinesOnField)
            win();
    }

    @Override
    public void onMouseLeftClick(int x, int y) {
        if (isGameStopped)
            restart();
        else
            openTile(x, y);
    }

    private void markTile(int x, int y) {
        if (!gameField[y][x].isOpen && !isGameStopped)
            if (countFlags != 0 && !gameField[y][x].isFlag) {
                gameField[y][x].isFlag = true;
                countFlags--;
                setCellValue(x, y, FLAG);
                setCellColor(x, y, Color.GOLDENROD);
            }
            else if (gameField[y][x].isFlag) {
                gameField[y][x].isFlag = false;
                countFlags++;
                setCellValue(x, y, "");
                setCellColor(x, y, Color.BURLYWOOD);
            }
    }

    @Override
    public void onMouseRightClick(int x, int y) {
        markTile(x, y);
    }
}