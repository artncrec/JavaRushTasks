package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.CashMachine;
import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.CurrencyManipulator;
import com.javarush.task.task26.task2613.CurrencyManipulatorFactory;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.util.Collection;
import java.util.ResourceBundle;

class InfoCommand implements Command {
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "info_en");
    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage(res.getString("before"));
        Collection<CurrencyManipulator> collection = CurrencyManipulatorFactory.getAllCurrencyManipulators();
        if (collection.isEmpty())
            System.out.println(res.getString("no.money"));
        else
            for (CurrencyManipulator manipulator : collection) {
                if (!manipulator.hasMoney())
                    System.out.println(res.getString("no.money"));
                else
                    System.out.println(manipulator.getCurrencyCode().toUpperCase() + " - " + manipulator.getTotalAmount());
            }
    }
}
