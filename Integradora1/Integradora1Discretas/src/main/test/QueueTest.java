import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import ownStructures.Queue;
public class QueueTest {
    private Queue<Integer> queue;

    @Before
    public void setUp() {
        queue = new Queue<>();
    }

    // Test for enqueue
    @Test
    public void testEnqueueElemento() {
        queue.enqueue(1);
        assertEquals("The front of the tail should be the first element added.", (Integer)1, queue.front());
    }

    @Test
    public void testEnqueueVariosElementos() {
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        assertEquals("The size of the queue should be 3 after adding three elements", 3, queue.size());
    }

    @Test
    public void testEnqueueNull() {
        queue.enqueue(null);
        assertNull("The front of the queue should be null after a null element is added.", queue.front());
    }

    // Pruebas para dequeue
    @Test
    public void testDequeueVacio() {
        assertNull("Dequeue should return null if the queue is empty.", queue.dequeue());
    }

    @Test
    public void testDequeueElemento() {
        queue.enqueue(1);
        assertEquals("Dequeue should return the first element if the queue has an element", (Integer)1, queue.dequeue());
    }

    @Test
    public void testDequeueDespuesDeVariosEnqueue() {
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.dequeue();
        assertEquals("New front should be 2 after dequeueing", (Integer)2, queue.front());
    }

    // Pruebas para front
    @Test
    public void testFrontVacio() {
        assertNull("Front should return null if the queue is empty.", queue.front());
    }

    @Test
    public void testFrontElemento() {
        queue.enqueue(1);
        assertEquals("Front should return the first element if the queue has one element", (Integer)1, queue.front());
    }

    @Test
    public void testFrontNoModificaTamaño() {
        queue.enqueue(1);
        queue.front();
        assertEquals("Front should not modify the size of the queue", 1, queue.size());
    }

    // Test for isEmpty
    @Test
    public void testIsEmptyVerdadero() {
        assertTrue("isEmpty should return true if the queue is empty", queue.isEmpty());
    }

    @Test
    public void testIsEmptyFalso() {
        queue.enqueue(1);
        assertFalse("isEmpty should return false if the queue has elements", queue.isEmpty());
    }

    @Test
    public void testIsEmptyDespuesDeDequeue() {
        queue.enqueue(1);
        queue.dequeue();
        assertTrue("isEmpty should return true if the queue was emptied after dequeueing", queue.isEmpty());
    }

    // Test for  size
    @Test
    public void testSizeVacio() {
        assertEquals("Size should return 0 if the queue is empty", 0, queue.size());
    }

    @Test
    public void testSizeUnElemento() {
        queue.enqueue(1);
        assertEquals("Size should return 1 if the queue has one element", 1, queue.size());
    }

    @Test
    public void testSizeDespuesDeDequeue() {
        queue.enqueue(1);
        queue.enqueue(2);
        queue.dequeue();
        assertEquals("Size should return 1 after dequeuing to a queue with two items", 1, queue.size());
    }
}

