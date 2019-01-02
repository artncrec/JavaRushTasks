package com.javarush.games.snake;

import com.javarush.engine.cell.*;

public class SnakeGame extends Game {
    public static final int WIDTH = 15;
    public static final int HEIGHT = 15;
    private int turnDelay;

    private Snake snake;
    private Apple apple;

    @Override
    public void initialize() {
        setScreenSize(WIDTH, HEIGHT);
        createGame();
    }

    private void createGame() {
        turnDelay = 300;
        setTurnTimer(turnDelay);
        snake = new Snake(WIDTH / 2, HEIGHT / 2);
        createNewApple();
        drawScene();
    }

    private void drawScene(){
        for (int i = 0; i < getScreenWidth(); i++) {
            for (int j = 0; j < getScreenHeight(); j++) {
                setCellValueEx(i, j, Color.BEIGE, "");
            }
        }
        snake.draw(this);
        apple.draw(this);
    }

    @Override
    public void onTurn(int step) {
        snake.move(apple);
        if (!apple.isAlive)
            createNewApple();
        drawScene();
    }

    @Override
    public void onKeyPress(Key key) {
        if (key.equals(Key.LEFT))
            snake.setDirection(Direction.LEFT);
        else if (key.equals(Key.UP))
            snake.setDirection(Direction.UP);
        else if (key.equals(Key.RIGHT))
            snake.setDirection(Direction.RIGHT);
        else if (key.equals(Key.DOWN))
            snake.setDirection(Direction.DOWN);
    }

    private void createNewApple(){
        int w = getRandomNumber(14);
        int h = getRandomNumber(14);
        apple = new Apple(w,h);
    }
}
