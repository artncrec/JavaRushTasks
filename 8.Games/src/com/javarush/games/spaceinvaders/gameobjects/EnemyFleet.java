package com.javarush.games.spaceinvaders.gameobjects;

import com.javarush.engine.cell.Game;
import com.javarush.games.spaceinvaders.ShapeMatrix;

import java.util.ArrayList;
import java.util.List;

public class EnemyFleet {
    private static final int ROWS_COUNT = 3, COLUMNS_COUNT = 10, STEP = ShapeMatrix.ENEMY.length + 1;
    private List<EnemyShip> ships;

    public EnemyFleet() {
        createShips();
    }

    private void createShips() {
        ships = new ArrayList<>();
        for (int x = 0; x < COLUMNS_COUNT; x++)
            for (int y = 0; y < ROWS_COUNT; y++) {
                ships.add(new EnemyShip(x * STEP, y * STEP + 12));
            }
    }

    public void draw(Game game) {
        for (Ship ship :
              ships) {
            ship.draw(game);
        }
    }

    private double getLeftBorder() {
        double min = ships.get(0).x;
        for (EnemyShip enemyShip :
              ships) {
            if (enemyShip.x < min)
                min = enemyShip.x;
        }
        return min;
    }

    private double getRightBorder() {
        double max = ships.get(0).x + ships.get(0).width;
        for (EnemyShip enemyShip :
              ships) {
            if (enemyShip.x + enemyShip.width > max)
                max = enemyShip.x + enemyShip.width;
        }
        return max;
    }

}
