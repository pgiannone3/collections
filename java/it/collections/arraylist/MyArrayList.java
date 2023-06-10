package it.collections.arraylist;

import java.security.InvalidParameterException;
import java.util.Arrays;

public class MyArrayList<E> {

    public static void main(String[] args) {
        MyArrayList<String> a = new MyArrayList<>();
        a.add("Hello World1");
        a.add("Hello World2");
        a.add("Hello World3");
        a.add("Hello World4");
        a.add("Hello World5");
        a.add("Hello World6");
        a.add("Hello World7");
        a.add("Hello World8");
        a.add("Hello World9");
        a.add("Hello World10");
        a.add("Hello World11");

        a.get(0);
        a.get(1);
        a.remove(2);
        a.remove("Hello World8");
        System.out.println("YUPPI!");
    }

    private static final int DEFAULT_CAPACITY = 10;
    private Object [] elementData;
    private int size;

    public MyArrayList(int capacity) {
        if(capacity > 0) {
            elementData = new Object[capacity];
        } else if(capacity == 0) {
            elementData = new Object[] {};
        } else {
            throw new InvalidParameterException("Invalid capacity!");
        }
    }

    public MyArrayList() {
        elementData = new Object[DEFAULT_CAPACITY];
    }

    public int size() {
        return size;
    }

    public E get(int index) {
        if(index < size()) {
          return (E) elementData[index];
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    public void add(E anElement) {
        if(elementData.length == size) {
            elementData = Arrays.copyOf(elementData, size + 10);
        }

        elementData[size] = anElement;
        size++;
    }

    public E remove(int index) {
        if(index < size) {
            E oldValue = (E) elementData[index];
            size = size - 1;
            System.arraycopy(elementData, index + 1, elementData, index, size);
            return oldValue;
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    public boolean remove(E anElement) {
        for (int i = 0; i < size; i++) {
                if (elementData[i] != null && elementData[i] == anElement) {
                    remove(i);
                    break;
                }
            }

        return true;
    }
}
