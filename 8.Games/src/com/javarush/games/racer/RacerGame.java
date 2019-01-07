package com.javarush.games.racer;

import com.javarush.engine.cell.*;

public class RacerGame extends Game {
    public static final int WIDTH = 64, HEIGHT = 64, CENTER_X = WIDTH / 2, ROADSIDE_WIDTH = 14;
    private RoadMarking roadMarking;
    private PlayerCar player;

    @Override
    public void initialize() {
        showGrid(false);
        setScreenSize(WIDTH, HEIGHT);
        createGame();
    }

    private void createGame(){
        roadMarking = new RoadMarking();
        player = new PlayerCar();
        setTurnTimer(40);
        drawScene();
    }

    private void drawScene(){
        drawField();
        roadMarking.draw(this);
        player.draw(this);
    }

    private void drawField(){
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

    private void moveAll(){
        roadMarking.move(player.speed);
        player.move();
    }

    @Override
    public void onKeyPress(Key key) {
        if (key.equals(Key.RIGHT))
            player.setDirection(Direction.RIGHT);
        else if (key.equals(Key.LEFT))
            player.setDirection(Direction.LEFT);
    }

    @Override
    public void onKeyReleased(Key key) {
        if (key.equals(Key.LEFT) && player.getDirection().equals(Direction.LEFT) ||
              key.equals(Key.RIGHT) && player.getDirection().equals(Direction.RIGHT))
            player.setDirection(Direction.NONE);
    }

    @Override
    public void onTurn(int step) {
        moveAll();
        drawScene();
    }
}
