package com.javarush.games.spaceinvaders;

import com.javarush.engine.cell.*;
import com.javarush.games.spaceinvaders.gameobjects.Bullet;
import com.javarush.games.spaceinvaders.gameobjects.EnemyFleet;
import com.javarush.games.spaceinvaders.gameobjects.PlayerShip;
import com.javarush.games.spaceinvaders.gameobjects.Star;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SpaceInvadersGame extends Game {
    public static final int WIDTH = 64, HEIGHT = 64;
    private List<Star> stars;
    private EnemyFleet enemyFleet;
    public static final int COMPLEXITY = 5;
    private List<Bullet> enemyBullets;
    private List<Bullet> playerBullets;
    private PlayerShip playerShip;
    private boolean isGameStopped;
    private int animationsCount;
    private static final int PLAYER_BULLETS_MAX = 1;
    private int score;

    @Override
    public void initialize() {
        setScreenSize(WIDTH, HEIGHT);
        createGame();
    }

    private void createGame() {
        setTurnTimer(40);
        createStars();
        isGameStopped = false;
        animationsCount = 0;
        score = 0;
        enemyFleet = new EnemyFleet();
        enemyBullets = new ArrayList<>();
        playerBullets = new ArrayList<>();
        playerShip = new PlayerShip();
        drawScene();
    }

    private void drawScene() {
        drawField();
        for (Bullet bullet :
              enemyBullets) {
            bullet.draw(this);
        }
        for (Bullet bullet :
              playerBullets) {
            bullet.draw(this);
        }
        playerShip.draw(this);
        enemyFleet.draw(this);
    }

    private void drawField() {
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                setCellValueEx(i, j, Color.BLACK, "");
            }
        }
        for (Star star :
              stars) {
            star.draw(this);
        }
    }

    private void createStars() {
        stars = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            stars.add(new Star(getRandomNumber(WIDTH), getRandomNumber(HEIGHT)));
        }
    }

    @Override
    public void onTurn(int step) {
        moveSpaceObjects();
        check();
        Bullet bullet = enemyFleet.fire(this);
        if (bullet != null)
            enemyBullets.add(bullet);
        setScore(score);
        drawScene();
    }

    private void moveSpaceObjects() {
        enemyFleet.move();
        playerShip.move();
        for (Bullet bullet :
              enemyBullets) {
            bullet.move();
        }
        for (Bullet bullet :
              playerBullets) {
            bullet.move();
        }
    }

    private void removeDeadBullets() {
        Iterator<Bullet> bulletIterator = enemyBullets.iterator();
        while (bulletIterator.hasNext()) {
            Bullet bullet = bulletIterator.next();
            if (!bullet.isAlive || bullet.y >= HEIGHT - 1)
                bulletIterator.remove();
        }
        bulletIterator = playerBullets.iterator();
        while (bulletIterator.hasNext()) {
            Bullet bullet = bulletIterator.next();
            if (!bullet.isAlive || bullet.y + bullet.height < 0)
                bulletIterator.remove();
        }
    }

    private void check() {
        playerShip.verifyHit(enemyBullets);
        score += enemyFleet.verifyHit(playerBullets);
        enemyFleet.deleteHiddenShips();
        if (enemyFleet.getShipsCount() == 0) {
            playerShip.win();
            stopGameWithDelay();
        }
        if (enemyFleet.getBottomBorder() >= playerShip.y)
            playerShip.kill();
        removeDeadBullets();
        if (!playerShip.isAlive)
            stopGameWithDelay();
    }

    private void stopGame(boolean isWin) {
        isGameStopped = true;
        stopTurnTimer();
        if (isWin)
            showMessageDialog(Color.BLACK, "win", Color.GREEN, 75);
        else showMessageDialog(Color.BLACK, "lose", Color.RED, 75);
    }

    private void stopGameWithDelay() {
        animationsCount++;
        if (animationsCount >= 10)
            stopGame(playerShip.isAlive);
    }

    @Override
    public void onKeyPress(Key key) {
        Bullet bullet;
        if (key == Key.SPACE && isGameStopped)
            createGame();
        else if (key == Key.SPACE) {
            bullet = playerShip.fire();
            if (bullet != null && playerBullets.size() < PLAYER_BULLETS_MAX)
                playerBullets.add(bullet);
        }
        if (key == Key.LEFT)
            playerShip.setDirection(Direction.LEFT);
        if (key == Key.RIGHT)
            playerShip.setDirection(Direction.RIGHT);
    }

    @Override
    public void onKeyReleased(Key key) {
        if (key == Key.LEFT && playerShip.getDirection() == Direction.LEFT)
            playerShip.setDirection(Direction.UP);
        if (key == Key.RIGHT && playerShip.getDirection() == Direction.RIGHT)
            playerShip.setDirection(Direction.UP);
    }

    @Override
    public void setCellValueEx(int x, int y, Color cellColor, String value) {
        if (x > 0 && y > 0 && x < WIDTH && y < HEIGHT)
            super.setCellValueEx(x, y, cellColor, value);
    }
}
