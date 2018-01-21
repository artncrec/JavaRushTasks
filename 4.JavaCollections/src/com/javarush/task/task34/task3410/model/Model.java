package com.javarush.task.task34.task3410.model;

import com.javarush.task.task34.task3410.controller.Controller;
import com.javarush.task.task34.task3410.controller.EventListener;

import java.nio.file.Paths;

public class Model {
    public static final int FIELD_CELL_SIZE = 20;
    private EventListener eventListener;
    private GameObjects gameObjects;
    private int currentLevel = 1;
    private String path = "4.JavaCollections/src/" + Controller.class.getPackage().getName().substring(0, Controller.class.getPackage().getName().lastIndexOf('.')).replace('.', '/');
    private LevelLoader levelLoader = new LevelLoader(Paths.get(path + "/res/levels.txt"));

    public void setEventListener(EventListener eventListener) {
        this.eventListener = eventListener;
    }

    public GameObjects getGameObjects() {
        return gameObjects;
    }

    public void restartLevel(int level) {
        gameObjects = levelLoader.getLevel(level);
        currentLevel = level;
    }

    public void restart() {
        restartLevel(currentLevel);
    }

    public void startNextLevel() {
        currentLevel++;
        restart();
    }

    public void move(Direction direction) {
        Player player = gameObjects.getPlayer();
        if (checkWallCollision(player, direction))
            return;
        if (checkBoxCollisionAndMoveIfAvaliable(direction))
            return;
        switch (direction) {
            case UP: player.move(0, -Model.FIELD_CELL_SIZE); break;
            case DOWN: player.move(0, Model.FIELD_CELL_SIZE); break;
            case LEFT: player.move(-Model.FIELD_CELL_SIZE, 0); break;
            case RIGHT: player.move(Model.FIELD_CELL_SIZE, 0);
        }
        checkCompletion();
    }

    public void checkCompletion(){
        for (Box box : gameObjects.getBoxes()){
            boolean check = false;
            for (Home home : gameObjects.getHomes())
                if (box.getX() == home.getX() && box.getY() == home.getY()) {
                    check = true;
                    break;
                }
            if (!check) return;
        }
        eventListener.levelCompleted(currentLevel);
    }

    public boolean checkWallCollision(CollisionObject gameObject, Direction direction) {
        for (GameObject wall : gameObjects.getWalls())
            if (gameObject.isCollision(wall, direction))
                return true;
        return false;
    }

    public boolean checkBoxCollisionAndMoveIfAvaliable(Direction direction) {
        Player player = gameObjects.getPlayer();
        Box nextBox = null;
        for (Box box : gameObjects.getBoxes())
            if (player.isCollision(box, direction)) {
                nextBox = box;
                break;
            }
        if (nextBox != null) {
            if (checkWallCollision(nextBox, direction))
                return true;
            for (Box box : gameObjects.getBoxes())
                if (nextBox.isCollision(box, direction))
                    return true;
            switch (direction) {
                case UP: nextBox.move(0, -Model.FIELD_CELL_SIZE); break;
                case DOWN: nextBox.move(0, Model.FIELD_CELL_SIZE); break;
                case LEFT: nextBox.move(-Model.FIELD_CELL_SIZE, 0); break;
                case RIGHT: nextBox.move(Model.FIELD_CELL_SIZE, 0);
            }
        }
        return false;
    }
}