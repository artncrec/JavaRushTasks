package com.javarush.task.task35.task3513;

public class MoveEfficiency implements Comparable<MoveEfficiency> {
    private int numberOfEmptyTiles, score;
    private Move move;

    public MoveEfficiency(int numberOfEmptyTiles, int score, Move move) {
        this.numberOfEmptyTiles = numberOfEmptyTiles;
        this.score = score;
        this.move = move;
    }

    public Move getMove() {
        return move;
    }

    @Override
    public int compareTo(MoveEfficiency moveEfficiency) {
        if (moveEfficiency.numberOfEmptyTiles != this.numberOfEmptyTiles)
            return this.numberOfEmptyTiles - moveEfficiency.numberOfEmptyTiles < 0 ? -1 : 1;
        else if (moveEfficiency.score != this.score)
            return this.score - moveEfficiency.score < 0 ? -1 : 1;
        else
            return 0;
    }
}
