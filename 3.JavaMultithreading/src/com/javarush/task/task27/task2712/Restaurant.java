package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.kitchen.Cook;
import com.javarush.task.task27.task2712.kitchen.Waiter;
import com.javarush.task.task27.task2712.statistic.StatisticManager;

import java.util.ArrayList;
import java.util.List;

public class Restaurant {

   private static final int ORDER_CREATING_INTERVAL = 100;

   public static void main(String[] args) {
      List<Tablet> tablets = new ArrayList<>();
      DirectorTablet directorTablet = new DirectorTablet();

      Cook cook1 = new Cook("Amigo");
      Cook cook2 = new Cook("Artur");
      StatisticManager.getInstance().register(cook1);
      StatisticManager.getInstance().register(cook2);

      Waiter waiter = new Waiter();
      cook1.addObserver(waiter);
      cook2.addObserver(waiter);

      OrderManager orderManager = new OrderManager();
      for (int i = 0; i < 5; i++) {
         Tablet tablet = new Tablet(i);
         tablet.addObserver(orderManager);
         tablets.add(tablet);
      }

      RandomOrderGeneratorTask orderGeneratorTask = new RandomOrderGeneratorTask(tablets, ORDER_CREATING_INTERVAL);

      Thread thread = new Thread(orderGeneratorTask);
      thread.start();
      try {
         Thread.sleep(1000);
      }
      catch (InterruptedException e) {}
      thread.interrupt();
   }
}