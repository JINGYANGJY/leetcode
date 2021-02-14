package amazon.HashTable;

import java.util.List;

public class MyHashMap<K, V> {
    /*
    put
    get
    delete
    Entry:
        Key key
        Value value
    structure
        Array fix size + list
        [] ______________
        [] ______________
        [] ______________
        []
        []
        index = hash(key) % size;
        compare(key)
        index of array
            get the entry's list
        for loop list, compare the key
            entry
        get:
            1. get hashcode(key)
            2. index
            3. check list has such entry
                if true, return value
                false, return null
        put:
            1. get hashcode(key)
            2. index
            3. check list has such entry
                if true, replace
                false, add it into the list
        int hash(Key key)
        int getIndex(Key key)
        compare(Key key1, Key key2)

        put
        get
        class Entry<K, V> {
            K key;
            V value;
            public Entry(K key, V value) {
                this.key = k;
                thi.value = v;
            }
        }
     */
    class Entry<K, V> {
        K key;
        V value;
        Entry<K, V> next;
        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return this.key;
        }
        public V getValue() {
            return this.value;
        }

        public void setKey(K key) {
            this.key = key;
        }
        public void setValue(V val) {
            this.value = val;
        }
    }


    Entry<K, V>[] array;
    int size;
    float loadFactor;
    public static final int DEFAULT_SIZE = 16;
    public static final float DEFAULT_LOAD_FACTOR = 0.75f;

    public MyHashMap() {
        this.size = DEFAULT_SIZE;
        this.loadFactor = DEFAULT_LOAD_FACTOR;
        this.array = new Entry[size];
    }
    private int hash(K key) {
        return key.hashCode() & 0x7FFFFFFF; // positive
    }
    private int getIndex(K key) {
        return hash(key) % array.length;
    }

    private boolean compareKey(K key1, K key2) {
        if (key1 == null && key2 == null) {
            return true;
        }
        if (key1 == null || key2 == null) {
            return false;
        }
        return key1.equals(key2);
    }
    public boolean containsKey(K key) {
        int index = getIndex(key);
        Entry<K, V> head = array[index];
        Entry<K, V> node = head;
        while (node != null) {
            if (compareKey(node.key, key)) {
                return true;
            }
            node = node.next;
        }
        return false;
    }
    public V get(K key) {
        int index = getIndex(key);
        Entry<K, V> head = array[index];
        Entry<K, V> node = head;
        while (node != null) {
            if (compareKey(node.key, key)) {
                V res = node.value;
                return res;
            }
            node = node.next;
        }
        return null;
    }

    public V put(K key, V value) {
        int index = getIndex(key);
        Entry<K, V> head = array[index];
        Entry<K, V> node = head;
        while (node != null) {
            if (compareKey(node.key, key)) {
                V res = node.value;
                node.value = value;
                return res;
            }
            node = node.next;
        }
        Entry<K, V> newNode = new Entry<>(key, value);
        newNode.next = head;
        array[index] = newNode;
        size++;
        if (needRehashing()) {
           // rehashing();
        }
        return null;
    }

    private boolean needRehashing() {
        return (size + 0.0f) / array.length >= loadFactor;
    }

    public K remove(K key) {
        int index = getIndex(key);
        Entry<K, V> head = array[index];
        Entry<K, V> node = head;
        Entry<K, V> prev = null;
        while (node != null) {
            if (compareKey(node.key, key)) {
                size--;
                if (prev == null) {
                    array[index] = null;
                    return node.key;
                }
                prev.next = node.next;
                return node.key;
            }
            prev = node;
            node = node.next;
        }
        return null;
    }



}
