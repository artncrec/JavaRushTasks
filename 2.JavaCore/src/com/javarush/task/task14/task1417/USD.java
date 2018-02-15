package com.javarush.task.task14.task1417;

public class USD extends Money {
    public USD(int i) {
        super(i);
    }

    @Override
    public String getCurrencyName() {
        return "USD";
    }
}
