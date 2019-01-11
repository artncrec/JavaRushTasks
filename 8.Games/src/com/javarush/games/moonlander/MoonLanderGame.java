package com.javarush.games.moonlander;

import com.javarush.engine.cell.*;

public class MoonLanderGame extends Game {
    public static final int WIDTH = 64, HEIGHT = 64;

    private Rocket rocket;
    private GameObject landscape;
    private boolean isUpPressed, isLeftPressed, isRightPressed;

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
        rocket.move();
        drawScene();
    }

    private void createGame() {
        setTurnTimer(50);
        isLeftPressed = false;
        isRightPressed = false;
        isUpPressed = false;
        createGameObjects();
        drawScene();
    }

    private void drawScene(){
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                setCellColor(j, i, Color.DARKBLUE);
            }
        }
        rocket.draw(this);
        landscape.draw(this);
    }

    private void createGameObjects(){
        rocket = new Rocket(WIDTH/2, 0);
        landscape = new GameObject(0, 25, ShapeMatrix.LANDSCAPE);

    }
}
