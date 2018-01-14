package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.CashMachine;
import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.util.ResourceBundle;

public class LoginCommand implements Command {
    private ResourceBundle validCreditCards = ResourceBundle.getBundle(CashMachine.class.getPackage().getName() + "/resources/verifiedCards");

    @Override
    public void execute() throws InterruptOperationException {
        while (true) {
            ConsoleHelper.writeMessage("Введите номер карты");
            String number = ConsoleHelper.readString();
            ConsoleHelper.writeMessage("Введите пин код");
            String pinCode = ConsoleHelper.readString();
            if (!number.matches("\\d{12}") || !pinCode.matches("\\d{4}") || !validCreditCards.containsKey(number) || !validCreditCards.getString(number).equals(pinCode)) {
                ConsoleHelper.writeMessage("Данные не корректны");
            } else {
                ConsoleHelper.writeMessage("Верификация успешна");
                break;
            }
        }
    }
}
