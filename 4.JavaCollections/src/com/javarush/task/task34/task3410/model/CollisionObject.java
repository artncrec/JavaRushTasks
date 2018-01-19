package com.javarush.task.task34.task3410.model;

public abstract class CollisionObject extends GameObject{

    public CollisionObject(int x, int y) {
        super(x, y);
    }

    public boolean isCollision (GameObject gameObject, Direction direction){
        switch (direction){
            case UP: return getY() - Model.FIELD_CELL_SIZE == gameObject.getY() && getX() == gameObject.getX();
            case DOWN: return getY() + Model.FIELD_CELL_SIZE == gameObject.getY() && getX() == gameObject.getX();
            case LEFT: return getY() - Model.FIELD_CELL_SIZE == gameObject.getX() && getY() == gameObject.getY();
            case RIGHT: return getX() + Model.FIELD_CELL_SIZE == gameObject.getX() && getY() == gameObject.getY();
        }
        return false;
    }
}
