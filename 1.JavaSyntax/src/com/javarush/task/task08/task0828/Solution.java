package com.javarush.task.task08.task0828;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/* 
Номер месяца
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
        String month = read.readLine();
        List<String> list = new ArrayList<>();
        list.add(0, "January");
        list.add(1, "February");
        list.add(2, "March");
        list.add(3, "April");
        list.add(4, "May");
        list.add(5, "June");
        list.add(6, "July");
        list.add(7, "August");
        list.add(8, "September");
        list.add(9, "October");
        list.add(10, "November");
        list.add(11, "December");
        for (int z = 0; z < 12; z++)
            if (month.equals(list.get(z)))
                System.out.println(month + " is " + (z + 1) + " month");
    }
}
