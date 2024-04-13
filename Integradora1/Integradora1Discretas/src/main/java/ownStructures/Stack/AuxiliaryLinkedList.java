package ownStructures.Stack;

public class AuxiliaryLinkedList <T>{
    private NodeS<T> head;
    private int size;

    public AuxiliaryLinkedList() {
        head = null;
        size = 0;
    }

    public void addFirst(T item) {
        head = new NodeS<>(item, head);
        size++;
    }

    public T removeFirst() {
        if (head == null) return null;
        T item = head.data;
        head = head.next;
        size--;
        return item;
    }

    public T getFirst() {
        if (head == null) return null;
        return head.data;
    }

    public int size() {
        return size;
    }


}
