package ownStructures.HashTable;

import java.lang.reflect.Array;

public class HashTable<K,V> implements IHashTable<K,V>{

    private static final int NUM_BUCKETS = 10;
    private NodeH<K,V>[] buckets;
    private int size;

    public HashTable() {
        buckets = (NodeH<K,V>[]) Array.newInstance(NodeH.class, NUM_BUCKETS);
        size = 0;
    }

    private int getBucketIndex(K key) {
        int hashCode = key.hashCode();
        int index = hashCode % NUM_BUCKETS;
        return index < 0 ? index * -1 : index;
    }

    public void put(K key, V value) {
        int bucketIndex = getBucketIndex(key);
        NodeH head =  buckets[bucketIndex];

        while (head != null) {
            if (head.key.equals(key)) {
                head.value = value;
                return;
            }
            head = head.next;
        }

        size++;
        head =  buckets[bucketIndex];
        NodeH newNode = new NodeH(key, value);
        newNode.next = head;
        buckets[bucketIndex] = newNode;
    }

    public V get(K key) {
        int bucketIndex = getBucketIndex(key);
        NodeH head = buckets[bucketIndex];

        while (head != null) {
            if (head.key.equals(key)) {
                return (V) head.value;
            }
            head = head.next;
        }
        return null;
    }

    public void remove(K key) {
        int bucketIndex = getBucketIndex(key);
        NodeH head =  buckets[bucketIndex];
        NodeH prev = null;

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


}
