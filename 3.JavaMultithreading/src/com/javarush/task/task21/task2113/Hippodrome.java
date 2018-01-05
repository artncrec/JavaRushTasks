package com.javarush.task.task21.task2113;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Arthur on 10.06.2017.
 */
public class Hippodrome {
    private List<Horse> horses = new ArrayList<>();
    static Hippodrome game;

    public Hippodrome(List<Horse> horses) {
        this.horses = horses;
    }

    public List<Horse> getHorses() {
        return horses;
    }

    public static void main(String[] args) throws Exception {
        List<Horse> list = new ArrayList<>();
        list.add(new Horse("Alex", 3, 0));
        list.add(new Horse("Bob", 3, 0));
        list.add(new Horse("Dilan", 3, 0));
        game = new Hippodrome(list);
        game.run();
        game.printWinner();
    }

    public Horse getWinner () {
        double max = 0;
        Horse winner = null;
        for (Horse h : horses)
            if (h.getDistance() > max)
            {
                max = h.getDistance();
                winner = h;
            }
        return winner;
    }

    public void printWinner () {
        System.out.println("Winner is " + getWinner().getName() + "!");
    }

    public void run() throws Exception{
        for (int i = 0; i < 100; i++)
        {
            move();
            print();
            Thread.sleep(50);
        }
    }
    
    public void move(){
        for (Horse h:horses)
            h.move();
    }

    public void print() {
        for (Horse h : horses)
            h.print();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
    }
}
