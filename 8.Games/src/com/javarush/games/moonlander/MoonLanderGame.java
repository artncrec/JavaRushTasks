package com.javarush.games.moonlander;

import com.javarush.engine.cell.*;

import java.security.spec.KeySpec;

public class MoonLanderGame extends Game {
    public static final int WIDTH = 64, HEIGHT = 64;
    private int score;
    private Rocket rocket;
    private GameObject landscape, platform;
    private boolean isUpPressed, isLeftPressed, isRightPressed, isGameStopped;

    @Override
    public void initialize() {
        setScreenSize(WIDTH, HEIGHT);
        showGrid(false);
        createGame();
    }

    @Override
    public void setCellColor(int x, int y, Color color) {
        if (x < WIDTH && x >= 0 && y < HEIGHT && y >= 0)
            super.setCellColor(x, y, color);
    }

    @Override
    public void onTurn(int step) {
        rocket.move(isUpPressed, isLeftPressed, isRightPressed);
        check();
        drawScene();
        if (score > 0)
            score--;
        setScore(score);
    }

    private void createGame() {
        setTurnTimer(50);
        isGameStopped = false;
        isLeftPressed = false;
        isRightPressed = false;
        isUpPressed = false;
        score = 1000;
        createGameObjects();
        drawScene();
    }

    private void drawScene() {
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                setCellColor(j, i, Color.DARKBLUE);
            }
        }
        rocket.draw(this);
        landscape.draw(this);
    }

    private void createGameObjects() {
        rocket = new Rocket(WIDTH / 2, 0);
        landscape = new GameObject(0, 25, ShapeMatrix.LANDSCAPE);
        platform = new GameObject(23, MoonLanderGame.HEIGHT - 1, ShapeMatrix.PLATFORM);
    }

    @Override
    public void onKeyReleased(Key key) {
        if (key == Key.UP)
            isUpPressed = false;
        if (key == Key.LEFT) {
            isLeftPressed = false;
        }
        if (key == Key.RIGHT) {
            isRightPressed = false;
        }
    }

    @Override
    public void onKeyPress(Key key) {
        if (key == Key.UP)
            isUpPressed = true;
        if (key == Key.LEFT) {
            isLeftPressed = true;
            isRightPressed = false;
        }
        if (key == Key.RIGHT) {
            isRightPressed = true;
            isLeftPressed = false;
        }
        if (key == Key.SPACE && isGameStopped)
            createGame();
    }

    private void check() {
        if (rocket.isCollision(landscape) || rocket.isCollision(platform) && !rocket.isStopped())
            gameOver();
        if (rocket.isCollision(platform) && rocket.isStopped())
            win();
    }

    private void win() {
        rocket.land();
        isGameStopped = true;
        showMessageDialog(Color.NONE, "Rocket landed!", Color.BISQUE, 50);
        stopTurnTimer();
    }

    private void gameOver() {
        rocket.crash();
        isGameStopped = true;
        showMessageDialog(Color.NONE, "Rocket crashed!", Color.INDIANRED, 50);
        stopTurnTimer();
        score = 0;
    }
}
