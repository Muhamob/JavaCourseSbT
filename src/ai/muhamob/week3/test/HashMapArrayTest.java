package ai.muhamob.week3.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import ai.muhamob.week3.src.HashMapArray;

public class HashMapArrayTest {

    HashMapArray<String, Integer> hashMapArray;

    @Test
    public void testInit() {
        hashMapArray = new HashMapArray<>(10);
    }

    @Test
    public void testPut() {
        hashMapArray.put("first", 1);
        hashMapArray.put("second", 2);
        hashMapArray.put("third", 3);
        hashMapArray.put("fourth", 4);
        hashMapArray.put("fives", 5);
    }

    @Test
    public void testGet() {
        assertEquals(hashMapArray.get("first"), 1);
        assertEquals(hashMapArray.get("second"), 2);
        assertEquals(hashMapArray.get("third"), 3);
        assertEquals(hashMapArray.get("fourth"), 4);
        assertEquals(hashMapArray.get("fives"), 6);
    }
}