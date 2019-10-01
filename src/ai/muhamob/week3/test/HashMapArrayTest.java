package ai.muhamob.week3.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import ai.muhamob.week3.src.HashMapArray;

class HashMapArrayTest {

    /*
    Constructs hash map of given structure
    0:
    1:
    2: -> one, 1 -> eno, 2 -> neo, 3
    3:
    4:
    5: -> sec, 2 -> ces, 5
    6:
    7:
    8:
    9: -> third, 3
    l*/
    HashMapArray<String, Integer> makeHashMap() {
        HashMapArray<String, Integer> hashMapArray = new HashMapArray<>(10);

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
        /*
        Constructs hash map of given structure
        0:
        1:
        2: -> one, 1 -> eno, 2 -> neo, 3
        3:
        4:
        5: -> sec, 2 -> ces, 5
        6:
        7:
        8:
        9: -> third, 3

        assert that hashMap is not null
         */
        HashMapArray<String, Integer> hashMapArray = new HashMapArray<>(10);

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

    /*
    Constructs hashMap with given structure
    0:
    1:
    2: -> one, 1 -> eno, 2 -> neo, 3
    3:
    4:
    5:
    6:
    7:
    8:
    9:

    and then put same key but different value to the middle of the chain
    put("eno", 4)

    assume HashMapArray.get(key) works

     */
    @Test
    void testPutRepeatedKeysPutInMiddle() {
        HashMapArray<String, Integer> hashMapArray = new HashMapArray<>(10);

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

    /*
    Constructs hashMap with given structure
    0:
    1:
    2: -> one, 1 -> eno, 2 -> neo, 3
    3:
    4:
    5:
    6:
    7:
    8:
    9:

    and then put same key but different value to the start of the chain
    put("one", 4)

    assume HashMapArray.get(key) works

     */
    @Test
    void testPutRepeatedKeysPutAtTheBeggining() {
        HashMapArray<String, Integer> hashMapArray = new HashMapArray<>(10);

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

    /*
    Constructs hashMap with given structure
    0:
    1:
    2: -> one, 1 -> eno, 2 -> neo, 3
    3:
    4:
    5:
    6:
    7:
    8:
    9:

    and then put same key but different value to the end of the chain
    put("eno", 4)

    assume HashMapArray.get(key) works

     */
    @Test
    void testPutRepeatedKeysPutToTheEnd() {
        HashMapArray<String, Integer> hashMapArray = new HashMapArray<>(10);

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

    @Test
    void testPutAndGet() {
        HashMapArray<String, Integer> hashMapArray = makeHashMap();

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
        HashMapArray<String, Integer> hashMapArray = makeHashMap();

        hashMapArray.remove("third");
        assertNull(hashMapArray.get("third"));
    }

    @Test
    void testRemoveFromTheBegginingOfChain() {
        HashMapArray<String, Integer> hashMapArray = makeHashMap();

        hashMapArray.remove("one");
        assertNull(hashMapArray.get("one"));

        // assert that remainder of chain not deleted
        assertNotNull(hashMapArray.get("eno"));
        assertNotNull(hashMapArray.get("neo"));
    }

    @Test
    void testRemoveFromTheMiddleOfChain() {
        HashMapArray<String, Integer> hashMapArray = makeHashMap();

        hashMapArray.remove("eno");
        assertNull(hashMapArray.get("eno"));

        // assert that remainder of chain not deleted
        assertNotNull(hashMapArray.get("one"));
        assertNotNull(hashMapArray.get("neo"));
    }

    @Test
    void testRemoveFromTheEndOfChain() {
        HashMapArray<String, Integer> hashMapArray = makeHashMap();

        hashMapArray.remove("neo");
        assertNull(hashMapArray.get("neo"));

        // assert that remainder of chain not deleted
        assertNotNull(hashMapArray.get("one"));
        assertNotNull(hashMapArray.get("eno"));
    }

    @Test
    void testContains() {
        
    }
}