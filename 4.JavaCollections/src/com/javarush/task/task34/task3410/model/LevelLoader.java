package com.javarush.task.task34.task3410.model;

import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

public class LevelLoader {
    private Path levels;

    public LevelLoader(Path levels) {
        this.levels = levels;
    }

    public GameObjects getLevel(int level) {
        Set<Wall> walls = new HashSet<>();
        walls.add(new Wall(10, 10));
        walls.add(new Wall(30, 10));
        walls.add(new Wall(50, 10));

        Set<Box> boxes = new HashSet<>();
        boxes.add(new Box(30, 30));

        Set<Home> homes = new HashSet<>();
        homes.add(new Home(10, 30));

        Player player = new Player(50, 30);

        return new GameObjects(walls, boxes, homes, player);
    }
}
