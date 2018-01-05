package com.javarush.task.task27.task2712.ad;

public class Advertisement implements Comparable<Advertisement>{
    private Object content;
    private String name;
    private long initialAmount;
    private int hits;
    private int duration;
    private long amountPerOneDisplaying;

    public Advertisement(Object content, String name, long initialAmount, int hits, int duration) {
        this.content = content;
        this.name = name;
        this.initialAmount = initialAmount;
        this.hits = hits;
        this.duration = duration;
        amountPerOneDisplaying = initialAmount / hits;
    }

    public void revalidate() throws UnsupportedOperationException {
        if (hits <= 0)
            throw new UnsupportedOperationException();
        hits--;
    }

    public int getHits() {
        return hits;
    }

    public String getName() {
        return name;
    }

    public int getDuration() {
        return duration;
    }

    public long getAmountPerOneDisplaying() {
        return amountPerOneDisplaying;
    }

    @Override
    public int compareTo(Advertisement o) {
        if (o != null) {
            long a = this.amountPerOneDisplaying;// * 1000 / this.duration;
            long b = o.amountPerOneDisplaying;// * 1000 / o.duration;
            if (a==b)
                return this.duration > o.duration ? -1 : 1;
            else
                return a > b? -1 : 1;
        }
        return 0;
    }
}
