package com.javarush.task.task26.task2613;

import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ResourceBundle;

public class ConsoleHelper {
    private static BufferedReader bis = new BufferedReader(new InputStreamReader(System.in));
    private static ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "common_en");

    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static String readString() throws InterruptOperationException {
        try {
            String s = bis.readLine();
            if (s.toLowerCase().equals("exit")) {
                throw new InterruptOperationException();
            }
            return s;
        } catch (IOException e) {
            return "";
        }
    }

    public static void printExitMessage(){
        writeMessage(res.getString("the.end"));
    }

    public static String askCurrencyCode() throws InterruptOperationException {
        writeMessage(res.getString("choose.currency.code"));
        String code;
        while (true) {
            code = readString();
            if (code.length() == 3)
                break;
            writeMessage(res.getString("invalid.data"));
        }
        return code.toUpperCase();
    }

    public static String[] getValidTwoDigits(String currencyCode) throws InterruptOperationException {
        writeMessage(String.format(res.getString("choose.denomination.and.count.format"), currencyCode));
        String[] s;
        while (true) {
            s = readString().split(" ");
            if (s.length == 2 && s[0].matches("\\d+") && s[1].matches("\\d+") && Integer.parseInt(s[0]) * Integer.parseInt(s[1]) > 0)
                break;
            writeMessage(res.getString("invalid.data"));
        }
        return s;
    }

    public static Operation askOperation() throws InterruptOperationException {
        writeMessage(res.getString("choose.operation"));
        writeMessage("1 - " + res.getString("operation.INFO"));
        writeMessage("2 - " + res.getString("operation.DEPOSIT"));
        writeMessage("3 - " + res.getString("operation.WITHDRAW"));
        writeMessage("4 - " + res.getString("operation.EXIT"));
        while (true) {
            String line = readString();
            if (line.matches("\\d"))
                return Operation.getAllowableOperationByOrdinal(Integer.parseInt(line));
            writeMessage(res.getString("invalid.data"));
        }
    }
}
