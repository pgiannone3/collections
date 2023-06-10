package it.collections.linkedlist;

public class MyLinkedList<T> {

    private int size;
    private Node<T> first;
    private Node<T> last;

    public static void main(String[] args) {
        MyLinkedList<Integer> myLinkedList = new MyLinkedList<>();
        myLinkedList.add(1);
        myLinkedList.add(2);
        myLinkedList.add(3);
        myLinkedList.add(4);
        myLinkedList.add(5);
        myLinkedList.remove(4);

        System.out.println("Contains 2? " + myLinkedList.contains(2));
        System.out.println("Contains 5? " + myLinkedList.contains(5));
        System.out.println("Get 3rd element? " + myLinkedList.get(10));
    }

    public int size() {
        return size;
    }

    public boolean contains(T value) {

        for (Node<T> aNode = first; aNode != null; aNode = aNode.getNext()) {
            if (aNode.getValue() == value) {
                return true;
            }
        }
        return false;
    }

    public T get(int index) {

        int counter = 0;
        for (Node<T> aNode = first; aNode != null; aNode = aNode.getNext()) {
            if(counter == index) {
                return aNode.getValue();
            }
            counter++;

        }
        return null;
    }

    public boolean add(T value) {
        Node<T> l = last;
        Node<T> newNode = new Node<>(value, last, null);

        last = newNode;

        if (l == null) {
            first = newNode;
        } else {
            l.setNext(newNode);
        }
        size = size + 1;
        return true;
    }

    public void remove(T value) {
        for (Node<T> aNode = first; aNode != null; aNode = aNode.getNext()) {

            if (aNode.getValue().equals(value)) {
                Node<T> prev = aNode.getPrevious();
                Node<T> next = aNode.getNext();

                if (aNode == first) {
                    next.setPrevious(null);
                    first = next;
                } else if (aNode == last) {
                    prev.setNext(null);
                    last = prev;
                } else {
                    prev.setNext(next);
                    next.setPrevious(prev);
                }
                size = size - 1;
                break;
            }
        }
    }
}