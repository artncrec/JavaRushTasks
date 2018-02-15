package com.javarush.task.task14.task1413;

public class Computer {
    private Keyboard Keyboard;
    private Mouse Mouse;
    private Monitor Monitor;

    public Computer(Keyboard keyboard, Mouse mouse, Monitor monitor) {
        Keyboard = keyboard;
        Mouse = mouse;
        Monitor = monitor;
    }

    public Keyboard getKeyboard() {
        return Keyboard;
    }

    public Mouse getMouse() {
        return Mouse;
    }

    public Monitor getMonitor() {
        return Monitor;
    }
}
