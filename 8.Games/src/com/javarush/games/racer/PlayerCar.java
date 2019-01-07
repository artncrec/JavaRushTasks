package com.javarush.games.racer;

import com.javarush.games.racer.road.RoadManager;

public class PlayerCar extends GameObject {
    private static final int playerCarHeight = ShapeMatrix.PLAYER.length;
    public int speed = 1;
    private Direction direction = Direction.NONE;

    public PlayerCar() {
        super(RacerGame.WIDTH / 2 + 2, RacerGame.HEIGHT - playerCarHeight - 1, ShapeMatrix.PLAYER);
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Direction getDirection() {
        return direction;
    }

    public void move() {
        if (direction.equals(Direction.RIGHT)) {
            x++;
        }
        else if (direction.equals(Direction.LEFT)) {
            x--;
        }
        if (x < RoadManager.LEFT_BORDER)
            x = RoadManager.LEFT_BORDER;
        else if (x > RoadManager.RIGHT_BORDER - width)
            x = RoadManager.RIGHT_BORDER - width;
    }
}
