package com.javarush.games.racer.road;

import com.javarush.engine.cell.Game;
import com.javarush.games.racer.RacerGame;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class RoadManager {
    public static final int LEFT_BORDER = RacerGame.ROADSIDE_WIDTH, RIGHT_BORDER = RacerGame.WIDTH - LEFT_BORDER;
    private static final int FIRST_LANE_POSITION = 16, FOURTH_LANE_POSITION = 44;
    private List<RoadObject> items = new ArrayList<>();

    private void addRoadObject(RoadObjectType type, Game game) {
        int x = game.getRandomNumber(FIRST_LANE_POSITION, FOURTH_LANE_POSITION);
        int y = -1 * RoadObject.getHeight(type);
        RoadObject object = createRoadObject(type, x, y);
        if (object != null)
            items.add(object);
    }

    private RoadObject createRoadObject(RoadObjectType type, int x, int y) {
        if (type == RoadObjectType.THORN)
            return new Thorn(x, y);
        else return null;
    }

    public void draw(Game game) {
        for (RoadObject object : items)
            object.draw(game);
    }

    public void move(int boost) {
        for (RoadObject object : items) {
            object.move(boost + object.speed);
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

    private void generateThorn(Game game) {
        if (!isThornExists() && game.getRandomNumber(100) < 10)
            addRoadObject(RoadObjectType.THORN, game);
    }

    public void generateNewRoadObjects(Game game) {
        generateThorn(game);
    }

    private void deletePassedItems() {
        for (Iterator<RoadObject> iterator = items.iterator(); iterator.hasNext(); ) {
            if (iterator.next().y >= RacerGame.HEIGHT)
                iterator.remove();
        }
    }
}
