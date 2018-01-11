package com.javarush.task.task26.task2613;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleHelper {
    private static BufferedReader bis = new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static String readString() {
        try {
            return bis.readLine();
        } catch (IOException e) {
            return "";
        }
    }

    public static String askCurrencyCode() {
        writeMessage("Введите код валюты (Код должен содержать 3 символа)");
        String code;
        while (true) {
            code = readString();
            if (code.length() == 3)
                break;
            writeMessage("Код должен содержать 3 символа");
        }
        return code.toUpperCase();
    }

    public static String[] getValidTwoDigits(String currencyCode) {
        writeMessage("Введите номинал и количество банкнот");
        String[] s;
        while (true) {
            s = readString().split(" ");
            if (s.length == 2 && s[0].matches("\\d+") && s[1].matches("\\d+") && Integer.parseInt(s[0])*Integer.parseInt(s[1])>0)
                break;
            writeMessage("Введите 2 числа через пробел");
        }
        return s;
    }

    public static Operation askOperation(){
        writeMessage("Введите номер операции:\n1 - Info\n2 - Deposit\n3 - Withdraw\n4 - Exit");
        while (true) {
            String line = readString();
            if (line.matches("\\d"))
                return Operation.getAllowableOperationByOrdinal(Integer.parseInt(line));
            writeMessage("Введите корректный номер");
        }
    }
}
