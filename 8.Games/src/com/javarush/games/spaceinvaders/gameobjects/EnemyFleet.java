package com.javarush.games.spaceinvaders.gameobjects;

import com.javarush.engine.cell.Game;
import com.javarush.games.spaceinvaders.Direction;
import com.javarush.games.spaceinvaders.ShapeMatrix;
import com.javarush.games.spaceinvaders.SpaceInvadersGame;

import java.util.ArrayList;
import java.util.List;

public class EnemyFleet {
    private static final int ROWS_COUNT = 3, COLUMNS_COUNT = 10, STEP = ShapeMatrix.ENEMY.length + 1;
    private List<EnemyShip> ships;
    private Direction direction  = Direction.RIGHT;

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

    private double getSpeed() {
        double min = 2.0;
        double speed = 3.0 / ships.size();
        return min < speed ? min : speed;
    }

    public void move() {
        if (ships.size() > 0) {
            boolean hasChanged = false;
            if (direction == Direction.LEFT && getLeftBorder() < 0) {
                direction = Direction.RIGHT;
                hasChanged = true;
            }
            if (direction == Direction.RIGHT && getRightBorder() > SpaceInvadersGame.WIDTH) {
                direction = Direction.LEFT;
                hasChanged = true;
            }
            for (EnemyShip enemyShip :
                  ships) {
                if (hasChanged)
                    enemyShip.move(Direction.DOWN, getSpeed());
                else
                    enemyShip.move(direction, getSpeed());
            }
        }
    }

    public Bullet fire(Game game) {
        if (ships.isEmpty())
            return null;
        if (game.getRandomNumber(100 / SpaceInvadersGame.COMPLEXITY) > 0)
            return null;
        return ships.get(game.getRandomNumber(ships.size())).fire();
    }
}
