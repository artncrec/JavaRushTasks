package com.javarush.task.task14.task1417;

public class Hrivna extends Money {
    public Hrivna(int i) {
        super(i);
    }

    @Override
    public String getCurrencyName() {
        return "HRN";
    }
}
