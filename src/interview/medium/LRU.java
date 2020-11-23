package interview.medium;
/*
Design a data structure that follows the constraints of a Least Recently Used (LRU) cache.

Implement the LRUCache class:

LRUCache(int capacity) Initialize the LRU cache with positive size capacity.
int get(int key) Return the value of the key if the key exists, otherwise return -1.
void put(int key, int value) Update the value of the key if the key exists. Otherwise, add the key-value pair to the cache. If the number of keys exceeds the capacity from this operation, evict the least recently used key.
Follow up:
Could you do get and put in O(1) time complexity?
 */

import java.util.HashMap;
import java.util.Map;

class LRU {
    static class DDL {
        int key;
        int value;
        DDL next;
        DDL prev;
        public DDL(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }
    /*
    least recently used
    sorted by add sequence
    get(key) -- search -- O(1) -- map
    put(key, value) -- store object -- sorted map according to add sequence

    data structure
        map
        sorted the entry by adding sequence
        - arraylist add O(1) delete O(n)
        - linked list  add O(1) delete O(n)
        - optimization O(1)
                doubly linked list

    put
       if (exist) {
            remove(node);
            update node.valu;
            add(node)
       } else {
            if >= capacity
                remove(tail.prev)
            add(node)
       }
   get
         1. remove
         2. add
         3. return
    Time complexity: put(key, value) O(1)
          get(key) O(1)
    Space: O(capacity)
     */
    private int capacity;
    Map<Integer,DDL> map;
    DDL head = new DDL(Integer.MAX_VALUE, Integer.MAX_VALUE);
    DDL tail = new DDL(Integer.MIN_VALUE, Integer.MIN_VALUE);

    public LRU(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>();
        head.next = tail;
        tail.prev = head;
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            DDL node = map.get(key);
            remove(node);
            node.value = value;
            add(node);
        } else {
            if (map.size() >= capacity) {
                map.remove(tail.prev.key);
                remove(tail.prev);
            }
            DDL node = new DDL(key, value);
            add(node);
            map.put(key, node);
        }
    }
    public int get(int key) {
        if(map.containsKey(key)) {
            DDL node = map.get(key);
            remove(node);
            add(node);
            return node.value;
        }
        return -1;
    }

    private void add(DDL node) {
        DDL next = head.next;
        head.next = node;
        node.next = next;
        node.prev = head;
        next.prev = node;
    }

    private void remove(DDL node) {
        DDL prev = node.prev;
        DDL next = node.next;
        prev.next = next;
        next.prev = prev;
    }

    public static void main(String[] args) {
        // capacity 3
        // put(1, 1) (2, 2) (3, 3) (4, 4)
        // get(1)  -1
        // get(3)  3
        // put(5, 5)
        // get(4)
        System.out.println(Math.sqrt(Double.MAX_VALUE));
        LRU lru = new LRU(3);
        lru.put(1, 1);
        lru.put(2, 2);
        lru.put(3, 3);
        lru.put(4, 4);
        System.out.println(lru.get(1));
        System.out.println(lru.get(3));
        lru.put(5, 5);
        System.out.println(lru.get(2));

    }
}
