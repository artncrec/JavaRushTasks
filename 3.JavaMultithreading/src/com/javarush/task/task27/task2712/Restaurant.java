package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.kitchen.Cook;
import com.javarush.task.task27.task2712.kitchen.Order;
import com.javarush.task.task27.task2712.kitchen.Waiter;
import com.javarush.task.task27.task2712.statistic.StatisticManager;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

public class Restaurant {
   private static final LinkedBlockingQueue<Order> orderQueue = new LinkedBlockingQueue<>();
   private static final int ORDER_CREATING_INTERVAL = 100;

   public static void main(String[] args) {
      List<Tablet> tablets = new ArrayList<>();
      DirectorTablet directorTablet = new DirectorTablet();

      Cook cook1 = new Cook("Amigo");
      Cook cook2 = new Cook("Artur");

      Waiter waiter = new Waiter();
      cook1.addObserver(waiter);
      cook1.setQueue(orderQueue);
      cook2.addObserver(waiter);
      cook2.setQueue(orderQueue);

      for (int i = 0; i < 5; i++) {
         Tablet tablet = new Tablet(i);
         tablet.setQueue(orderQueue);
         tablets.add(tablet);
      }
      Thread cook = new Thread(cook1);
      cook.setDaemon(true);
      cook.start();
      cook = new Thread(cook2);
      cook.setDaemon(true);
      cook.start();

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