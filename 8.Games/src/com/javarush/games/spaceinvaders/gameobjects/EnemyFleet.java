package com.javarush.games.spaceinvaders.gameobjects;

import com.javarush.games.spaceinvaders.ShapeMatrix;

import java.util.ArrayList;
import java.util.List;

public class EnemyFleet {
    private static final int ROWS_COUNT = 3, COLUMNS_COUNT = 10, STEP = ShapeMatrix.ENEMY.length+1;
    private List<EnemyShip> ships;

    private void createShips(){
        ships = new ArrayList<>();
    }
}
