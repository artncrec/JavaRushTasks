package com.javarush.task.task25.task2502;

import java.util.*;

/* 
Машину на СТО не повезем!
*/
public class Solution {
    public static enum Wheel {
        FRONT_LEFT,
        FRONT_RIGHT,
        BACK_LEFT,
        BACK_RIGHT
    }

    public static class Car {
        protected List<Wheel> wheels;

        public Car() {
            wheels = new ArrayList<>();
            String[] strings = loadWheelNamesFromDB();
            Wheel[] wheelsEnum = Wheel.values();
            boolean isWheel = false;
            if (strings.length!=4)
                throw new IllegalArgumentException();
            else {
                for (int i = 0; i < strings.length; i++) {
                    for (int j = 0; j < wheelsEnum.length; j++) {
                        isWheel = strings[i].equals(wheelsEnum[j].name());
                        if (isWheel) break;
                    }
                    if (isWheel)
                        wheels.add(Wheel.valueOf(strings[i]));
                    else
                        throw new IllegalArgumentException();
                }
            }
        }

        protected String[] loadWheelNamesFromDB() {
            //this method returns mock data
            return new String[]{"FRONT_RIGHT", "BACK_LEFT", "BACK_RIGHT", "FRONT_LEFT"};
        }
    }

    public static void main(String[] args) {
        System.out.println(new Car().wheels);
    }
}
