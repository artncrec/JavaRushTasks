package com.javarush.task.task39.task3912;

/* 
Максимальная площадь
*/

public class Solution {
    public static void main(String[] args) {
        int[][] matrix = {
                {0, 1, 0, 1},
                {1, 1, 1, 1},
                {1, 1, 0, 1},
                {0, 1, 1, 1},
        };
        System.out.println(maxSquare(matrix));
    }

    private static int maxSquare(int[][] matrix) {
        int result = 0;
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[i].length; j++) {
                if (matrix[i][j] == 1)
                    matrix[i][j] = Math.min(matrix[i - 1][j], Math.min(matrix[i][j - 1], matrix[i - 1][j - 1])) + 1;
                if (result < matrix[i][j])
                    result = matrix[i][j];
            }
        }
        return result * result;
    }
}
