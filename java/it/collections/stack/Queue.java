package it.collections.stack;

import java.util.NoSuchElementException;

public class Queue<T> {

    private Node<T> first;
    private Node<T> last;

    public void offer(T val) {
        Node<T> l = last;
        Node<T> newNode = new Node<>(val, null, l);

        if(first == null) {
        //stack empty
            first = newNode;
            last = newNode;
        } else {
            last = newNode;
            l.setNext(newNode);
        }
    }

    public T pull() {
        if(first == null) {
            throw new NoSuchElementException();
        }

        Node<T> f = first;
        first = f.getNext();
        first.setPrev(null);
        return f.getValue();
    }

    public T peek() {
        if(first == null) {
            throw new NoSuchElementException();
        }

        return first.getValue();
    }

    public boolean isClear() {
        return first == null;
    }
}
