package com.javarush.task.task35.task3513;

import java.util.*;

public class Model {
    private static final int FIELD_WIDTH = 4;
    private Tile[][] gameTiles;
    public int score, maxTile;
    private boolean isSaveNeeded = true;
    private Stack<Tile[][]> previousStates;
    private Stack<Integer> previousScores;

    public void randomMove(){
        int n = ((int)(Math.random()*100))%4;
        switch (n){
            case(0):left();break;
            case(1):right();break;
            case(2):up();break;
            case(3):down();
        }
    }

    public void autoMove(){
        PriorityQueue<MoveEfficiency> priorityQueue = new PriorityQueue(4, Collections.reverseOrder());
        Move left = new Move() {
            @Override
            public void move() {
                left();
            }
        };
        Move right = new Move() {
            @Override
            public void move() {
                right();
            }
        };
        Move up = new Move() {
            @Override
            public void move() {
                up();
            }
        };
        Move down = new Move() {
            @Override
            public void move() {
                down();
            }
        };
        priorityQueue.add(getMoveEfficiency(left));
        priorityQueue.add(getMoveEfficiency(right));
        priorityQueue.add(getMoveEfficiency(up));
        priorityQueue.add(getMoveEfficiency(down));
        priorityQueue.peek().getMove().move();
    }

    private boolean hasBoardChanged() {
        Tile[][] tiles = previousStates.peek();
        for (int i = 0; i < gameTiles.length; i++) {
            for (int j = 0; j < gameTiles[i].length; j++) {
                if (tiles[i][j].value != gameTiles[i][j].value)
                    return true;
            }
        }
        return false;
    }

    private MoveEfficiency getMoveEfficiency(Move move) {
        MoveEfficiency moveEfficiency;
        move.move();
        if (!hasBoardChanged())
            moveEfficiency = new MoveEfficiency(-1, 0, move);
        else
            moveEfficiency = new MoveEfficiency(getEmptyTiles().size(), score, move);
        rollback();
        return moveEfficiency;
    }

    private void saveState(Tile[][] tiles) {
        Tile[][] prevGame = new Tile[tiles.length][tiles.length];
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[i].length; j++) {
                prevGame[i][j] = new Tile(tiles[i][j].value);
            }
        }
        previousStates.push(prevGame);
        previousScores.push(score);
        isSaveNeeded = false;
    }

    public void rollback(){
        if (!previousStates.isEmpty() && !previousScores.isEmpty()) {
            gameTiles = previousStates.pop();
            score = previousScores.pop();
        }
    }

    public Model() {
        previousStates = new Stack<>();
        previousScores = new Stack<>();
        resetGameTiles();
    }

    public Tile[][] getGameTiles() {
        return gameTiles;
    }

    public boolean canMove() {
        if (!getEmptyTiles().isEmpty())
            return true;
        for (int i = 0; i < gameTiles.length; i++)
            for (int j = 0; j < gameTiles[i].length - 1; j++)
                if (gameTiles[i][j].value == gameTiles[i][j + 1].value)
                    return true;
        for (int j = 0; j < gameTiles[0].length; j++)
            for (int i = 0; i < gameTiles.length - 1; i++)
                if (gameTiles[i][j].value == gameTiles[i + 1][j].value)
                    return true;
        return false;
    }

    private List<Tile> getEmptyTiles() {
        List<Tile> result = new ArrayList<>();
        for (int i = 0; i < FIELD_WIDTH; i++) {
            for (int j = 0; j < FIELD_WIDTH; j++) {
                if (gameTiles[i][j].isEmpty())
                    result.add(gameTiles[i][j]);
            }
        }
        return result;
    }

    public void addTile() {
        List<Tile> empty = getEmptyTiles();
        int tile = (int) (Math.random() * empty.size());
        if (empty.size() > 0)
            empty.get(tile).value = Math.random() < 0.9 ? 2 : 4;
    }

    public void resetGameTiles() {
        gameTiles = new Tile[FIELD_WIDTH][FIELD_WIDTH];
        for (int i = 0; i < FIELD_WIDTH; i++) {
            for (int j = 0; j < FIELD_WIDTH; j++) {
                gameTiles[i][j] = new Tile();
            }
        }
        addTile();
        addTile();
        score = 0;
        maxTile = 2;
    }

    private boolean compressTiles(Tile[] tiles) {
        boolean isChanged = false;
        for (int i = tiles.length - 1; i >= 0; i--) {
            if (tiles[i].isEmpty()) {
                for (int j = i; j < tiles.length - 1; j++)
                    if (!tiles[j + 1].isEmpty()) {
                        int temp = tiles[j].value;
                        tiles[j].value = tiles[j + 1].value;
                        tiles[j + 1].value = temp;
                        if (!isChanged)
                            isChanged = true;
                    }
            }
        }
        return isChanged;
    }

    private boolean mergeTiles(Tile[] tiles) {
        boolean isChanged = false;
        for (int i = 0; i < tiles.length - 1; i++) {
            if (tiles[i].value == tiles[i + 1].value & !tiles[i].isEmpty() & !tiles[i + 1].isEmpty()) {
                tiles[i].value += tiles[i].value;
                tiles[i + 1].value = 0;
                if (tiles[i].value > maxTile)
                    maxTile = tiles[i].value;
                score += tiles[i].value;
                i++;
                isChanged = true;
            }
        }
        if (isChanged)
            compressTiles(tiles);
        return isChanged;
    }

    public void left() {
        if (isSaveNeeded)
            saveState(gameTiles);
        boolean addNew = false;
        for (Tile[] tiles : gameTiles) {
            if (compressTiles(tiles) | mergeTiles(tiles))
                addNew = true;
        }
        isSaveNeeded = true;
        if (addNew)
            addTile();
    }
    public void down(){
        saveState(gameTiles);
        turn();
        left();
        turn();
        turn();
        turn();
    }
    public void right(){
        saveState(gameTiles);
        turn();
        turn();
        left();
        turn();
        turn();
    }
    public void up(){
        saveState(gameTiles);
        turn();
        turn();
        turn();
        left();
        turn();
    }
    private void turn() {
        for (int i = 0; i < gameTiles.length/2; i++)
            for (int j = i; j < gameTiles[i].length - 1 - i; j++) {
                int k = gameTiles.length - 1;
                int temp = gameTiles[i][j].value;
                gameTiles[i][j].value = gameTiles[k - j][i].value;
                gameTiles[k - j][i].value = gameTiles[k - i][k - j].value;
                gameTiles[k - i][k - j].value = gameTiles[j][k - i].value;
                gameTiles[j][k - i].value = temp;
            }
    }
}
