package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.CashMachine;
import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.CurrencyManipulator;
import com.javarush.task.task26.task2613.CurrencyManipulatorFactory;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;
import com.javarush.task.task26.task2613.exception.NotEnoughMoneyException;

import java.util.Map;
import java.util.ResourceBundle;

class WithdrawCommand implements Command {
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "withdraw_en");

    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage(res.getString("before"));
        String code = ConsoleHelper.askCurrencyCode();
        CurrencyManipulator manipulator = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(code);
        int sum;
        while (true) {
            ConsoleHelper.writeMessage(res.getString("specify.amount"));
            String s = ConsoleHelper.readString();
            if (s.matches("\\d+")) {
                sum = Integer.parseInt(s);
                if (manipulator.isAmountAvailable(sum)) {
                    try {
                        for (Map.Entry<Integer, Integer> entry : manipulator.withdrawAmount(sum).entrySet())
                            ConsoleHelper.writeMessage("\t" + entry.getKey() + " - " + entry.getValue());
                        ConsoleHelper.writeMessage(String.format(res.getString("success.format"), sum, code));
                        break;
                    } catch (NotEnoughMoneyException e) {
                        ConsoleHelper.writeMessage(res.getString("exact.amount.not.available"));
                        continue;
                    }
                } else {
                    ConsoleHelper.writeMessage(res.getString("not.enough.money"));
                    continue;
                }
            }
            ConsoleHelper.writeMessage(res.getString("specify.not.empty.amount"));
        }
    }
}
