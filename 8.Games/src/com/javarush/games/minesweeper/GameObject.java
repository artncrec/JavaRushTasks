package com.javarush.games.minesweeper;

public class GameObject {
    public int x, y, countMineNeighbors = 0;
    public boolean isMine, isOpen, isFlag;

    public GameObject(int x, int y, boolean mine) {
        this.x = x;
        this.y = y;
        isMine = mine;
    }
}
