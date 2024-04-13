package ownStructures.Heap;

import java.util.ArrayList;
import java.util.List;

public class PriorityQueueH<T> implements IHeap<T> {
    private List<NodePQ<T>> heap;
    private boolean isMinHeap;

    public PriorityQueueH(boolean isMinHeap) {
        this.isMinHeap = isMinHeap;
        heap = new ArrayList<>();
    }

    public boolean isEmpty() {
        return heap.isEmpty();
    }

    public void enqueue(NodePQ<T> item) {
        heap.add(item);
        heapSort();
    }

    public NodePQ<T> dequeue() {
        if (isEmpty()) {
            return null;
        }

        NodePQ<T> top = heap.get(0);
        NodePQ<T> last = heap.remove(heap.size() - 1);

        if (!isEmpty()) {
            heap.set(0, last);
            if (isMinHeap) {
                minHeapify(heap.size(), 0);
            } else {
                maxHeapify(heap.size(), 0);
            }
        }

        return top;
    }

    public void heapSort() {
        int n = heap.size();

        if (isMinHeap) {
            for (int i = n / 2 - 1; i >= 0; i--) {
                minHeapify(n, i);
            }
        } else {
            for (int i = n / 2 - 1; i >= 0; i--) {
                maxHeapify(n, i);
            }
        }

        List<NodePQ<T>> sortedList = new ArrayList<>();
        for (int i = n - 1; i >= 0; i--) {
            NodePQ<T> item = heap.get(0);
            sortedList.add(item);
            heap.set(0, heap.get(i));
            if (isMinHeap) {
                minHeapify(i, 0);
            } else {
                maxHeapify(i, 0);
            }
        }

        heap.clear();
        heap.addAll(sortedList);
    }

    private void minHeapify(int n, int i) {
        int smallest = i;
        int leftChild = 2 * i + 1;
        int rightChild = 2 * i + 2;

        if (leftChild < n && heap.get(leftChild).getPriority() < heap.get(smallest).getPriority()) {
            smallest = leftChild;
        }

        if (rightChild < n && heap.get(rightChild).getPriority() < heap.get(smallest).getPriority()) {
            smallest = rightChild;
        }

        if (smallest != i) {
            NodePQ<T> temp = heap.get(i);
            heap.set(i, heap.get(smallest));
            heap.set(smallest, temp);

            minHeapify(n, smallest);
        }
    }

    private void maxHeapify(int n, int i) {
        int largest = i;
        int leftChild = 2 * i + 1;
        int rightChild = 2 * i + 2;

        if (leftChild < n && heap.get(leftChild).getPriority() > heap.get(largest).getPriority()) {
            largest = leftChild;
        }

        if (rightChild < n && heap.get(rightChild).getPriority() > heap.get(largest).getPriority()) {
            largest = rightChild;
        }

        if (largest != i) {
            NodePQ<T> temp = heap.get(i);
            heap.set(i, heap.get(largest));
            heap.set(largest, temp);

            maxHeapify(n, largest);
        }
    }

    public NodePQ<T> peek() {
        if (isEmpty()) {
            return null;
        }
        return heap.get(0);
    }

    public int size() {
        return heap.size();
    }

    public List<NodePQ<T>> getHeap() {
        return heap;
    }

    public void remove(NodePQ<T> item) {
        int index = -1;

        for (int i = 0; i < heap.size(); i++) {
            if (heap.get(i).equals(item)) {
                index = i;
                break;
            }
        }

        if (index != -1) {
            int lastIndex = heap.size() - 1;
            if (index != lastIndex) {
                heap.set(index, heap.get(lastIndex));
                heap.remove(lastIndex);
                heapSort();
            } else {
                heap.remove(index);
            }
        }
    }

    public void assignPriority(){
        heapSort();
        if(isMinHeap){
            heap.get(0).setPriority(heap.get(heap.size()-1).getPriority() + 1);
        } else {
            heap.get(0).setPriority(heap.get(heap.size()-1).getPriority() - 1);
        }
    }

    public void assignPriorityTest(){
        heapSort();
        int size = heap.size();
        if(isMinHeap){
            for (int i = 0; i < size; i++) {
                heap.get(i).setPriority(i + 1);
            }
        } else {
            for (int i = size - 1; i >= 0; i--) {
                heap.get(i).setPriority(size - i);
            }
        }
    }

}