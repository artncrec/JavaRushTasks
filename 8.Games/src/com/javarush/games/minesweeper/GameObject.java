package com.javarush.games.minesweeper;

public class GameObject {
    public int x, y, countMineNeighbors;
    public boolean isMine;

    public GameObject(int x, int y, boolean mine) {
        this.x = x;
        this.y = y;
        isMine = mine;
    }
}
