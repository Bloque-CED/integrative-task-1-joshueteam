package ownStructures;

public interface IPriorityQueue<T> {
    void offer(T element);

    T poll();


    T peek();


    boolean isEmpty();

    int size();
}
