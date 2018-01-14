package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.CurrencyManipulator;
import com.javarush.task.task26.task2613.CurrencyManipulatorFactory;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;
import com.javarush.task.task26.task2613.exception.NotEnoughMoneyException;

import java.util.Map;

class WithdrawCommand implements Command {
    @Override
    public void execute() throws InterruptOperationException {
        String code = ConsoleHelper.askCurrencyCode();
        CurrencyManipulator manipulator = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(code);
        int sum;
        while (true) {
            ConsoleHelper.writeMessage("Введите сумму");
            String s = ConsoleHelper.readString();
            if (s.matches("\\d+")) {
                sum = Integer.parseInt(s);
                if (manipulator.isAmountAvailable(sum)) {
                    try {
                        for (Map.Entry<Integer, Integer> entry : manipulator.withdrawAmount(sum).entrySet())
                            ConsoleHelper.writeMessage("\t" + entry.getKey() + " - " + entry.getValue());
                        ConsoleHelper.writeMessage("Успешно");
                        break;
                    } catch (NotEnoughMoneyException e) {
                        ConsoleHelper.writeMessage("Недостаточно банкнот");
                        continue;
                    }
                }
            }
            ConsoleHelper.writeMessage("Данные не корректны");
        }
    }
}
