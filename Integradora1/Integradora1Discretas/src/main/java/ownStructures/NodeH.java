package ownStructures;

public class NodeH<K,V> {
    K key;
    V value;
    NodeH<K,V> next;

    NodeH(K key, V value) {
        this.key = key;
        this.value = value;
        this.next = null;
    }

}
