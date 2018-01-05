package com.javarush.task.task38.task3804;

/* 
Фабрика исключений
*/

public class Solution {
    public static Class getFactoryClass() {
        return factoryEx.class;
    }

    public static class factoryEx {
        public static Throwable getFactory(Enum en) {
            if (en != null) {
                String s = en.name().toLowerCase().replace('_', ' ');
                String c = s.substring(0, 1);
                s = s.replaceFirst(c, c.toUpperCase());
                if (en instanceof ExceptionApplicationMessage)
                    return new Exception(s);
                if (en instanceof ExceptionDBMessage)
                    return new RuntimeException(s);
                if (en instanceof ExceptionUserMessage)
                    return new Error(s);
            }
            return new IllegalArgumentException();
        }
    }

    public static void main(String[] args) {
        factoryEx.getFactory(ExceptionApplicationMessage.UNHANDLED_EXCEPTION);
    }
}