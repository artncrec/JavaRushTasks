package com.javarush.task.task27.task2712.ad;

import com.javarush.task.task27.task2712.ConsoleHelper;
import com.javarush.task.task27.task2712.statistic.StatisticManager;
import com.javarush.task.task27.task2712.statistic.event.VideoSelectedEventDataRow;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AdvertisementManager {

   private final AdvertisementStorage storage = AdvertisementStorage.getInstance();
   private int timeSeconds;

   public AdvertisementManager(int timeSeconds) {
      this.timeSeconds = timeSeconds;
   }

   public void processVideos() throws RuntimeException {
      if (storage.list().isEmpty())
         throw new NoVideoAvailableException();
      List<Advertisement> advertisements = storage.list();
      Collections.sort(advertisements);
      List<Advertisement> toShow = new ArrayList<>();
      int adTime = 0;
      for (Advertisement ad : advertisements) {
         if (adTime + ad.getDuration() <= timeSeconds) {
            try {
               ad.revalidate();
               toShow.add(ad);
               adTime += ad.getDuration();
            }
            catch (UnsupportedOperationException e) {}
         }
      }

      long amount = 0;
      for (Advertisement ad : toShow)
         amount += ad.getAmountPerOneDisplaying();

      StatisticManager.getInstance().register(new VideoSelectedEventDataRow(toShow, amount, adTime));

      for (Advertisement ad : toShow)
         ConsoleHelper.writeMessage(ad.getName() + " is displaying... " +
               ad.getAmountPerOneDisplaying() + ", " +
               ad.getAmountPerOneDisplaying() * 1000 / ad.getDuration());
   }
}
