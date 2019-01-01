package com.javarush.games.snake;

import com.javarush.engine.cell.*;

import java.util.ArrayList;
import java.util.List;

public class Snake {
    private List<GameObject> snakeParts = new ArrayList<>();
    private static final String HEAD_SIGN = "\uD83D\uDC7E";
    private static final String BODY_SIGN = "\u26AB";

    public Snake(int x, int y) {
        GameObject first = new GameObject(x,y);
        GameObject second = new GameObject(x+1,y);
        GameObject third = new GameObject(x+2,y);
        snakeParts.add(first);
        snakeParts.add(second);
        snakeParts.add(third);
    }

    public void draw(Game game) {
        game.setCellValue(snakeParts.get(0).x, snakeParts.get(0).y, HEAD_SIGN);
        for (int i = 1; i < snakeParts.size(); i++) {
            game.setCellValue(snakeParts.get(i).x, snakeParts.get(i).y, BODY_SIGN);
        }
    }
}
