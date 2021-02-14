package amazon.Design;

import java.util.HashMap;
import java.util.Map;

public class Leet146_LRUCache {
    /*
    ❌ 1. update 有更新的情况
least recently used
    timeline sequence
    from head to tail -> recently used
    get()
        if existed
            return value
            move To head
        else
            null
    put()
        if existed
            move to head
        else
            if size < capacity
                add To head
            else
                move tail
                add to head



    Data structure
        sorted:
            1. arraylist
                get: O(n)
                put: O(n)
            -----------------------------
            optimizations
                -> O(1)
                Doubly linked list + HashMap
                get: O(1)
                put: O(1)

        key -> value : Map
*/
    class DDL {
        int key;
        int val;
        DDL next;
        DDL prev;
        public DDL(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }

    Map<Integer, DDL> map;
    int capacity;
    DDL head;
    DDL tail;
    public Leet146_LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>();
        head = new DDL(Integer.MIN_VALUE, Integer.MIN_VALUE);
        tail = new DDL(Integer.MAX_VALUE, Integer.MAX_VALUE);
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        if (map.containsKey(key)) {
            moveToHead(key);
            return map.get(key).val;
        } else {
            return -1;
        }
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            map.get(key).val = value;
            moveToHead(key);
        } else {
            if (map.size() < capacity) {
                addToHead(key, value);
            } else {
                removeTail();
                addToHead(key, value);
            }
        }
    }

    private void moveToHead(int key) {
        DDL node = map.get(key);
        DDL prev = node.prev;
        DDL next = node.next;
        prev.next = next;
        next.prev = prev;
        DDL headNext = head.next;
        head.next = node;
        node.prev = head;
        node.next = headNext;
        headNext.prev = node;
    }
    private void removeTail() {
        DDL deleteNode = tail.prev;
        deleteNode.prev.next = tail;
        tail.prev = deleteNode.prev;
        map.remove(deleteNode.key);
    }
    private void addToHead(int key, int value) {
        DDL newNode = new DDL(key, value);
        DDL headNext = head.next;
        head.next = newNode;
        newNode.prev = head;
        newNode.next = headNext;
        headNext.prev = newNode;
        map.put(key, newNode);
    }
}
