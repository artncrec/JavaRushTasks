package com.javarush.task.task18.task1827;

/* 
Прайсы
*/

import java.io.*;
import java.util.ArrayList;

public class Solution {
    public static void main(String[] args) throws Exception {
        ////---------------------------------------------////
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
        ArrayList<Integer> integers = new ArrayList<>();
        for (String l : list) {
            char[] array = l.substring(0, 8).toCharArray();
            StringBuilder builder = new StringBuilder();
            for (char c : array) {
                System.out.print(c);
                if (c != ' ' && c != 65279) builder.append(c);
            }
            integers.add(Integer.parseInt(builder.toString()));
        }
        int id = 0;
        for (int i : integers)
            if (i > id) id = i;
        ////---------------------------------------------////
        if (args[0].equals("-c")) {
            FileInputStream in = new FileInputStream(file);
            byte[] input = new byte[in.available()];
            in.read(input);
            in.close();
            FileOutputStream outputStream = new FileOutputStream(file);
            outputStream.write(input);
            outputStream.flush();
            if (input != null) {
                outputStream.write(13);
                outputStream.write(10);
            }
            Integer index = new Integer(id + 1);
            if (index.toString().length() >= 8) outputStream.write(index.toString().getBytes());
            else {
                outputStream.write(index.toString().getBytes());
                for (int i = 8 - index.toString().length(); i > 0; i--)
                    outputStream.write(" ".getBytes());
            }
            StringBuilder builder = new StringBuilder();
            for (int z = 1; z < args.length - 2; z++) {
                builder.append(args[z]);
                if (z != args.length - 3) builder.append(" ");
            }
            if (builder.length() >= 30) outputStream.write(builder.toString().substring(0, 30).getBytes());
            else {
                outputStream.write(builder.toString().getBytes());
                for (int i = 30 - builder.length(); i > 0; i--)
                    outputStream.write(" ".getBytes());
            }
            if (args[args.length - 2].length() >= 8)
                outputStream.write(args[args.length - 2].substring(0, 8).getBytes());
            else {
                outputStream.write(args[args.length - 2].getBytes());
                for (int i = 8 - args[args.length - 2].length(); i > 0; i--)
                    outputStream.write(" ".getBytes());
            }
            if (args[args.length - 1].length() >= 4)
                outputStream.write(args[args.length - 1].substring(0, 4).getBytes());
            else {
                outputStream.write(args[args.length - 1].getBytes());
                for (int i = 4 - args[args.length - 1].length(); i > 0; i--)
                    outputStream.write(" ".getBytes());
            }
            outputStream.close();
        }
    }
}
