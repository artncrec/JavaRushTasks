package com.javarush.task.task26.task2613;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CurrencyManipulatorFactory {
    private CurrencyManipulatorFactory() {
    }

    private static Map<String, CurrencyManipulator> map = new HashMap<>();

    public static CurrencyManipulator getManipulatorByCurrencyCode(String currencyCode){
        String currency = currencyCode.toLowerCase();
        if (map.containsKey(currency))
            return map.get(currency);
        else {
            CurrencyManipulator currencyManipulator = new CurrencyManipulator(currency);
            map.put(currency, currencyManipulator);
            return currencyManipulator;
        }
    }
    public static Collection<CurrencyManipulator> getAllCurrencyManipulators(){
        return map.values();
    }
}
