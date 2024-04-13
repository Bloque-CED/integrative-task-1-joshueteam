package ownStructures.Queue;

public interface IQueue <E>{
    void enqueue(E item);
    E dequeue();
    E front();
    boolean isEmpty();
    int size();
}
