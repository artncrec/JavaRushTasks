package com.javarush.task.task27.task2712.statistic;

import com.javarush.task.task27.task2712.kitchen.Cook;
import com.javarush.task.task27.task2712.statistic.event.CookedOrderEventDataRow;
import com.javarush.task.task27.task2712.statistic.event.EventDataRow;
import com.javarush.task.task27.task2712.statistic.event.EventType;
import com.javarush.task.task27.task2712.statistic.event.VideoSelectedEventDataRow;

import java.text.SimpleDateFormat;
import java.util.*;

public class StatisticManager {
    private static StatisticManager instance = new StatisticManager();
    private StatisticStorage statisticStorage = new StatisticStorage();
    private Set<Cook> cooks = new HashSet<>();

    public static StatisticManager getInstance() {
        return instance;
    }

    private StatisticManager() {
    }

    public void register(EventDataRow data) {
        statisticStorage.put(data);
    }

    public void register(Cook cook) {
        this.cooks.add(cook);
    }

    public Set<Cook> getCooks() {
        return cooks;
    }

    public Map<String, Double> profit() {
        Map<String, Double> map = new HashMap<>();
        List<EventDataRow> list = statisticStorage.getStorage().get(EventType.SELECTED_VIDEOS);
        VideoSelectedEventDataRow row;
        long result = 0;
        Date date = list.get(0).getDate();
        for (EventDataRow data : list) {
            row = (VideoSelectedEventDataRow) data;
            SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy");
            if (!format.format(row.getDate()).equals(format.format(date))) {
                date = row.getDate();
                result = 0;
            }
            result += row.getAmount();
            map.put(format.format(date), (double) result / 100);
        }
        return map;
    }

    public Map<String, Map<String, Integer>> cookTime() {
        Map<String, Map<String, Integer>> map = new HashMap<>();
        List<EventDataRow> list = statisticStorage.getStorage().get(EventType.COOKED_ORDER);
        CookedOrderEventDataRow row;
        int result = 0;
        Date date = list.get(0).getDate();
        Map<String, Integer> cookMap = new HashMap<>();
        for (EventDataRow data : list) {
            row = (CookedOrderEventDataRow) data;
            SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy");
            if (!format.format(row.getDate()).equals(format.format(date))) {
                cookMap = new HashMap<>();
                date = row.getDate();
                result = 0;
            }
            if (cookMap.containsKey(row.getCookName()))
                result = cookMap.get(row.getCookName());
            else
                result = 0;
            result += row.getTime();
            cookMap.put(row.getCookName(), result);
            map.put(format.format(date), cookMap);
        }
        return map;
    }

    private class StatisticStorage {
        private Map<EventType, List<EventDataRow>> storage = new HashMap<>();

        public Map<EventType, List<EventDataRow>> getStorage() {
            return storage;
        }

        public StatisticStorage() {
            for (EventType type : EventType.values())
                storage.put(type, new ArrayList<EventDataRow>());
        }

        private void put(EventDataRow data) {
            List<EventDataRow> list = storage.get(data.getType());
            list.add(data);
            storage.put(data.getType(), list);
        }
    }
}
