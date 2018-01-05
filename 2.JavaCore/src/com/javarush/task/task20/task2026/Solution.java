package com.javarush.task.task20.task2026;

/* 
Алгоритмы-прямоугольники
*/
public class Solution {
    public static void main(String[] args) {
        byte[][] a = new byte[][]{
                {1, 0, 1, 0, 1},
                {0, 1, 0, 1, 0},
                {1, 0, 1, 0, 1},
                {0, 1, 0, 1, 0},
                {1, 0, 1, 0, 1}
        };
        int count = getRectangleCount(a);
        System.out.println("count = " + count + ". Должно быть 2");
    }

    public static int getRectangleCount(byte[][] a) {
        int count=0;
        for (int i = 0, j=0; i < a.length; i++)
        {
            j=0;
            while (j<a[i].length)
            {
                if (a[i][j]==1)
                {
                    count++;
                    clearRectangle(a, i ,j);
                }
                j++;
            }
        }
        return count;
    }

    public static void clearRectangle(byte[][] a, int i, int j)
    {
        int j0=j;
        for (;i<a.length;i++)
        {
            j=j0;
            if (a[i][j] == 0) break;
            while (j < a[i].length)
            {
                if (a[i][j] == 1)
                {
                    a[i][j] = 0;
                    j++;
                }
                else break;
            }
        }
    }
}
