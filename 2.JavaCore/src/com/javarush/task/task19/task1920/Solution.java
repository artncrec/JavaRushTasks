package com.javarush.task.task19.task1920;

/* 
Самый богатый
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader(args[0]));
        ArrayList<String> data = new ArrayList<>();
        String s;
        while ((s = reader.readLine()) != null)
        {
            data.add(s.replace("\uFEFF", ""));
        }
        reader.close();
        HashMap<String, Double> map = new HashMap<>();
        double d;
        for (String l : data)
        {
            d = 0;
            if (map.containsKey(l.split(" ")[0])) {
                d = Double.parseDouble(l.split(" ")[1]) + map.get(l.split(" ")[0]);
                map.remove(l.split(" ")[0]);
                map.put(l.split(" ")[0], d);
            } else
                map.put(l.split(" ")[0], Double.parseDouble(l.split(" ")[1]));
        }
        ArrayList<String> list1 = new ArrayList<>();
        ArrayList<Double> list2 = new ArrayList<>();
        for (Map.Entry<String, Double> entry : map.entrySet())
        {
            list1.add(entry.getKey());
            list2.add(entry.getValue());
        }
        s = "";
        d = 0;
        double max=0;
        for (int i = 0; i < list1.size(); i++)
        {
            for (int j = i; j < list1.size(); j++)
                if (list1.get(i).compareTo(list1.get(j)) > 0)
                {
                    s = list1.get(i);
                    list1.set(i, list1.get(j));
                    list1.set(j, s);
                    d = list2.get(i);
                    list2.set(i, list2.get(j));
                    list2.set(j, d);
                }
            if (list2.get(i)>max)
            {
                max=list2.get(i);
            }
        }
        for (int i = 0; i < list1.size(); i++)
        {
            if (list2.get(i) == max)
                System.out.println(list1.get(i));
        }
    }
}
