package com.javarush.task.task33.task3310.strategy;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileBucket {
    private Path path;

    public FileBucket() {
        try {
            path = Files.createTempFile(null, null);
            Files.deleteIfExists(path);
            Files.createFile(path);
            path.toFile().deleteOnExit();
        } catch (IOException e) {
        }
    }

    public long getFileSize(){
        try {
            return Files.size(path);
        } catch (IOException e) {
        }
        return 0;
    }

    public void putEntry(Entry entry) {
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(Files.newOutputStream(path));
            while (entry != null) {
                objectOutputStream.writeObject(entry);
                objectOutputStream.reset();
                entry = entry.next;
            }
            objectOutputStream.close();
        } catch (IOException e) {
        }
    }

    public Entry getEntry() {
        if (getFileSize() > 0) {
            try {
                ObjectInputStream objectInputStream = new ObjectInputStream(Files.newInputStream(path));
                Entry entry;
                while (true) {
                    entry = (Entry) objectInputStream.readObject();
                    if (objectInputStream.available() > 0)
                        entry.next = entry;
                    else break;
                }
                return entry;
            } catch (IOException e) {
            } catch (ClassNotFoundException e) {
            }
        }
        return null;
    }

    public void remove() {
        try {
            Files.delete(path);
        } catch (IOException e) {
        }
    }
}
