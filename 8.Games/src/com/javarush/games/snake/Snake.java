package com.javarush.games.snake;

import com.javarush.engine.cell.*;

import java.util.ArrayList;
import java.util.List;

public class Snake {
    private List<GameObject> snakeParts = new ArrayList<>();
    private static final String HEAD_SIGN = "\uD83D\uDC7E";
    private static final String BODY_SIGN = "\u26AB";
    public boolean isAlive = true;
    private Direction direction = Direction.LEFT;

    public Snake(int x, int y) {
        GameObject first = new GameObject(x, y);
        GameObject second = new GameObject(x + 1, y);
        GameObject third = new GameObject(x + 2, y);
        snakeParts.add(first);
        snakeParts.add(second);
        snakeParts.add(third);
    }

    public void draw(Game game) {
        if (isAlive)
            game.setCellValueEx(snakeParts.get(0).x, snakeParts.get(0).y, Color.NONE, HEAD_SIGN, Color.GREEN, 75);
        else
            game.setCellValueEx(snakeParts.get(0).x, snakeParts.get(0).y, Color.NONE, HEAD_SIGN, Color.RED, 75);
        for (int i = 1; i < snakeParts.size(); i++) {
            if (isAlive)
                game.setCellValueEx(snakeParts.get(i).x, snakeParts.get(i).y, Color.NONE, BODY_SIGN, Color.GREEN, 75);
            else
                game.setCellValueEx(snakeParts.get(i).x, snakeParts.get(i).y, Color.NONE, BODY_SIGN, Color.RED, 75);
        }
    }

    public void setDirection(Direction direction) {
        int x1 = snakeParts.get(0).x;
        int x2 = snakeParts.get(1).x;
        int y1 = snakeParts.get(0).y;
        int y2 = snakeParts.get(1).y;
        if ((this.direction.equals(Direction.LEFT) || this.direction.equals(Direction.RIGHT)) && x1 == x2 ||
              (this.direction.equals(Direction.UP) || this.direction.equals(Direction.DOWN)) && y1 == y2)
            ;
        else
            this.direction = direction;
    }

    public void move(Apple apple) {
        GameObject head = createNewHead();
        if (head.x < 0 || head.y < 0 || head.x > SnakeGame.WIDTH - 1 || head.y > SnakeGame.HEIGHT - 1)
            isAlive = false;
        else {
            isAlive = !checkCollision(head);
            if (isAlive) {
                snakeParts.add(0, head);
                if (head.x == apple.x && head.y == apple.y) {
                    apple.isAlive = false;
                }
                else {
                    removeTail();
                }
            }
        }
    }

    public GameObject createNewHead() {
        int x = snakeParts.get(0).x;
        int y = snakeParts.get(0).y;
        if (direction.equals(Direction.LEFT))
            x--;
        else if (direction.equals(Direction.RIGHT))
            x++;
        else if (direction.equals(Direction.UP))
            y--;
        else if (direction.equals(Direction.DOWN))
            y++;
        return new GameObject(x, y);
    }

    public void removeTail() {
        snakeParts.remove(snakeParts.size() - 1);
    }

    public boolean checkCollision(GameObject gameObject) {
        for (GameObject object : snakeParts)
            if (object.x == gameObject.x && object.y == gameObject.y)
                return true;
        return false;
    }

    public int getLength(){
        return snakeParts.size();
    }
}
