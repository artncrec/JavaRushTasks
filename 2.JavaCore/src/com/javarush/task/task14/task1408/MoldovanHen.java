package com.javarush.task.task14.task1408;

public class MoldovanHen extends Hen {
    int eggs = 10;

    int getCountOfEggsPerMonth() {
        return eggs;
    }

    String getDescription() {
        return super.getDescription() + " Моя страна - " + Country.MOLDOVA + ". Я несу " + getCountOfEggsPerMonth() + " яиц в месяц.";
    }
}
