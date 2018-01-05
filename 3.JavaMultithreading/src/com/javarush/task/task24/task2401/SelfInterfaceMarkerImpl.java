package com.javarush.task.task24.task2401;

/**
 * Created by Arthur on 20.06.2017.
 */
public class SelfInterfaceMarkerImpl implements SelfInterfaceMarker {
    public void print(){
        System.out.println("method - " + this.getClass().getName());
    }

    public void getInt(){
    }
}
