package it.collections.least.recently.used.cache;

public class Node<K, V> {

    private K key;
    private V element;
    private Node<K, V> next;
    private Node<K, V> prev;

    public V getElement() {
        return element;
    }

    public void setElement(V element) {
        this.element = element;
    }

    public Node<K, V> getNext() {
        return next;
    }

    public void setNext(Node<K, V> next) {
        this.next = next;
    }

    public Node<K, V> getPrev() {
        return prev;
    }

    public void setPrev(Node<K, V> prev) {
        this.prev = prev;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public K getKey() {
        return key;
    }
}
