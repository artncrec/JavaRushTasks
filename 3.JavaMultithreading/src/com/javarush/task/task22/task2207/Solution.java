package com.javarush.task.task22.task2207;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/* 
Обращенные слова
*/
public class Solution {
    public static List<Pair> result = new LinkedList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader reader = new BufferedReader(new FileReader(consoleReader.readLine()));
        consoleReader.close();
        String string;
        StringBuilder builder;
        List<String> list = new ArrayList<>();
        while ((string = reader.readLine()) != null)
        {
            for (String s : string.split(" "))
            {
                if (s.contains("\uFEFF"))
                    s = new StringBuilder(s).deleteCharAt(0).toString();
                list.add(s);
            }
        }
        for (int i = 0; i < list.size(); i++)
            nextI:
                    {
                        for (Pair p : result)
                        {
                            if (p.first.equals(list.get(i)) || p.second.equals(list.get(i)))
                                break nextI;
                        }
                        for (int k = i + 1; k < list.size(); k++)
                        {
                            builder = new StringBuilder(list.get(k));
                            if (list.get(i).equals(builder.reverse().toString()))
                            {
                                Pair pair = new Pair();
                                pair.first = list.get(i);
                                pair.second = list.get(k);
                                result.add(pair);
                                break;
                            }
                        }

                    }
        reader.close();
        System.out.println(list);
        for (Pair p : result)
            System.out.println(p);
    }

    public static class Pair {
        String first;
        String second;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Pair pair = (Pair) o;

            if (first != null ? !first.equals(pair.first) : pair.first != null) return false;
            return second != null ? second.equals(pair.second) : pair.second == null;
        }

        @Override
        public int hashCode() {
            int result = first != null ? first.hashCode() : 0;
            result = 31 * result + (second != null ? second.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return  first == null && second == null ? "" :
                    first == null && second != null ? second :
                    second == null && first != null ? first :
                    first.compareTo(second) < 0 ? first + " " + second : second + " " + first;

        }
    }

}
