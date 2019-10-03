package ai.muhamob.week3.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import ai.muhamob.week3.src.HashMapArrayOpenAddressing;

public class HashMapArrayOpenAddressingTest {
    HashMapArrayOpenAddressing<String, Integer> makeHashMap() {
        HashMapArrayOpenAddressing<String, Integer> hashMapArray = new HashMapArrayOpenAddressing<>(10);

        hashMapArray.put("one", 1);
        hashMapArray.put("eno", 2);
        hashMapArray.put("neo", 3);

        hashMapArray.put("sec", 2);
        hashMapArray.put("ces", 5);

        hashMapArray.put("third", 3);

        return hashMapArray;
    }

    @Test
    void testPutStandart() {
        HashMapArrayOpenAddressing<String, Integer> hashMapArray = new HashMapArrayOpenAddressing<>(10);

        // one and eno and neo has same index
        // hashes are 110182, 100582, 108952
        hashMapArray.put("one", 1);
        hashMapArray.put("eno", 2);
        hashMapArray.put("neo", 3);

        // sec and ces hashes are 113745 and 98385
        hashMapArray.put("sec", 2);
        hashMapArray.put("ces", 5);

        // hash is 110331239
        hashMapArray.put("third", 3);

        assertNotNull(hashMapArray);
    }

    @Test
    void testPutRepeatedKeysPutInMiddle() {
        HashMapArrayOpenAddressing<String, Integer> hashMapArray = new HashMapArrayOpenAddressing<>(10);

        hashMapArray.put("one", 1);
        hashMapArray.put("eno", 2);
        hashMapArray.put("neo", 3);

        assertEquals(hashMapArray.get("one"), 1);
        assertEquals(hashMapArray.get("eno"), 2);
        assertEquals(hashMapArray.get("neo"), 3);

        // put in the middle
        hashMapArray.put("eno", 4);
        assertEquals(hashMapArray.get("one"), 1);
        assertEquals(hashMapArray.get("eno"), 4);
        assertEquals(hashMapArray.get("neo"), 3);
    }

    @Test
    void testPutRepeatedKeysPutAtTheBeggining() {
        HashMapArrayOpenAddressing<String, Integer> hashMapArray = new HashMapArrayOpenAddressing<>(10);

        hashMapArray.put("one", 1);
        hashMapArray.put("eno", 2);
        hashMapArray.put("neo", 3);

        assertEquals(hashMapArray.get("one"), 1);
        assertEquals(hashMapArray.get("eno"), 2);
        assertEquals(hashMapArray.get("neo"), 3);

        // put in the middle
        hashMapArray.put("one", 4);
        assertEquals(hashMapArray.get("one"), 4);
        assertEquals(hashMapArray.get("eno"), 2);
        assertEquals(hashMapArray.get("neo"), 3);
    }

    @Test
    void testPutRepeatedKeysPutToTheEnd() {
        HashMapArrayOpenAddressing<String, Integer> hashMapArray = new HashMapArrayOpenAddressing<>(10);

        hashMapArray.put("one", 1);
        hashMapArray.put("eno", 2);
        hashMapArray.put("neo", 3);

        assertEquals(hashMapArray.get("one"), 1);
        assertEquals(hashMapArray.get("eno"), 2);
        assertEquals(hashMapArray.get("neo"), 3);

        // put in the middle
        hashMapArray.put("neo", 4);
        assertEquals(hashMapArray.get("one"), 1);
        assertEquals(hashMapArray.get("eno"), 2);
        assertEquals(hashMapArray.get("neo"), 4);
    }

    void testManyPuts() {
        HashMapArrayOpenAddressing<String, Integer> hashMapArray = new HashMapArrayOpenAddressing<>(10);

        hashMapArray.put("first", 1);
        hashMapArray.put("second", 2);
        hashMapArray.put("third", 3);
        hashMapArray.put("fourth", 4);
        hashMapArray.put("fives", 5);

        assertEquals(hashMapArray.get("first"), 1);
        assertEquals(hashMapArray.get("first"), 1);
        assertEquals(hashMapArray.get("second"), 2);
        assertEquals(hashMapArray.get("third"), 3);
        assertEquals(hashMapArray.get("fourth"), 4);
        assertEquals(hashMapArray.get("fives"), 5);
    }

    /*
    indices are
    2
    0
    9
    2
    1
     */
    @Test
    void testPutAndGet() {
        HashMapArrayOpenAddressing<String, Integer> hashMapArray = new HashMapArrayOpenAddressing<>(10);

        System.out.println("first".hashCode() % 10);
        System.out.println("second".hashCode() % 10);
        System.out.println("third".hashCode() % 10);
        System.out.println("fourth".hashCode() % 10);
        System.out.println("fives".hashCode() % 10);
        hashMapArray.put("first", 1);
        hashMapArray.put("second", 2);
        hashMapArray.put("third", 3);
        hashMapArray.put("fourth", 4);
        hashMapArray.put("fives", 5);

        assertEquals(hashMapArray.get("first"), 1);
        assertEquals(hashMapArray.get("first"), 1);
        assertEquals(hashMapArray.get("second"), 2);
        assertEquals(hashMapArray.get("third"), 3);
        assertEquals(hashMapArray.get("fourth"), 4);
        assertEquals(hashMapArray.get("fives"), 5);
    }

    @Test
    void testRemoveFromChainWithOneElement() {
        HashMapArrayOpenAddressing<String, Integer> hashMapArray = makeHashMap();

        hashMapArray.remove("third");
        assertNull(hashMapArray.get("third"));
    }

    @Test
    void testRemoveFromTheBegginingOfChain() {
        HashMapArrayOpenAddressing<String, Integer> hashMapArray = makeHashMap();

        hashMapArray.remove("one");
        assertNull(hashMapArray.get("one"));

        // assert that remainder of chain not deleted
        assertNotNull(hashMapArray.get("eno"));
        assertNotNull(hashMapArray.get("neo"));
    }

    @Test
    void testRemoveFromTheMiddleOfChain() {
        HashMapArrayOpenAddressing<String, Integer> hashMapArray = makeHashMap();

        hashMapArray.remove("eno");
        assertNull(hashMapArray.get("eno"));

        // assert that remainder of chain not deleted
        assertNotNull(hashMapArray.get("one"));
        assertNotNull(hashMapArray.get("neo"));
    }

    @Test
    void testRemoveFromTheEndOfChain() {
        HashMapArrayOpenAddressing<String, Integer> hashMapArray = makeHashMap();

        hashMapArray.remove("neo");
        assertNull(hashMapArray.get("neo"));

        // assert that remainder of chain not deleted
        assertNotNull(hashMapArray.get("one"));
        assertNotNull(hashMapArray.get("eno"));
    }

    @Test
    void testContains() {
        HashMapArrayOpenAddressing<String, Integer> hashMapArray = makeHashMap();

        assertTrue(hashMapArray.contains("one"));
        assertTrue(hashMapArray.contains("neo"));
        assertTrue(hashMapArray.contains("eno"));

        assertTrue(hashMapArray.contains("sec"));
        assertTrue(hashMapArray.contains("ces"));

        assertTrue(hashMapArray.contains("third"));

        assertFalse(hashMapArray.contains("fives"));
    }
}
