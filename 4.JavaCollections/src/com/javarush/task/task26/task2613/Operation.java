package com.javarush.task.task26.task2613;

public enum Operation {
    LOGIN, INFO, DEPOSIT, WITHDRAW, EXIT;

    public static Operation getAllowableOperationByOrdinal(Integer i) {
        Operation[] operations = Operation.values();
        if (i > 0 && i < operations.length)
            return operations[i];
        else throw new IllegalArgumentException();
    }
}
