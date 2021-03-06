package com.javarush.games.spaceinvaders.gameobjects;

import com.javarush.engine.cell.Game;
import com.javarush.games.spaceinvaders.Direction;
import com.javarush.games.spaceinvaders.ShapeMatrix;
import com.javarush.games.spaceinvaders.SpaceInvadersGame;

import java.util.ArrayList;
import java.util.Iterator;
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
            for (int y = 0; y < ROWS_COUNT; y++)
                ships.add(new EnemyShip(x * STEP, y * STEP + 12));
        ships.add(new Boss(STEP * COLUMNS_COUNT / 2 - ShapeMatrix.BOSS_ANIMATION_FIRST.length / 2 - 1, 5));
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
        double max = 1.0;
        double speed = 3.0 / ships.size();
        return max < speed ? max : speed;
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
            for (EnemyShip enemyShip : ships) {
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

    public int verifyHit(List<Bullet> bullets) {
        if (bullets.size() == 0)
            return 0;
        int sum = 0;
        for (EnemyShip ship : ships) {
            for (Bullet bullet : bullets) {
                if (ship.isAlive && bullet.isAlive && ship.isCollision(bullet)) {
                    bullet.kill();
                    sum += ship.score;
                    ship.kill();
                }
            }
        }
        return sum;
    }

    public void deleteHiddenShips(){
        Iterator<EnemyShip> shipIterator = ships.iterator();
        while (shipIterator.hasNext()) {
            Ship ship = shipIterator.next();
            if (!ship.isVisible())
                shipIterator.remove();
        }
    }

    public double getBottomBorder(){
        double max = 0;
        for (Ship ship :
              ships) {
            if (max < ship.y + ship.height)
                max = ship.y + ship.height;
        }
        return max;
    }

    public int getShipsCount(){
        return ships.size();
    }
}
