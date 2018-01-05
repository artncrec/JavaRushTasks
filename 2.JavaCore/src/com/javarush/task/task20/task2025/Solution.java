package com.javarush.task.task20.task2025;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/*
Алгоритмы-числа
*/
public class Solution { //ArmstrongNumbersHash - https://github.com/shamily/ArmstrongNumbers/blob/master/README.md#benchmarking
    private static Map<Long, List<Long>> reach;
    private static List<Long> results;
    private static long[][] pows;

    public static long[] mySolution(long N)
    {
        long[] result;
        List<Long> list = new ArrayList<>();
        long z, sum;
        List<Integer> integers = new ArrayList<Integer>();
        for (long i = 1; i <= N; i++)
        {
            z = i;
            integers.clear();
            while (z > 0)
            {
                integers.add((int)z % 10);
                z /= 10;
            }
            sum = 0; genPows(integers.size());
            for (int k : integers)
            {
                sum += pows[k][integers.size()];
                if (sum>i) break;
            }
            if (i == sum)
            {
                list.add(i);
            }
        }
        int x = 0;
        result = new long[list.size()];
        for (long l : list)
            result[x++] = l;
        return result;
    }

    private static void genPows(int N) {
        if (N > 20) throw new IllegalArgumentException();
        pows = new long[10][N + 1];
        for (int i = 0; i < pows.length; i++) {
            long p = 1;
            for (int j = 0; j < pows[i].length; j++) {
                pows[i][j] = p;
                p *= i;
            }
        }
    }
    private static void searchSecondHalf(int depth, int target, long number, long pow) {
        if (depth == target / 2) {
            long p = pow - number;
            if (!reach.containsKey(p)) reach.put(p, new ArrayList<Long>());
            reach.get(p).add(number);
            return;
        }

        number *= 10;
        for (int i = 0; i < 10; i++) {
            searchSecondHalf(depth + 1, target, number + i, pow + pows[i][target]);
        }
    }
    private static void searchFirstHalf(int depth, int target, long number, long pow) {
        if (depth == (target + 1) / 2) {
            number = number * (long) Math.pow(10, target / 2);
            long p = number - pow;

            if (reach.containsKey(p)) {
                for (Long l : reach.get(p)) {
                    results.add(number + l);
                }
            }
            return;
        }

        number *= 10;
        for (int i = 0; i < 10; i++) {
            if (i == 0 && depth == 0) continue;
            searchFirstHalf(depth + 1, target, number + i, pow + pows[i][target]);
        }
    }
    public static List<Long> generate(int maxN) {
        genPows(maxN);
        results = new ArrayList<>();
        reach = new HashMap<>();

        for (int N = 1; N <= maxN; N++) {
            reach.clear();
            searchSecondHalf(0, N, 0L, 0L);
            searchFirstHalf(0, N, 0L, 0L);
        }

        Collections.sort(results);

        return results;
    }

    public static long[] getNumbers(long N) {
        long[] result;
        results = generate(String.valueOf(N).length());
        int x = 0;
        for (int i=0;i<results.size();i++)
            if (results.get(i)>N)
            {results.remove(i); i--;}
        result = new long[results.size()];
        for (long l:results)
            result[x++] = l;
        return result;
    }

    public static void main(String[] args) throws Exception {
        long N = 0;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(reader.readLine());
        long[] longs = getNumbers(N);
        for (long l : longs)
            System.out.println(l);
    }
}
