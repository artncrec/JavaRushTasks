package com.javarush.task.task26.task2613;

import com.javarush.task.task26.task2613.exception.NotEnoughMoneyException;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class CurrencyManipulator {
    private String currencyCode;
    private Map<Integer, Integer> denominations = new TreeMap<>(new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o2 - o1;
        }
    });

    public String getCurrencyCode() {
        return currencyCode;
    }

    public CurrencyManipulator(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public void addAmount(int denomination, int count) {
        if (denominations.containsKey(denomination)) {
            int k = denominations.get(denomination);
            denominations.put(denomination, k + count);
        } else
            denominations.put(denomination, count);
    }

    public int getTotalAmount() {
        int sum = 0;
        for (Map.Entry<Integer, Integer> entry : denominations.entrySet()) {
            sum += entry.getKey() * entry.getValue();
        }
        return sum;
    }

    public boolean hasMoney(){
        return !denominations.isEmpty();
    }

    public boolean isAmountAvailable(int expectedAmount) {
        return expectedAmount <= getTotalAmount();
    }

    public Map<Integer, Integer> withdrawAmount(int expectedAmount) throws NotEnoughMoneyException {
        TreeMap<Integer, Integer> result = new TreeMap<>();

        for (Map.Entry<Integer, Integer> entry : denominations.entrySet()) {
            int nominal = entry.getKey();
            int count = entry.getValue();
            if (expectedAmount >= nominal) {
                int k = expectedAmount / nominal;
                if (k <= count) {
                    result.put(nominal, k);
                    expectedAmount -= k * nominal;
                } else
                    throw new NotEnoughMoneyException();
            }
            if (expectedAmount == 0) break;
        }
        if (expectedAmount == 0) {
            for (Map.Entry<Integer, Integer> entry : result.entrySet()) {
                int nominal = entry.getKey();
                int count = denominations.get(nominal);
                count -= entry.getValue();
                denominations.put(nominal, count);
            }
            return result;
        }
        else
            throw new NotEnoughMoneyException();
    }
}
