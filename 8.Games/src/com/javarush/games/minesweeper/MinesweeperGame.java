package com.javarush.games.minesweeper;

import com.javarush.engine.cell.*;

import java.util.List;

public class MinesweeperGame extends Game {
    private static final int SIDE = 9;
    private int countMinesOnField = 0;
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
                else
                    gameField[i][j] = new GameObject(j, i, false);
                setCellColor(i, j, Color.BURLYWOOD);
            }
        }
    }

    private List<GameObject> getNeighbors(GameObject gameObject){
        return null;
    }
}
