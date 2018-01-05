package com.javarush.task.task33.task3308;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
public class Shop {
    public static class Goods {
        public List<String> names = new ArrayList<>();
    }

    public Goods goods;
    public int count;
    public double profit;
    public String[] secretData = new String[5];

    @Override
    public String toString() {
        return this.getClass().getSimpleName()
                + " | names = " + goods.names.toString()
                + " | count = " + count
                + " | profit = " + profit
                + " | secret data = " + secretData.toString();
    }
}
