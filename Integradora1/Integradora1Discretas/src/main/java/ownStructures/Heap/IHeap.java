package ownStructures.Heap;

import java.util.List;

public interface IHeap<T> {
    boolean isEmpty();
    void enqueue(NodePQ<T> item);
    NodePQ<T> dequeue();
    NodePQ<T> peek();
    int size();
    void remove(NodePQ<T> item);
    List<NodePQ<T>> getHeap();
}