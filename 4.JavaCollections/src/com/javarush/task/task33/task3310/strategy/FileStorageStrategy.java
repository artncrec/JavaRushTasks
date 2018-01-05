package com.javarush.task.task33.task3310.strategy;

public class FileStorageStrategy implements StorageStrategy {
    private static final int DEFAULT_INITIAL_CAPACITY = 16;
    private static final long DEFAULT_BUCKET_SIZE_LIMIT = 10000;
    private long bucketSizeLimit = DEFAULT_BUCKET_SIZE_LIMIT;
    private FileBucket[] table = new FileBucket[DEFAULT_INITIAL_CAPACITY];
    int size;
    long maxBucketSize;

    public void setBucketSizeLimit(long bucketSizeLimit) {
        this.bucketSizeLimit = bucketSizeLimit;
    }

    public long getBucketSizeLimit() {
        return bucketSizeLimit;
    }

    public int hash(Long k){
        int h;
        return (k == null) ? 0 : (h = k.hashCode()) ^ (h >>> 16);
    }

    public int indexFor(int hash, int length) {
        return hash & (length - 1);
    }

    public Entry getEntry(Long key) {
        int hash = (key == null) ? 0 : hash((long) key.hashCode());
        FileBucket e = table[indexFor(hash, table.length)];
        if (e != null)
            for (Entry entry = e.getEntry(); entry != null; entry = entry.next)
                if ((entry.getKey() != null && entry.getKey().equals(key)) || entry.getKey() == key)
                    return entry;
        return null;
    }

    public void resize(int newCapacity) {
        if (table.length == Integer.MAX_VALUE)
            return;

        FileBucket[] newTable = new FileBucket[newCapacity];
        transfer(newTable);
        table = newTable;
    }

    public void transfer(FileBucket[] newTable) {
        FileBucket[] source = table;
        for (int i = 0; i < source.length; i++) {
            Entry e = source[i].getEntry();
            while (e != null) {
                source[i].remove();
                Entry next = e.next;
                int j = indexFor(e.hash, newTable.length);
                if (newTable[j] == null)
                    newTable[j] = new FileBucket();
                e.next = newTable[j].getEntry();
                newTable[j].putEntry(e);
                e = next;
            }
        }
    }

    public void addEntry(int hash, Long key, String value, int bucketIndex) {
        createEntry(hash, key, value, bucketIndex);
        if (table[bucketIndex].getFileSize() > bucketSizeLimit)
            resize(table.length * 2);
    }

    public void createEntry(int hash, Long key, String value, int bucketIndex) {
        Entry e = table[bucketIndex].getEntry();
        table[bucketIndex].putEntry(new Entry(hash, key, value, e));
        size++;
    }

    @Override
    public boolean containsKey(Long key) {
        return getEntry(key) != null;
    }

    @Override
    public boolean containsValue(String value) {
        for (int i = 0; i < table.length; i++) {
            if (table[i] == null) continue;
            Entry entry = table[i].getEntry();
            while (entry != null) {
                if (value == null && value == entry.getValue() || entry.getValue().equals(value))
                    return true;
                entry = entry.next;
            }
        }
        return false;
    }

    @Override
    public void put(Long key, String value) {
        int hash = (key == null) ? 0 : hash((long) key.hashCode());
        int i = indexFor(hash, table.length);
        if (table[i] == null)
            table[i] = new FileBucket();
        Entry entry = table[i].getEntry();
        while (entry != null){
            if (entry.hash == hash && entry.getKey().equals(key))
                entry.value = value;
            entry = entry.next;
        }
        addEntry(hash, key, value, i);
    }

    @Override
    public Long getKey(String value) {
        for (int i = 0; i < table.length; i++) {
            if (table[i] == null) continue;
            Entry entry = table[i].getEntry();
            while (entry != null) {
                if (entry.getValue().equals(value))
                    return entry.getKey();
                entry = entry.next;
            }
        }
        return null;
    }

    @Override
    public String getValue(Long key) {
        return getEntry(key).getValue();
    }
}