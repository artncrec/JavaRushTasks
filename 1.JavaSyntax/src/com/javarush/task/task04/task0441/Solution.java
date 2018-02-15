package com.javarush.task.task04.task0441;


/* 
Как-то средненько
*/
import java.io.*;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
        int a = Integer.parseInt(rd.readLine());
        int b = Integer.parseInt(rd.readLine());
        int c = Integer.parseInt(rd.readLine());
        if ((a < b && a > c) || (a < c && a > b)) System.out.println(a);
        if ((b < a && b > c) || (b < c && b > a)) System.out.println(b);
        if ((c < a && c > b) || (c < b && c > a)) System.out.println(c);
    }
}
