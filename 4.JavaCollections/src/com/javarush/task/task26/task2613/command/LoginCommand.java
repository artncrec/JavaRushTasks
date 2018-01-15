package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.CashMachine;
import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.util.ResourceBundle;

public class LoginCommand implements Command {
    private ResourceBundle validCreditCards = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "verifiedCards");
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "login_en");

    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage(res.getString("before"));
        ConsoleHelper.writeMessage(res.getString("specify.data"));
        while (true) {
            ConsoleHelper.writeMessage(res.getString("try.again.with.details"));
            String number = ConsoleHelper.readString();
            String pinCode = ConsoleHelper.readString();
            if (!number.matches("\\d{12}") || !pinCode.matches("\\d{4}") || !validCreditCards.containsKey(number) || !validCreditCards.getString(number).equals(pinCode)) {
                ConsoleHelper.writeMessage(String.format(res.getString("not.verified.format"), number));
            } else {
                ConsoleHelper.writeMessage(String.format(res.getString("success.format"), number));
                break;
            }
            ConsoleHelper.writeMessage(res.getString("try.again.or.exit"));
        }
    }
}
