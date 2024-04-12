package ownStructures;

public class NodeP<T> {
    T data;
    int priority;
    NodeP<T> next;

    public NodeP(T data, int priority) {
        this.data = data;
        this.priority = priority;
        this.next = null;
    }
}
