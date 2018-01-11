package com.javarush.task.task26.task2613;

public enum Operation {
    INFO, DEPOSIT, WITHDRAW, EXIT;

    public static Operation getAllowableOperationByOrdinal(Integer i) {
        Operation[] operations = Operation.values();
        if (i > 0 && i <= operations.length)
            return operations[i - 1];
        else throw new IllegalArgumentException();
    }
}
