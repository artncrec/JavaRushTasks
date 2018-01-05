package com.javarush.task.task19.task1916;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/* 
Отслеживаем изменения
*/

public class Solution {
    public static List<LineItem> lines = new ArrayList<LineItem>();

    public static void main(String[] args) throws Exception{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String file1, file2, s;
        file1 = reader.readLine();
        file2 = reader.readLine();
        reader.close();
        BufferedReader reader2 = new BufferedReader(new FileReader(file1));
        ArrayList<String> list = new ArrayList<>();
        while ((s=reader2.readLine())!=null)
        {
            list.add(s);
        }
        reader2.close();
        ArrayList<String> list2 = new ArrayList<>();
        BufferedReader reader3 = new BufferedReader(new FileReader(file2));
        while ((s=reader3.readLine())!=null)
        {
            list2.add(s);
        }
        reader3.close();

        for (int i = 0, j=0; i < list.size();)
        {
            if (list.get(i).equals(list2.get(j)))
            {
                lines.add(new LineItem(Type.SAME, list.get(i)));
                if (i == list.size() - 1 && j < list2.size() - 1)
                {
                    lines.add(new LineItem(Type.ADDED, list2.get(j + 1)));
                    i++;
                } else
                    if (j == list2.size() - 1 && i < list.size() - 1)
                    {
                        lines.add(new LineItem(Type.REMOVED, list.get(i + 1)));
                        break;
                    }
                    else
                    {
                        i++; j++;
                    }
            }
            else {
                if (list.get(i).equals(list2.get(j + 1))) {
                    lines.add(new LineItem(Type.ADDED, list2.get(j)));
                    j++;
                }
                if (list.get(i + 1).equals(list2.get(j))) {
                    lines.add(new LineItem(Type.REMOVED, list.get(i)));
                    i++;
                }
            }
        }

        for (LineItem l : lines)
            System.out.println(l.type+ " "+l.line);
    }


    public static enum Type {
        ADDED,        //добавлена новая строка
        REMOVED,      //удалена строка
        SAME          //без изменений
    }

    public static class LineItem {
        public Type type;
        public String line;

        public LineItem(Type type, String line) {
            this.type = type;
            this.line = line;
        }
    }
}
