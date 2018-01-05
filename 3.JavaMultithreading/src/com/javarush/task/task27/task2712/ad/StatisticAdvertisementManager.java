package com.javarush.task.task27.task2712.ad;

import java.util.*;

public class StatisticAdvertisementManager {
    private static StatisticAdvertisementManager ourInstance = new StatisticAdvertisementManager();
    AdvertisementStorage storage = AdvertisementStorage.getInstance();

    public static StatisticAdvertisementManager getInstance() {
        return ourInstance;
    }

    private StatisticAdvertisementManager() {
    }

    public List<Advertisement> getActiveVideos () {
        List<Advertisement> advertisements = new ArrayList<>();
        for (Advertisement ad : storage.list())
            if (ad.getHits() > 0)
                advertisements.add(ad);
        Collections.sort(advertisements, new Comparator<Advertisement>() {
            @Override
            public int compare(Advertisement o1, Advertisement o2) {
                return o1.getName().compareToIgnoreCase(o2.getName());
            }
        });
        return advertisements;
    }

    public List<Advertisement> getNonActiveVideos () {
        List<Advertisement> advertisements = new ArrayList<>();
        for (Advertisement ad : storage.list())
            if (ad.getHits() <= 0)
                advertisements.add(ad);
        Collections.sort(advertisements, new Comparator<Advertisement>() {
            @Override
            public int compare(Advertisement o1, Advertisement o2) {
                return o1.getName().compareToIgnoreCase(o2.getName());
            }
        });
        return advertisements;
    }
}
