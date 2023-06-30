package it.collections.least.recently.used.cache;

import java.util.HashMap;
import java.util.Map;
public class LeastRecentlyUsedCache<K, V> {

    public static void main(String[] args) {

        Map<Integer, Node<Integer, String>> myMap = new HashMap<>();

        LeastRecentlyUsedCache<Integer, String> l = new LeastRecentlyUsedCache<>(myMap, null, null, 5);
        l.put(1, "Hello World");
        l.put(2, "Greeting");
        l.put(3, "Salut");
        l.get(2);
        l.get(1);
        l.put(4, "Ciao");
        l.put(5, "Hi all");
        l.get(5);
        l.get(4);

        l.put(6, "Boooom");
        l.put(1, "Cazzz");

        System.out.println("###############");
    }

    private final Map<K, Node<K, V>> myLeastRecentlyUsedCache;
    private Node<K, V> first;
    private Node<K, V> last;
    private Integer capacity;


    public LeastRecentlyUsedCache(Map<K, Node<K, V>> myLeastRecentlyUsedCache, Node<K, V> first, Node<K, V> last, Integer capacity) {
        this.myLeastRecentlyUsedCache = myLeastRecentlyUsedCache;
        this.first = first;
        this.last = last;
        this.capacity = capacity;
    }

    public void put(K key, V value) {
        Node<K,V> maybeNode = myLeastRecentlyUsedCache.get(key);

        if(maybeNode != null) {
            maybeNode.setElement(value);
            return;
        }

        Node<K, V> myNode = new Node<>();
        myNode.setElement(value);
        myNode.setKey(key);

        myLeastRecentlyUsedCache.put(key, myNode);

        if(this.capacity == 0) {
            remove(this.last);
            myLeastRecentlyUsedCache.remove(this.last.getKey());
        }

        if(first == null && last == null) {
            first = last = myNode;
        } else {
            last.setNext(myNode);
            myNode.setPrev(last);
            this.last = myNode;
        }
        if(this.capacity > 0) {
            this.capacity--;
        }
    }

    public V get(K key) {
        Node<K, V> myNode = myLeastRecentlyUsedCache.get(key);

        if(myNode == null) {
            return null;
        }

        remove(myNode);
        addToHead(myNode);

        return myNode.getElement();
    }

    public void remove(Node<K, V> myNode) {
        if(myNode == first) {
            myNode.getNext().setPrev(null);
            this.first = myNode.getNext();
        } else if(myNode == last) {
            myNode.getPrev().setNext(null);
            this.last = myNode.getPrev();
        } else {
            myNode.getPrev().setNext(myNode.getNext());
            myNode.getNext().setPrev(myNode.getPrev());
        }
    }

    public void addToHead(Node<K, V> myNode) {
        myNode.setNext(this.first);
        myNode.setPrev(null);

        this.first.setPrev(myNode);
        this.first = myNode;
    }
}
