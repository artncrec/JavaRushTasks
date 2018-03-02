package com.javarush.task.task18.task1828;

/* 
Прайсы 2
*/

import java.io.*;
import java.util.ArrayList;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String file = reader.readLine();
        reader.close();
        ////---------------------------------------------////
        reader = new BufferedReader(new FileReader(file));
        ArrayList<String> list = new ArrayList<>();
        String s;
        while ((s = reader.readLine()) != null) {
            list.add(s);
        }
        reader.close();
        ArrayList<Integer> integers = new ArrayList<>();
        for (String l : list) {
            char[] array = l.substring(0, 8).toCharArray();
            StringBuilder builder = new StringBuilder();
            for (char c : array) {
                if (c != ' ' && c != 65279) builder.append(c);
            }
            integers.add(Integer.parseInt(builder.toString()));
        }
        ////---------------------------------------------////
        if (args[0].equals("-d")) {
            for (int i = 0; i < integers.size(); i++) {
                if (Integer.parseInt(args[1]) == integers.get(i)) {
                    list.remove(i);
                    break;
                }
            }
        }
        ////---------------------------------------------////
        if (args[0].equals("-u")) {
            for (int i = 0; i < integers.size(); i++) {
                if (Integer.parseInt(args[1]) == integers.get(i)) {
                    list.remove(i);
                    StringBuilder builder = new StringBuilder();
                    if (args[1].length() >= 8)
                        builder.append(args[1].substring(0, 8));
                    else {
                        builder.append(args[1]);
                        for (int y = 8 - args[1].length(); y > 0; y--)
                            builder.append(' ');
                    }
                    StringBuilder builderName = new StringBuilder();
                    for (int z = 2; z < args.length - 2; z++) {
                        builderName.append(args[z]);
                        if (z != args.length - 3) builderName.append(" ");
                    }
                    if (builderName.length() >= 30)
                        builder.append(builderName.toString().substring(0, 30));
                    else {
                        builder.append(builderName.toString());
                        for (int y = 30 - builderName.length(); y > 0; y--)
                            builder.append(' ');
                    }
                    if (args[args.length - 2].length() >= 8)
                        builder.append(args[args.length - 2].substring(0, 8));
                    else {
                        builder.append(args[args.length - 2]);
                        for (int y = 8 - args[args.length - 2].length(); y > 0; y--)
                            builder.append(' ');
                    }
                    if (args[args.length - 1].length() >= 4)
                        builder.append(args[args.length - 1].substring(0, 4));
                    else {
                        builder.append(args[args.length - 1]);
                        for (int y = 4 - args[args.length - 1].length(); y > 0; y--)
                            builder.append(' ');
                    }
                    list.add(i, builder.toString());
                }
            }
        }
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        for (String q : list) {
            writer.write(q);
            writer.write(13);
            writer.write(10);
        }
        writer.close();
    }
}
