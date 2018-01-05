package com.javarush.task.task19.task1924;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/* 
Замена чисел
*/

public class Solution {
    public static Map<Integer, String> map = new HashMap<Integer, String>();

    static
    {
        map.put(0,"ноль");
        map.put(1,"один");
        map.put(2,"два");
        map.put(3,"три");
        map.put(4,"четыре");
        map.put(5,"пять");
        map.put(6,"шесть");
        map.put(7,"семь");
        map.put(8,"восемь");
        map.put(9,"девять");
        map.put(10,"десять");
        map.put(11,"одиннадцать");
        map.put(12,"двенадцать");
    }

    public static void main(String[] args) throws Exception {
        BufferedReader reader0 = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader reader = new BufferedReader(new FileReader(reader0.readLine()));
        reader0.close();
        ArrayList<String> list = new ArrayList<>();
        while (reader.ready())
        {
            list.add(reader.readLine().replace("\uFEFF",""));
        }
        reader.close();
        String[] line;
        for (String l:list)
        {
            line = l.split(" ");
            for (String p:line)
            {
                if (p.length()<=2)
                {
                    if (p.length()==1)
                        if (p.toCharArray()[0]>=48 && p.toCharArray()[0]<58)
                        {
                            for (Map.Entry<Integer,String> entry : map.entrySet())
                            {
                                if (Integer.parseInt(p)==entry.getKey())
                                {
                                    System.out.print(entry.getValue() + " ");
                                    break;
                                }
                            }
                        }
                        else
                            System.out.print(p+" ");
                    else
                        if (p.length()==2)
                        {
                            if (p.charAt(0)=='1')
                            {
                                if (p.charAt(1) == '0')
                                {
                                    System.out.print(map.get(10) + " ");
                                }
                                else if (p.charAt(1) == '1')
                                {
                                    System.out.print(map.get(11) + " ");
                                }
                                else if (p.charAt(1) == '2')
                                {
                                    System.out.print(map.get(12) + " ");
                                }
                                else System.out.print(p + " ");
                            }
                            else System.out.print(p + " ");
                        }

                }
                else
                    System.out.print(p + " ");
            }
            System.out.println();
        }
    }
}
