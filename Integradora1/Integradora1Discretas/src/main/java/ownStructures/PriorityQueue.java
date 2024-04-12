package ownStructures;

public class PriorityQueue<T extends Comparable<T>> implements IPriorityQueue<T> {
    private NodeP<T> front;

    public PriorityQueue() {
        this.front = null;
    }


    public void offer(T element) {
        int priority = (element).compareTo(element);
        NodeP<T> newNode = new NodeP<>(element, priority);
        if (front == null || priority < front.priority) {
            newNode.next = front;
            front = newNode;
        } else {
            NodeP<T> current = front;
            while (current.next != null && priority >= current.next.priority) {
                current = current.next;
            }
            newNode.next = current.next;
            current.next = newNode;
        }
    }

    public T poll() {
        if (isEmpty()) {
            return null;
        }
        T data = front.data;
        front = front.next;
        return data;
    }

    public T peek() {
        return front != null ? front.data : null;
    }

    public boolean isEmpty() {
        return front == null;
    }

    public int size() {
        int size = 0;
        NodeP<T> current = front;
        while (current != null) {
            size++;
            current = current.next;
        }
        return size;
    }


}
