package com.javarush.games.racer;

import com.javarush.engine.cell.*;
import com.javarush.games.racer.road.RoadManager;

public class RacerGame extends Game {
    public static final int WIDTH = 64, HEIGHT = 64, CENTER_X = WIDTH / 2, ROADSIDE_WIDTH = 14;
    private static final int RACE_GOAL_CARS_COUNT = 40;
    private boolean isGameStopped;
    private RoadMarking roadMarking;
    private PlayerCar player;
    private RoadManager roadManager;
    private FinishLine finishLine;
    private ProgressBar progressBar;
    private int score;

    @Override
    public void initialize() {
        showGrid(false);
        setScreenSize(WIDTH, HEIGHT);
        createGame();
    }

    private void createGame() {
        isGameStopped = false;
        score = 3500;
        roadMarking = new RoadMarking();
        player = new PlayerCar();
        roadManager = new RoadManager();
        finishLine = new FinishLine();
        progressBar = new ProgressBar(RACE_GOAL_CARS_COUNT);
        setTurnTimer(30);
        drawScene();
    }

    private void drawScene() {
        drawField();
        roadMarking.draw(this);
        roadManager.draw(this);
        finishLine.draw(this);
        progressBar.draw(this);
        player.draw(this);
    }

    private void drawField() {
        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                if (x == CENTER_X)
                    setCellColor(CENTER_X, y, Color.KHAKI);
                else if (x >= ROADSIDE_WIDTH && x < WIDTH - ROADSIDE_WIDTH)
                    setCellColor(x, y, Color.DIMGREY);
                else setCellColor(x, y, Color.DARKGREEN);
            }
        }
    }

    @Override
    public void setCellColor(int x, int y, Color color) {
        if (x >= 0 && x < WIDTH && y >= 0 && y < HEIGHT)
            super.setCellColor(x, y, color);
    }

    private void moveAll() {
        roadManager.move(player.speed);
        roadMarking.move(player.speed);
        finishLine.move(player.speed);
        progressBar.move(roadManager.getPassedCarsCount());
        player.move();
    }

    @Override
    public void onKeyPress(Key key) {
        if (key == Key.SPACE && isGameStopped)
            createGame();
        if (key == Key.UP)
            player.speed = 2;
        if (key.equals(Key.RIGHT))
            player.setDirection(Direction.RIGHT);
        else if (key.equals(Key.LEFT))
            player.setDirection(Direction.LEFT);
    }

    @Override
    public void onKeyReleased(Key key) {
        if (key == Key.UP)
            player.speed = 1;
        if (key.equals(Key.LEFT) && player.getDirection().equals(Direction.LEFT) ||
              key.equals(Key.RIGHT) && player.getDirection().equals(Direction.RIGHT))
            player.setDirection(Direction.NONE);
    }

    @Override
    public void onTurn(int step) {
        if (roadManager.checkCrush(player)) {
            gameOver();
        } else {
            if (roadManager.getPassedCarsCount() >= RACE_GOAL_CARS_COUNT)
                finishLine.show();
            if (finishLine.isCrossed(player))
                win();
            else {
                roadManager.generateNewRoadObjects(this);
                moveAll();
                score-=5;
                setScore(score);
            }
        }
        drawScene();
    }

    private void gameOver() {
        isGameStopped = true;
        showMessageDialog(Color.RED, "GAME OVER!", Color.WHITE, 75);
        stopTurnTimer();
        player.stop();
    }

    private void win(){
        isGameStopped = true;
        showMessageDialog(Color.WHEAT, "YOU WIN!", Color.AZURE, 75);
        stopTurnTimer();
    }
}
