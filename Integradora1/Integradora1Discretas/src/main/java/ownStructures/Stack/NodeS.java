package ownStructures.Stack;

public class NodeS<T> {
    T data;
    NodeS<T> next;

    NodeS(T data, NodeS<T> next) {
        this.data = data;
        this.next = next;
    }

}
