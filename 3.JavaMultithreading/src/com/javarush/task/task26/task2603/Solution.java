package com.javarush.task.task26.task2603;

import java.util.Comparator;

/*
Убежденному убеждать других не трудно
*/
public class Solution {

    public static void main(String[] args) {

    }

    public static class CustomizedComparator<T> implements Comparator<T> {
        private Comparator<T>[] comparators;

        public CustomizedComparator(Comparator<T>... comparators) {
            if (comparators.length > 0)
                this.comparators = comparators;
        }

        @Override
        public int compare(Object o1, Object o2) {
            int i = 0;
            for (int j = 0; j < comparators.length; j++) {
                if ((i = comparators[j].compare((T)o1, (T)o2)) != 0)
                    return i;
            }
            return i;
        }
    }
}
