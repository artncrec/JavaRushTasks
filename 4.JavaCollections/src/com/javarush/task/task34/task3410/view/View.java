package com.javarush.task.task34.task3410.view;

import com.javarush.task.task34.task3410.controller.Controller;
import com.javarush.task.task34.task3410.controller.EventListener;
import com.javarush.task.task34.task3410.model.GameObjects;
import com.sun.org.apache.xpath.internal.operations.String;

import javax.swing.*;

public class View extends JFrame {
    private Controller controller;
    private Field field;

    public View(Controller controller) {
        this.controller = controller;
    }

    public void init() {
        field = new Field(this);
        add(field);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(540, 540);
        setLocationRelativeTo(null);
        setTitle("Сокобан");
        setVisible(true);
    }

    public void setEventListener(EventListener eventListener) {
        field.setEventListener(eventListener);
    }

    public void update() {
        field.repaint();
    }

    public GameObjects getGameObjects() {
        return controller.getGameObjects();
    }

    public void completed(int level) {
        update();
        JOptionPane.showMessageDialog(field, level + " Level Complete");
        controller.startNextLevel();
    }
}
