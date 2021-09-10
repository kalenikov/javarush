package com.javarush.task.task20.task2028;

import java.io.Serializable;
import java.util.AbstractList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/* 
Построй дерево(1)
*/
public class CustomTree extends AbstractList<String> implements Serializable, Cloneable {

    Entry<String> root;

    public CustomTree() {
        this.root = new Entry<>("0");
    }

    @Override
    public boolean add(String elementName) {
        if (elementName == null) return false;
        Queue<Entry<String>> que = new LinkedList<>();
        que.offer(root);
        while (!que.isEmpty()) {
            Entry<String> el = que.poll();
            if (el.isAvailableToAddChildren()) {
                if (el.availableToAddLeftChildren) {
                    el.leftChild = new Entry<>(elementName);
                    el.leftChild.parent = el;
                    el.checkChildren();
                    return true;
                }
                if (el.availableToAddRightChildren) {
                    el.rightChild = new Entry<>(elementName);
                    el.rightChild.parent = el;
                    el.checkChildren();
                    return true;
                }
            }
            if (el.leftChild != null) que.offer(el.leftChild);
            if (el.rightChild != null) que.offer(el.rightChild);
        }
        return false;
    }

    @Override
    public int size() {
        Queue<Entry<String>> que = new LinkedList<>();
        que.offer(root);
        int count = -1;
        while (!que.isEmpty()) {
            Entry<String> el = que.poll();
            count++;
            if (el.leftChild != null) que.offer(el.leftChild);
            if (el.rightChild != null) que.offer(el.rightChild);
        }
        return count;
    }

    public String getParent(String nameChild) {
        if (nameChild == null) return "not found";
        Queue<Entry<String>> que = new LinkedList<>();
        que.offer(root);
        while (!que.isEmpty()) {
            Entry<String> el = que.poll();
            if (el.elementName.equals(nameChild)) return el.parent.elementName;
            if (el.leftChild != null) que.offer(el.leftChild);
            if (el.rightChild != null) que.offer(el.rightChild);
        }
        return "not found";
    }

    public boolean remove(Object o) {
        if (o == null) throw new UnsupportedOperationException();
        String nameObjectRemove;
        try {
            nameObjectRemove = (String) o;
        } catch (Exception ex) {
            throw new UnsupportedOperationException();
        }
        Queue<Entry<String>> que = new LinkedList<>();
        que.offer(root);
        while (!que.isEmpty()) {
            Entry<String> el = que.poll();
            el.checkChildren();
            if (el.parent != null) {
                if (el.parent.leftChild != null) {
                    if (el.parent.leftChild.elementName.equals(nameObjectRemove)) {
                        el.parent.leftChild = null;
                        return true;
                    }
                }
                if (el.parent.rightChild != null) {
                    if (el.parent.rightChild.elementName.equals(nameObjectRemove)) {
                        el.parent.rightChild = null;
                        return true;
                    }
                }
            }
            if (el.leftChild != null) que.offer(el.leftChild);
            if (el.rightChild != null) que.offer(el.rightChild);
        }
        return false;
    }

    static class Entry<T> implements Serializable {
        String elementName;
        int lineNumber;
        boolean availableToAddLeftChildren, availableToAddRightChildren;
        Entry<T> parent, leftChild, rightChild;

        public Entry(String elementName) {
            this.elementName = elementName;
            this.availableToAddLeftChildren = true;
            this.availableToAddRightChildren = true;
        }

        public void checkChildren() {
            if (leftChild != null) availableToAddLeftChildren = false;
            else availableToAddLeftChildren = true;
            if (rightChild != null) availableToAddRightChildren = false;
            else availableToAddRightChildren = true;
        }

        public boolean isAvailableToAddChildren() {
            return availableToAddLeftChildren || availableToAddRightChildren;
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
    public boolean addAll(int index, Collection<? extends String> c) {
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
}
