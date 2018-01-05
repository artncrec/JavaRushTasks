package com.javarush.task.task38.task3810;

public @interface Author {
    Position position() default Position.OTHER;
    String value();
}
