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
    public static final int WIDTH=64, HEIGHT=64;
    private List<Star> stars;
    private EnemyFleet enemyFleet;
    public static final int COMPLEXITY = 5;
    private List<Bullet> enemyBullets;
    private PlayerShip playerShip;
    private boolean isGameStopped;
    private int animationsCount;

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
        enemyFleet = new EnemyFleet();
        enemyBullets = new ArrayList<>();
        playerShip = new PlayerShip();
        drawScene();
    }

    private void drawScene(){
        drawField();
        for (Bullet bullet :
              enemyBullets) {
            bullet.draw(this);
        }
        playerShip.draw(this);
        enemyFleet.draw(this);
    }

    private void drawField(){
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

    private void createStars(){
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
        drawScene();
    }

    private void moveSpaceObjects(){
        enemyFleet.move();
        playerShip.move();
        for (Bullet bullet :
              enemyBullets) {
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
    }

    private void check(){
        playerShip.verifyHit(enemyBullets);
        removeDeadBullets();
        if (!playerShip.isAlive)
            stopGameWithDelay();
    }

    private void stopGame(boolean isWin){
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
        if (key == Key.SPACE && isGameStopped)
            createGame();
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
}
