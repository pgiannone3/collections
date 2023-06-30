package it.collections.linkedlist;

public class MyLinkedList<T> {
    public static void main(String[] args) {
        MyLinkedList<Integer> myLinkedList = new MyLinkedList<>();
        myLinkedList.add(1);
        myLinkedList.add(2);
        myLinkedList.add(3);
        myLinkedList.add(4);
        myLinkedList.add(5);
        myLinkedList.moveToFirst(3);
        myLinkedList.moveToFirst(5);
        myLinkedList.moveToFirst(4);
        myLinkedList.remove(1);

        for(Node<Integer> aNode = myLinkedList.first; aNode != null; aNode = aNode.getNext()) {
            System.out.println(aNode.getValue());
        }
    }

    private int size;
    private Node<T> first;
    private Node<T> last;

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

    public void remove(Node<T> myNode) {
        if(myNode == first) {
            myNode.getNext().setPrevious(null);
            this.first = myNode.getNext();
        } else if(myNode == last) {
            myNode.getPrevious().setNext(null);
            this.last = myNode.getPrevious();
        } else {
            myNode.getPrevious().setNext(myNode.getNext());
            myNode.getNext().setPrevious(myNode.getPrevious());
        }

    }

    public Node<T> remove(T value) {
        for (Node<T> aNode = first; aNode != null; aNode = aNode.getNext()) {

            if (aNode.getValue().equals(value)) {
                remove(aNode);
                size = size - 1;
                return aNode;
            }
        }
        return null;
    }

    public void addToHead(Node<T> myNode) {
        myNode.setNext(this.first);
        this.first.setPrevious(myNode);
        this.first = myNode;
        this.size = this.size + 1;
    }

    public void moveToFirst(T value) {
        Node<T> myNode = remove(value);
        if(myNode == null) {
            return;
        }

        myNode.setNext(this.first);
        myNode.setPrevious(null);

        this.first.setPrevious(myNode);
        this.first = myNode;
    }
}