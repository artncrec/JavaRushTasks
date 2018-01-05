package com.javarush.task.task20.task2028;

import java.io.Serializable;
import java.util.AbstractList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/* 
Построй дерево(1)
*/
public class CustomTree extends AbstractList<String> implements Cloneable, Serializable {
    Entry<String> root = new Entry<>("0");
    List<Entry<String>> list = new LinkedList<>();

    public static void main(String[] args) {
        List<String> list = new CustomTree();
        for (int i = 1; i < 16; i++) {
            list.add(String.valueOf(i));
        }
        System.out.println("Expected 3, actual is " + ((CustomTree) list).getParent("8"));
        System.out.println(list.size());
        list.remove("4");
        System.out.println("Expected null, actual is " + ((CustomTree) list).getParent("13"));
        System.out.println(list.size());
        list.add("40");
        list.add("41");
        list.add("42");
        System.out.println("Expected null, actual is " + ((CustomTree) list).getParent("40"));
        System.out.println("Expected null, actual is " + ((CustomTree) list).getParent("41"));
        System.out.println("Expected null, actual is " + ((CustomTree) list).getParent("42"));
        System.out.println(list.size());
    }

    public CustomTree() {
        list.add(root);
    }

    @Override
    public boolean add(String s) {
        Entry entry = new Entry(s);
        for (int i = 0; i < list.size(); i++) {
            Entry entryFromList = list.get(i);
            entryFromList.checkChildren();
            if (entryFromList.isAvailableToAddChildren()){
                if (entryFromList.availableToAddLeftChildren){
                    entry.parent = entryFromList;
                    entryFromList.leftChild = entry;
                    list.add(entry);
                    break;
                }
                else if (entryFromList.availableToAddRightChildren){
                    entry.parent = entryFromList;
                    entryFromList.rightChild = entry;
                    list.add(entry);
                    break;
                }
            }
        }
        return true;
    }

    @Override
    public boolean remove(Object o) {
        for (int i = 0; i < list.size(); i++) {
            Entry entry = list.get(i);
            if (entry.elementName.equals((String)o)) {
                if (entry.leftChild != null)
                    remove(entry.leftChild.elementName);
                if (entry.rightChild != null)
                    remove(entry.rightChild.elementName);
                if (entry.parent.leftChild != null && entry.parent.leftChild.elementName.equals(entry.elementName))
                    entry.parent.leftChild = null;
                else if (entry.parent.rightChild != null && entry.parent.rightChild.elementName.equals(entry.elementName))
                    entry.parent.rightChild = null;
                list.remove(entry);
                break;
            }
        }
        return true;
    }

    @Override
    public int size() {
        return list.size()-1;
    }

    public String getParent(String s) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).elementName.equals(s))
                return list.get(i).parent.elementName;
        }
        return null;
    }

    static class Entry<T> implements Serializable {
        String elementName;
        int lineNumber;
        boolean availableToAddLeftChildren, availableToAddRightChildren;
        Entry<T> parent, leftChild, rightChild;

        public Entry(String elementName) {
            this.elementName = elementName;
            availableToAddLeftChildren = true;
            availableToAddRightChildren = true;
        }

        void checkChildren() {
            if (leftChild != null)
                availableToAddLeftChildren = false;
            if (rightChild != null)
                availableToAddRightChildren = false;
        }

        boolean isAvailableToAddChildren() {
            return availableToAddRightChildren || availableToAddLeftChildren;
        }
    }

    @Override
    public String get(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String set(int index, String element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void add(int index, String element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String remove(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<String> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected void removeRange(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(int index, Collection<? extends String> c) {
        throw new UnsupportedOperationException();
    }
}