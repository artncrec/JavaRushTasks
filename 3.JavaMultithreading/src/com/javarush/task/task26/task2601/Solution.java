package com.javarush.task.task26.task2601;

/* 
Почитать в инете про медиану выборки
*/
public class Solution {

    public static void main(String[] args) {
        Integer[] array = {13, 8, 15, 5, 17, 14, 17, 4, 12, 3, 9, 11, 2, 10};
        array = sort(array);
    }

    public static Integer[] sort(Integer[] array) {
        //implement logic here
        Integer[] result;
        int m;
        array = sortHalf(array);
        if (array.length % 2 == 0)
        {
            result = new Integer[array.length];
            m = (array[array.length / 2] + array[array.length / 2 - 1]) / 2;
            int q = 0, b = 0;
            for (int i = 0, j = 1, n = array.length / 2; i + j <= array.length; )
            {
                if (n + i >= array.length)
                {
                    result[i + j - 1] = array[n - j];
                    j++;
                    continue;
                }
                else
                    q = array[n + i] - m;
                if (n - j < 0)
                {
                    result[j + i - 1] = array[n + i];
                    i++;
                    continue;
                }
                else
                    b = m - array[n - j];
                if (q < b)
                {
                    result[j + i - 1] = array[n + i];
                    i++;
                }
                else
                {
                    result[i + j - 1] = array[n - j];
                    j++;
                }
            }
        }
        else
        {
            result = new Integer[array.length];
            m = array[array.length / 2];
            result[0] = m;
            int q = 0, b = 0;
            for (int i = 1, j = 1, n = array.length / 2; i+j <= array.length;)
            {
                if (n + i >= array.length)
                {
                    result[i + j - 1] = array[n - j];
                    j++; continue;
                }
                else
                    q = array[n + i] - result[0];
                if (n - j < 0)
                {
                    result[j + i - 1] = array[n + i];
                    i++; continue;
                }
                else
                    b = result[0] - array[n - j];
                if (q < b)
                {
                    result[j + i - 1] = array[n + i];
                    i++;
                }
                else
                {
                    result[i + j - 1] = array[n - j];
                    j++;
                }
            }
        }
        return result;
    }

    private static Integer[] merge(Integer[] left, Integer[] right) {
        int l = left.length + right.length, j = 0, k = 0;
        Integer[] result = new Integer[l];
        for (int i = 0; i < l; i++) {
            if (j == left.length)
            {
                result[i] = right[k]; k++; continue;
            }
            if (k == right.length)
            {
                result[i] = left[j]; j++; continue;
            }
            if (left[j] < right[k]) {
                result[i] = left[j];
                j++;
            } else {
                result[i] = right[k];
                k++;
            }
        }
        return result;
    }

    private static Integer[] sortHalf(Integer[] array) {
        Integer[] left = null, right = null;
        int n = array.length;
        if (n > 1) {
            left = new Integer[n / 2];
            if (n % 2 == 0)
                right = new Integer[n / 2];
            else
                right = new Integer[n / 2 + 1];
            for (int i = 0; i < n; i++) {
                if (i < n / 2)
                    left[i] = array[i];
                else
                    right[i - n / 2] = array[i];
            }
            left = sortHalf(left);
            right = sortHalf(right);
            return merge(left, right);
        } else return array;
    }
}
