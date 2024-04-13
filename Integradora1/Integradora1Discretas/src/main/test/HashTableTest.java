import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import ownStructures.HashTable;
public class HashTableTest {
    private HashTable<String, Integer> hashTable;

    @Before
    public void setUp() {
        hashTable = new HashTable<>();
    }

    // Test for put
    @Test
    public void testPutNuevoElemento() {
        hashTable.put("clave1", 1);
        assertEquals("Size should be 1 after adding an element", 1, hashTable.size());
    }

    @Test
    public void testPutSobrescribirElemento() {
        hashTable.put("clave1", 1);
        hashTable.put("clave1", 2);
        assertEquals("The value associated with 'key1' should be 2 after overwriting", (Integer)2, hashTable.get("clave1"));
    }

    @Test
    public void testPutElementosColision() {
        hashTable.put("clave1", 1);
        hashTable.put("clave2", 2);
        assertEquals("Size should be 2 even if there is collision", 2, hashTable.size());
    }

    // Test for get
    @Test
    public void testGetExistente() {
        hashTable.put("clave1", 1);
        assertEquals("Get should return the value for an existing key", (Integer)1, hashTable.get("clave1"));
    }

    @Test
    public void testGetNoExistente() {
        assertNull("Get should return null for a key that does not exist.", hashTable.get("clave1"));
    }

    @Test
    public void testGetDespuesDeColision() {
        hashTable.put("clave1", 1);
        hashTable.put("clave2", 2);
        assertEquals("Get should return the correct value even after a collision.", (Integer)2, hashTable.get("clave2"));
    }

    // Test for remove
    @Test
    public void testRemoveExistente() {
        hashTable.put("clave1", 1);
        hashTable.remove("clave1");
        assertNull("Remove should remove the element and get should return null", hashTable.get("clave1"));
    }

    @Test
    public void testRemoveNoExistente() {
        hashTable.remove("clave1");
        assertEquals("The size should still be 0 after attempting to delete a non-existing key", 0, hashTable.size());
    }

    @Test
    public void testRemoveEnColision() {
        hashTable.put("clave1", 1);
        hashTable.put("clave2", 2); // Asumiendo que 'clave1' y 'clave2' colisionan en el mismo índice
        hashTable.remove("clave1");
        assertNotNull("Remove should remove 'key1' but 'key2' should still exist", hashTable.get("clave2"));
    }

    // Test for size
    @Test
    public void testSizeVacio() {
        assertEquals("Size should be 0 for an empty hash table", 0, hashTable.size());
    }

    @Test
    public void testSizeConElementos() {
        hashTable.put("clave1", 1);
        hashTable.put("clave2", 2);
        assertEquals("Size should be 2 after adding two elements", 2, hashTable.size());
    }

    @Test
    public void testSizeDespuesDeRemove() {
        hashTable.put("clave1", 1);
        hashTable.put("clave2", 2);
        hashTable.remove("clave1");
        assertEquals("Size should be 1 after deleting an element", 1, hashTable.size());
    }
}

