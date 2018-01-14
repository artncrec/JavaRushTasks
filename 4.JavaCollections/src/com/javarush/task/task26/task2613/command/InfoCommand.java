package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.CurrencyManipulator;
import com.javarush.task.task26.task2613.CurrencyManipulatorFactory;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.util.Collection;

class InfoCommand implements Command {
    @Override
    public void execute() throws InterruptOperationException {
        Collection<CurrencyManipulator> collection = CurrencyManipulatorFactory.getAllCurrencyManipulators();
        if (collection.isEmpty())
            System.out.println("No money available.");
        else
            for (CurrencyManipulator manipulator : collection) {
                if (!manipulator.hasMoney())
                    System.out.println("No money available.");
                else
                    System.out.println(manipulator.getCurrencyCode().toUpperCase() + " - " + manipulator.getTotalAmount());
            }
    }
}
