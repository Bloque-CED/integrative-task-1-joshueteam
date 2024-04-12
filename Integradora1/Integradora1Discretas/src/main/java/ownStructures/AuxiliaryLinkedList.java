package ownStructures;

public class AuxiliaryLinkedList <T>{
    private Node<T> head;
    private int size;

    public AuxiliaryLinkedList() {
        head = null;
        size = 0;
    }

    public void addFirst(T item) {
        head = new Node<>(item, head);
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

    private static class Node<T> {
        T data;
        Node<T> next;

        Node(T data, Node<T> next) {
            this.data = data;
            this.next = next;
        }
    }
}
