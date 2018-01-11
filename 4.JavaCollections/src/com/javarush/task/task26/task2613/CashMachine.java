package com.javarush.task.task26.task2613;

import java.util.Locale;

public class CashMachine {
    public static void main(String[] args) {
        Locale.setDefault(Locale.ENGLISH);
        Operation operation = ConsoleHelper.askOperation();
        String code = ConsoleHelper.askCurrencyCode();
        String[] s = ConsoleHelper.getValidTwoDigits(code);
        CurrencyManipulator manipulator = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(code);
        manipulator.addAmount(Integer.parseInt(s[0]), Integer.parseInt(s[1]));
        System.out.println(manipulator.getTotalAmount());
    }
}
