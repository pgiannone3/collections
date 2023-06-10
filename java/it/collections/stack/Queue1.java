package it.collections.stack;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class Queue1 {

    private final Integer DEFAULT_CAPACITY = 10;
    private Object[] STACK;
    private int size = 0;


    public Queue1(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("Only positive numbers are allowed!");
        }

        STACK = new Object[capacity];
    }

    public Queue1() {
        STACK = new Object[DEFAULT_CAPACITY];
    }

    public boolean isClear() {
        return size == 0;
    }

    public void offer(Object aVal) {
        if(size == STACK.length) {
            STACK = Arrays.copyOf(STACK, size + DEFAULT_CAPACITY);
        }

        STACK[size] = aVal;
        size++;
    }

    public Object poll() {
        if(isClear()) {
            throw new NoSuchElementException();
        }

        var a = STACK[0];
        STACK = Arrays.copyOfRange(STACK, 1, STACK.length);
        size--;
        return a;
    }
}
