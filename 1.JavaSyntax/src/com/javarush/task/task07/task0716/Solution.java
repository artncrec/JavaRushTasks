package com.javarush.task.task07.task0716;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

/* 
Р или Л
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader bis = new BufferedReader(new InputStreamReader(System.in));

        ArrayList<String> list = new ArrayList<String>();
        list.add("роза"); //0
        list.add("лоза"); //1
        list.add("лира"); //2
        list = fix(list);

        for (String s : list) {
            System.out.println(s);
        }
    }

    public static ArrayList<String> fix(ArrayList<String> list) {
        for (int z = 0; z < list.size(); ) {
            if (list.get(z).contains("р") && !list.get(z).contains("л"))
                list.remove(z);
            else if (list.get(z).contains("л") && !list.get(z).contains("р")) {
                list.add(z, list.get(z));
                z += 2;
            } else z++;
        }
        return list;
    }
}