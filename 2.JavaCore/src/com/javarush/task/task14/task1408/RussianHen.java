package com.javarush.task.task14.task1408;

public class RussianHen extends Hen {
    int eggs = 30;

    int getCountOfEggsPerMonth() {
        return eggs;
    }

    String getDescription() {
        return super.getDescription() + " Моя страна - " + Country.RUSSIA + ". Я несу " + getCountOfEggsPerMonth() + " яиц в месяц.";
    }
}
