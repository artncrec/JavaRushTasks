package com.javarush.games.racer.road;

import com.javarush.engine.cell.Game;
import com.javarush.games.racer.GameObject;
import com.javarush.games.racer.PlayerCar;
import com.javarush.games.racer.RacerGame;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class RoadManager {
    public static final int LEFT_BORDER = RacerGame.ROADSIDE_WIDTH, RIGHT_BORDER = RacerGame.WIDTH - LEFT_BORDER;
    private static final int FIRST_LANE_POSITION = 16, FOURTH_LANE_POSITION = 44, PLAYER_CAR_DISTANCE = 12;
    private List<RoadObject> items = new ArrayList<>();
    private int passedCarsCount = 0;

    public int getPassedCarsCount() {
        return passedCarsCount;
    }

    private void addRoadObject(RoadObjectType type, Game game) {
        int x = game.getRandomNumber(FIRST_LANE_POSITION, FOURTH_LANE_POSITION);
        int y = -1 * RoadObject.getHeight(type);
        RoadObject object = createRoadObject(type, x, y);
        if (object != null && isRoadSpaceFree(object))
            items.add(object);
    }

    private RoadObject createRoadObject(RoadObjectType type, int x, int y) {
        if (type == RoadObjectType.THORN)
            return new Thorn(x, y);
        else if (type == RoadObjectType.DRUNK_CAR)
            return new MovingCar(x, y);
        else return new Car(type, x, y);
    }

    public void draw(Game game) {
        for (RoadObject object : items)
            object.draw(game);
    }

    public void move(int boost) {
        for (RoadObject object : items) {
            object.move(boost + object.speed, items);
        }
        deletePassedItems();
    }

    private boolean isThornExists() {
        for (RoadObject object : items) {
            if (object.type == RoadObjectType.THORN)
                return true;
        }
        return false;
    }

    private boolean isMovingCarExists(){
        for (RoadObject object : items) {
            if (object.type == RoadObjectType.DRUNK_CAR)
                return true;
        }
        return false;
    }

    private void generateThorn(Game game) {
        if (!isThornExists() && game.getRandomNumber(100) < 10)
            addRoadObject(RoadObjectType.THORN, game);
    }

    private void generateRegularCar(Game game) {
        int carTypeNumber = game.getRandomNumber(4);
        if (game.getRandomNumber(100) < 30)
            addRoadObject(RoadObjectType.values()[carTypeNumber], game);
    }

    private void generateMovingCar(Game game){
        if (!isMovingCarExists() && game.getRandomNumber(100) < 10)
            addRoadObject(RoadObjectType.DRUNK_CAR, game);
    }

    public void generateNewRoadObjects(Game game) {
        generateThorn(game);
        generateRegularCar(game);
        generateMovingCar(game);
    }

    private void deletePassedItems() {
        RoadObject roadObject;
        for (Iterator<RoadObject> iterator = items.iterator(); iterator.hasNext(); ) {
            roadObject = iterator.next();
            if (roadObject.y >= RacerGame.HEIGHT) {
                if (roadObject.type != RoadObjectType.THORN)
                    passedCarsCount++;
                iterator.remove();
            }
        }
    }

    public boolean checkCrush(PlayerCar playerCar) {
        for (GameObject gameObject : items) {
            if (gameObject.isCollision(playerCar))
                return true;
        }
        return false;
    }

    private boolean isRoadSpaceFree(RoadObject object) {
        for (RoadObject gameObject : items) {
            if (gameObject.isCollisionWithDistance(object, PLAYER_CAR_DISTANCE))
                return false;
        }
        return true;
    }
}
