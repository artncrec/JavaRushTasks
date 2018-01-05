package com.javarush.task.task20.task2015;

import java.io.*;

/* 
Переопределение сериализации
*/
public class Solution implements Serializable, Runnable{
    transient private Thread runner;
    private int speed;

    public Solution(int speed) {
        this.speed = speed;
        runner = new Thread(this);
        runner.start();
    }

    public void run() {
        float i;
        for (i = 0; i < 999; i++)
        {
            System.out.println(speed);
            try {
                Thread.sleep(speed);
            }
            catch (Exception e)
            {
                System.out.println(e);
            }
        }
        System.out.println(i);
    }

    /**
     Переопределяем сериализацию.
     Для этого необходимо объявить методы:
     private void writeObject(ObjectOutputStream out) throws IOException
     private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException
     Теперь сериализация/десериализация пойдет по нашему сценарию :)
     */
    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        new Thread(this).start();
    }

    public static void main(String[] args) throws Exception {
        Solution solution = new Solution(2);

        FileOutputStream stream = new FileOutputStream("D:\\0.txt");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(stream);
        objectOutputStream.writeObject(solution);
        objectOutputStream.close();
        stream.close();

        FileInputStream inputStream = new FileInputStream("D:\\0.txt");
        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
        Solution loadSol = (Solution)objectInputStream.readObject();
        objectInputStream.close();
        inputStream.close();
    }
}
