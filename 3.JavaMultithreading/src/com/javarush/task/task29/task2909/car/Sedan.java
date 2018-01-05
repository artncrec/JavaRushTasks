package com.javarush.task.task29.task2909.car;

/**
 * Created by admin on 03.07.2017.
 */
public class Sedan extends Car{
    private final int MAX_SEDAN_SPEED = 120;
    public Sedan(int numberOfPassengers) {
        super(1, numberOfPassengers);
    }

    @Override
    public int getMaxSpeed() {
        return MAX_SEDAN_SPEED;
    }
}
