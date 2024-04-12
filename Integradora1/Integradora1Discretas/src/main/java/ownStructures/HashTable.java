package ownStructures;
import java.util.ArrayList;

public class HashTable<K,V> implements IHashTable<K,V>{

    private static final int NUM_BUCKETS = 10;
    private Object[] buckets;
    private int size;

    public HashTable() {
        buckets = new Object[NUM_BUCKETS];
        size = 0;
    }

    private int getBucketIndex(K key) {
        int hashCode = key.hashCode();
        int index = hashCode % NUM_BUCKETS;
        return index < 0 ? index * -1 : index;
    }

    public void put(K key, V value) {
        int bucketIndex = getBucketIndex(key);
        Node head = (Node) buckets[bucketIndex];

        while (head != null) {
            if (head.key.equals(key)) {
                head.value = value;
                return;
            }
            head = head.next;
        }

        size++;
        head = (Node) buckets[bucketIndex];
        Node newNode = new Node(key, value);
        newNode.next = head;
        buckets[bucketIndex] = newNode;
    }

    public V get(K key) {
        int bucketIndex = getBucketIndex(key);
        Node head = (Node) buckets[bucketIndex];

        while (head != null) {
            if (head.key.equals(key)) {
                return head.value;
            }
            head = head.next;
        }
        return null;
    }

    public void remove(K key) {
        int bucketIndex = getBucketIndex(key);
        Node head = (Node) buckets[bucketIndex];
        Node prev = null;

        while (head != null) {
            if (head.key.equals(key)) {
                if (prev != null) {
                    prev.next = head.next;
                } else {
                    buckets[bucketIndex] = head.next;
                }
                size--;
                return;
            }
            prev = head;
            head = head.next;
        }
    }

    public int size() {
        return size;
    }

    private class Node {
        K key;
        V value;
        Node next;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }
    }













    /**
    private ArrayList<ArrayList<K>> keysArray;
    private ArrayList<ArrayList<V>> valuesArray;
    private int numBuckets;
    private int size;

    public HashTable() {
        keysArray = new ArrayList<>();
        valuesArray = new ArrayList<>();
        numBuckets = 10;
        size = 0;

        for (int i = 0; i < numBuckets; i++) {
            keysArray.add(new ArrayList<>());
            valuesArray.add(new ArrayList<>());
        }
    }

    private int getBucketIndex(K key) {
        int hashCode = key.hashCode();
        int index = hashCode % numBuckets;
        return index < 0 ? index * -1 : index;
    }

    @Override
    public void put(K key, V value) {
        int bucketIndex = getBucketIndex(key);
        ArrayList<K> keysBucket = keysArray.get(bucketIndex);
        ArrayList<V> valuesBucket = valuesArray.get(bucketIndex);

        int keyIndex = keysBucket.indexOf(key);
        if (keyIndex != -1) {
            valuesBucket.set(keyIndex, value);
        } else {
            keysBucket.add(key);
            valuesBucket.add(value);
            size++;
        }
    }

    @Override
    public V get(K key) {
        int bucketIndex = getBucketIndex(key);
        ArrayList<K> keysBucket = keysArray.get(bucketIndex);
        ArrayList<V> valuesBucket = valuesArray.get(bucketIndex);

        int keyIndex = keysBucket.indexOf(key);
        if (keyIndex != -1) {
            return valuesBucket.get(keyIndex);
        }
        return null;
    }

    @Override
    public void remove(K key) {
        int bucketIndex = getBucketIndex(key);
        ArrayList<K> keysBucket = keysArray.get(bucketIndex);
        ArrayList<V> valuesBucket = valuesArray.get(bucketIndex);

        int keyIndex = keysBucket.indexOf(key);
        if (keyIndex != -1) {
            keysBucket.remove(keyIndex);
            valuesBucket.remove(keyIndex);
            size--;
        }
    }

    @Override
    public int size() {
        return size;
    }

    */


}
