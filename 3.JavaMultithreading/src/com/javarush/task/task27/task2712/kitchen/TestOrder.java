package com.javarush.task.task27.task2712.kitchen;

import com.javarush.task.task27.task2712.Tablet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class TestOrder extends Order {
    public TestOrder(Tablet tablet) throws IOException {
        super(tablet);
    }

    @Override
    protected void initDishes() {
        int count = (int) ((Math.random() + 0.35) * 3);
        Random dishRandNumber = new Random();
        dishes = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            dishes.add(Dish.values()[dishRandNumber.nextInt(Dish.values().length)]);
        }
    }
}
