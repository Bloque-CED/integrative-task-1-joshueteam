import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import ownStructures.Stack;

public class StackTest {
    private Stack<Integer> stack;

    @Before
    public void setUp() {
        stack = new Stack<>();
    }

    // Pruebas para push
    @Test
    public void testPushElemento() {
        stack.push(1);
        assertEquals("El elemento en la cima debería ser el último añadido", (Integer)1, stack.peek());
    }

    @Test
    public void testPushVariosElementos() {
        stack.push(1);
        stack.push(2);
        stack.push(3);
        assertEquals("El tamaño de la pila debería ser 3 después de añadir tres elementos", 3, stack.size());
    }

    @Test
    public void testPushNull() {
        stack.push(null);
        assertNull("La cima de la pila debería ser null después de añadir un elemento null", stack.peek());
    }

    // Pruebas para pop
    @Test
    public void testPopVacio() {
        assertNull("Pop debería retornar null si la pila está vacía", stack.pop());
    }

    @Test
    public void testPopElemento() {
        stack.push(1);
        assertEquals("Pop debería retornar el elemento si la pila tiene un elemento", (Integer)1, stack.pop());
    }

    @Test
    public void testPopDespuesDeVariosPush() {
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.pop();
        assertEquals("El nuevo elemento en la cima debería ser 2 después de hacer pop", (Integer)2, stack.peek());
    }

    // Pruebas para peek
    @Test
    public void testPeekVacio() {
        assertNull("Peek debería retornar null si la pila está vacía", stack.peek());
    }

    @Test
    public void testPeekElemento() {
        stack.push(1);
        assertEquals("Peek debería retornar el elemento si la pila tiene un elemento", (Integer)1, stack.peek());
    }

    @Test
    public void testPeekNoModificaTamaño() {
        stack.push(1);
        stack.peek();
        assertEquals("Peek no debería modificar el tamaño de la pila", 1, stack.size());
    }

    // Pruebas para isEmpty
    @Test
    public void testIsEmptyVerdadero() {
        assertTrue("isEmpty debería retornar true si la pila está vacía", stack.isEmpty());
    }

    @Test
    public void testIsEmptyFalso() {
        stack.push(1);
        assertFalse("isEmpty debería retornar false si la pila tiene elementos", stack.isEmpty());
    }

    @Test
    public void testIsEmptyDespuesDePop() {
        stack.push(1);
        stack.pop();
        assertTrue("isEmpty debería retornar true si la pila se vació después de pop", stack.isEmpty());
    }

    // Pruebas para size
    @Test
    public void testSizeVacio() {
        assertEquals("Size debería retornar 0 si la pila está vacía", 0, stack.size());
    }

    @Test
    public void testSizeUnElemento() {
        stack.push(1);
        assertEquals("Size debería retornar 1 si la pila tiene un elemento", 1, stack.size());
    }

    @Test
    public void testSizeDespuesDePop() {
        stack.push(1);
        stack.push(2);
        stack.pop();
        assertEquals("Size debería retornar 1 después de hacer pop a una pila con dos elementos", 1, stack.size());
    }
}

