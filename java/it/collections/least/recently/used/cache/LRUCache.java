package it.collections.least.recently.used.cache;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {

    public static void main(String[] args) {

        LRUCache lruCache = new LRUCache(2);
        System.out.println(lruCache.get(2));
        lruCache.put(2, 6);
        System.out.println(lruCache.get(1));
        lruCache.put(1, 5);
        lruCache.put(1, 2);
        System.out.println(lruCache.get(1));
        System.out.println(lruCache.get(2));

    }

    private Map<Integer, MyNode> myLeastRecentlyUsedCache;
    private MyNode first;
    private MyNode last;
    private Integer capacity;

    public LRUCache(int capacity) {
        Map<Integer, MyNode> myMap = new HashMap<>();

        this.myLeastRecentlyUsedCache = myMap;
        this.first = null;
        this.last = null;
        this.capacity = capacity;
    }

    public void put(int key, int value) {
        MyNode maybeNode = this.myLeastRecentlyUsedCache.get(key);

        if(maybeNode != null) {
            maybeNode.element = value;
            remove(maybeNode);
            addToHead(maybeNode);
            return;
        }

        MyNode myNode = new MyNode();
        myNode.element = value;
        myNode.key = key;

        if(this.capacity == 0) {
            myLeastRecentlyUsedCache.remove(this.last.key);
            remove(this.last);
        }

        if(first == null && last == null) {
            first = last = myNode;
        } else {
            addToHead(myNode);
        }
        if(this.capacity > 0) {
            this.capacity--;
        }
        myLeastRecentlyUsedCache.put(key, myNode);
    }

    public int get(int key) {
        MyNode myNode = myLeastRecentlyUsedCache.get(key);

        if(myNode == null) {
            return -1;
        }

        remove(myNode);
        addToHead(myNode);

        return myNode.element;
    }

    private void remove(MyNode myNode) {
        if(first == last) {
            return;
        }

        if(myNode == first) {
            myNode.next.prev = null;
            this.first = myNode.next;
        } else if(myNode == last) {
            myNode.prev.next = null;
            this.last = myNode.prev;
        } else {
            myNode.prev.next = myNode.next;
            myNode.next.prev = myNode.prev;
        }
    }

    private void addToHead(MyNode myNode) {
        myNode.next = this.first;
        myNode.prev = null;

        this.first.prev = myNode;
        this.first = myNode;
    }

}

class MyNode {

    public int key;
    public int element;
    public MyNode next;
    public MyNode prev;

}
