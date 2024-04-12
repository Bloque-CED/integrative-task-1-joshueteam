package ownStructures;

import java.util.Comparator;

public class PriorityQueue<T extends Comparable<T>> implements IPriorityQueue<T> {
    private NodeP<T> front;
    private Comparator<T> comparator;

    public PriorityQueue() {
        this.front = null;
    }
    
    @Override
    public void add(Player player) {
        if (heapSize == heap.length) {
            System.out.println("The maximum number of players has been reached!");
        }
        heap[heapSize] = player;
        int currentPosition = heapSize;
        heapSize++;
        // Proceso para mantener la propiedad de la cola de prioridad:
        while (currentPosition != 0 && player.getPriority() > heap[(currentPosition - 1) / 2].getPriority()) {
            heap[currentPosition] = heap[(currentPosition - 1) / 2];
            currentPosition = (currentPosition - 1) / 2;
        }
        heap[currentPosition] = player;
        System.out.println("True");
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
