package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.ad.Advertisement;
import com.javarush.task.task27.task2712.ad.StatisticAdvertisementManager;
import com.javarush.task.task27.task2712.statistic.StatisticManager;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class DirectorTablet {
    public void printAdvertisementProfit(){
        Map<String, Double> map = StatisticManager.getInstance().profit();
        double sum = 0;
        for (Map.Entry<String, Double> entry : map.entrySet())
        {
            ConsoleHelper.writeMessage(String.format("%s - %.2f", entry.getKey(), entry.getValue()));
            sum += entry.getValue();
        }
        ConsoleHelper.writeMessage(String.format("Total - %.2f", sum));
    }

    public void printCookWorkloading(){
        Map<String, Map<String, Integer>> map = StatisticManager.getInstance().cookTime();
        for (Map.Entry<String, Map<String, Integer>> entry : map.entrySet())
        {
            ConsoleHelper.writeMessage(entry.getKey());
            for (Map.Entry<String, Integer> integerEntry : entry.getValue().entrySet())
                ConsoleHelper.writeMessage(String.format("%s - %d min", integerEntry.getKey(), integerEntry.getValue()));
            ConsoleHelper.writeMessage("");
        }
    }

    public void printActiveVideoSet(){
        for (Advertisement ad : StatisticAdvertisementManager.getInstance().getActiveVideos())
            System.out.println(ad.getName() + " - " + ad.getHits());
    }

    public void printArchivedVideoSet(){
        for (Advertisement ad : StatisticAdvertisementManager.getInstance().getNonActiveVideos())
            System.out.println(ad.getName());
    }
}
