package com.javarush.task.task34.task3404;

/* 
Рекурсия для мат. выражения
*/
public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.recursion("sin(2*(-5+1.5*4)+28)", 0); //expected output 0.5 6
    }

    public void recursion(final String expression, int countOperation) {
        int i;
        if ((i = expression.lastIndexOf('(')) >= 0) {
            String exp = expression.substring(i + 1, expression.indexOf(')')).trim();
            if (exp.contains("^")){

                StringBuilder builder = new StringBuilder(exp);

            }
            if (exp.contains("*"))

            recursion(exp, countOperation++);
        }
        else {

        }
    }

    public Solution() {
        //don't delete
    }
}