package com.javarush.task.task34.task3410.model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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
        Set<Box> boxes = new HashSet<>();
        Set<Home> homes = new HashSet<>();
        Player player = null;

        if (level > 60)
            level %= 60;
        int x = Model.FIELD_CELL_SIZE / 2;
        int y = Model.FIELD_CELL_SIZE / 2;
        int xc = 0, yc = 0;

        try {
            BufferedReader reader = new BufferedReader(new FileReader(levels.toFile()));
            String data;
            while ((data = reader.readLine()) != null)
                if (data.startsWith("Maze:")) {
                    if (Integer.parseInt(data.split(" ")[1]) == level) {
                        boolean start = false;
                        while ((data = reader.readLine()) != null) {
                            if (data.length() == 0) {
                                if (!start) {
                                    start = true;
                                    continue;
                                }
                                else
                                    break;
                            }
                            else {
                                if (data.startsWith("Size X:")) {
                                    xc = (520 - Integer.parseInt(data.split(" ")[2]) * Model.FIELD_CELL_SIZE);
                                    if (xc != 0)
                                        xc /= 2;
                                }
                                if (data.startsWith("Size Y:")) {
                                    yc = (520 - Integer.parseInt(data.split(" ")[2]) * Model.FIELD_CELL_SIZE);
                                    if (yc != 0)
                                        yc /= 2;
                                    y += yc;
                                }
                            }
                            if (start) {
                                x = xc + Model.FIELD_CELL_SIZE / 2;
                                for (char c : data.toCharArray()) {
                                    switch (c) {
                                        case 'X':
                                            walls.add(new Wall(x, y));
                                            break;
                                        case '*':
                                            boxes.add(new Box(x, y));
                                            break;
                                        case '.':
                                            homes.add(new Home(x, y));
                                            break;
                                        case '&':
                                            homes.add(new Home(x, y));
                                            boxes.add(new Box(x, y));
                                            break;
                                        case '@':
                                            player = new Player(x, y);
                                    }
                                    x += Model.FIELD_CELL_SIZE;
                                }
                                y += Model.FIELD_CELL_SIZE;
                            }
                        }
                    }
                }
        } catch (IOException e) {
        }
        return new GameObjects(walls, boxes, homes, player);
    }
}
