package ownStructures.Stack;

import ownStructures.Stack.Node;

public class AuxiliaryDoubleLinkedList<E>{
    Node<E> head;
    Node<E> tail;
    int size;

    public AuxiliaryDoubleLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    public void addLast(E item) {
        Node<E> newNode = new Node<>(item);
        if (tail == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        size++;
    }

    public E removeFirst() {
        if (head == null) return null;
        Node<E> temp = head;
        head = head.next;
        if (head == null) {
            tail = null;
        } else {
            head.prev = null;
        }
        size--;
        return temp.data;
    }

    public E first() {
        if (head == null) return null;
        return head.data;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }
}



