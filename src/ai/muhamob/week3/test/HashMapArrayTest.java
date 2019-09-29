package ai.muhamob.week3.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import ai.muhamob.week3.src.HashMapArray;

class HashMapArrayTest {

    HashMapArray testHashMap(int tableSize) {
        HashMapArray<String, Integer> hashMapArray = new HashMapArray<>(tableSize);

        //
        hashMapArray.put("one", 1);
        hashMapArray.put("eno", 2);
        hashMapArray.put("neo", 3);

        hashMapArray.put("second", 22);

        return hashMapArray;
    }

    @Test
    void testHashes() {
        int hash1 = "one".hashCode();
        int hash2 = "noe".hashCode();
        int hash3 = "eon".hashCode();

        System.out.println(hash1);
        System.out.println(hash2);
        System.out.println(hash3);

        assertEquals(hash1, hash2);
        assertEquals(hash2, hash3);
    }

    @Test
    void testInit() {
        HashMapArray<String, Integer> hashMapArray = new HashMapArray<>(10);
    }

    @Test
    void testPutAndGet() {
        HashMapArray<String, Integer> hashMapArray = new HashMapArray<>(10);

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
    void testRemove() {
        HashMapArray<String, Integer> hashMapArray = new HashMapArray<>(10);
        hashMapArray.put("one", 1);
        hashMapArray.put("eno", 2);
        hashMapArray.put("neo", 3);

        hashMapArray.put("second", 22);

        hashMapArray.remove("neo");
        assertNull(hashMapArray.get("neo"));

        hashMapArray.remove("second");
        assertNull(hashMapArray.get("second"));

        hashMapArray.remove("one");
        assertNull(hashMapArray.get("one"));
        assertEquals(hashMapArray.get("eno"), 2);
    }

    @Test
    void testContains() {

    }
}