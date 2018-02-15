package com.javarush.task.task14.task1417;

public class Ruble extends Money {
    public Ruble(int i) {
        super(i);
    }

    @Override
    public String getCurrencyName() {
        return "RUB";
    }
}
