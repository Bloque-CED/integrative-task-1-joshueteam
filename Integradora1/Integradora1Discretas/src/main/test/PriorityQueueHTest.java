
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ownStructures.Heap.NodePQ;
import ownStructures.Heap.PriorityQueueH;

import static org.junit.jupiter.api.Assertions.*;

public class PriorityQueueHTest {

    private PriorityQueueH<Integer> minHeap;
    private PriorityQueueH<Integer> maxHeap;

    @BeforeEach
    public void setUp() {
        minHeap = new PriorityQueueH<Integer>(true);
        maxHeap = new PriorityQueueH<Integer>(false);
    }

    @Test
    public void testEnqueueAndDequeue() {
        NodePQ<Integer> node1 = new NodePQ<>(1, 10);
        NodePQ<Integer> node2 = new NodePQ<>(2, 5);
        NodePQ<Integer> node3 = new NodePQ<>(3, 15);

        minHeap.enqueue(node1);
        minHeap.enqueue(node2);
        minHeap.enqueue(node3);

        assertEquals(3, minHeap.size());

        NodePQ<Integer> dequeuedNode = minHeap.dequeue();
        assertEquals(2, dequeuedNode.getData());
        assertEquals(5, dequeuedNode.getPriority());
        assertEquals(2, minHeap.size());

        dequeuedNode = minHeap.dequeue();
        assertEquals(1, dequeuedNode.getData());
        assertEquals(10, dequeuedNode.getPriority());
        assertEquals(1, minHeap.size());
    }

    @Test
    public void testPeek() {
        NodePQ<Integer> node1 = new NodePQ<>(1, 20);
        NodePQ<Integer> node2 = new NodePQ<>(2, 15);

        maxHeap.enqueue(node1);
        maxHeap.enqueue(node2);

        NodePQ<Integer> peekedNode = maxHeap.peek();

        assertEquals(1, peekedNode.getData());
        assertEquals(20, peekedNode.getPriority());
        assertEquals(2, maxHeap.size());
    }

    @Test
    public void testRemove() {
        NodePQ<Integer> node1 = new NodePQ<>(1, 10);
        NodePQ<Integer> node2 = new NodePQ<>(2, 5);
        NodePQ<Integer> node3 = new NodePQ<>(3, 15);

        minHeap.enqueue(node1);
        minHeap.enqueue(node2);
        minHeap.enqueue(node3);

        assertEquals(3, minHeap.size());

        minHeap.remove(node2);

        assertEquals(2, minHeap.size());
        assertEquals(1, minHeap.peek().getData());
    }

    @Test
    public void testAssignPriority() {
        NodePQ<Integer> node1 = new NodePQ<>(1, 10);
        NodePQ<Integer> node2 = new NodePQ<>(2, 5);
        NodePQ<Integer> node3 = new NodePQ<>(3, 15);

        minHeap.enqueue(node1);
        minHeap.enqueue(node2);
        minHeap.enqueue(node3);

        minHeap.assignPriorityTest();

        assertEquals(2, minHeap.peek().getData());

        maxHeap.enqueue(new NodePQ<>(1, 20));
        maxHeap.enqueue(new NodePQ<>(2, 15));
        maxHeap.enqueue(new NodePQ<>(3, 10));

        maxHeap.assignPriorityTest();

        assertEquals(1, maxHeap.peek().getData());
    }
}
