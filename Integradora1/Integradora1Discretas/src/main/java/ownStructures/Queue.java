package ownStructures;

public class Queue<E> implements IQueue<E> {
    AuxiliaryDoubleLinkedList<E> doubleLinkedList;

    public Queue() {
        doubleLinkedList = new AuxiliaryDoubleLinkedList<>();
    }

    @Override
    public void enqueue(E item) {
        doubleLinkedList.addLast(item);
    }

    @Override
    public E dequeue() {
        return doubleLinkedList.removeFirst();
    }

    @Override
    public E front() {
        return doubleLinkedList.first();
    }

    @Override
    public boolean isEmpty() {
        return doubleLinkedList.isEmpty();
    }

    @Override
    public int size() {
        return doubleLinkedList.size();
    }


}
