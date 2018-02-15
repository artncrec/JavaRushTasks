package com.javarush.task.task14.task1408;

public class BelarusianHen extends Hen {
    int eggs = 50;

    int getCountOfEggsPerMonth() {
        return eggs;
    }

    String getDescription() {
        return super.getDescription() + " Моя страна - " + Country.BELARUS + ". Я несу " + getCountOfEggsPerMonth() + " яиц в месяц.";
    }
}
