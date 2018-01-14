package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;

class ExitCommand implements Command {
    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage("Действительно хотите выйти?\ny - да\nn - нет");
        String s = ConsoleHelper.readString();
        if (s.equals("y"))
            ConsoleHelper.writeMessage("Прощай");
    }
}
