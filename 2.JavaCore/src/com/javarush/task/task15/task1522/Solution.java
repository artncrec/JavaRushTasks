package com.javarush.task.task15.task1522;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* 
Закрепляем паттерн Singleton
*/

public class Solution {
    public static void main(String[] args) {

    }

    public static Planet thePlanet;

    static {
        readKeyFromConsoleAndInitPlanet();
    }

    public static void readKeyFromConsoleAndInitPlanet() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            String data = reader.readLine();
            reader.close();
            if (data.equals(Planet.EARTH))
                thePlanet = Earth.getInstance();
            else if (data.equals(Planet.MOON))
                thePlanet = Moon.getInstance();
            else if (data.equals(Planet.SUN))
                thePlanet = Sun.getInstance();
            else thePlanet = null;
        } catch (IOException e) {
        }
    }
}
