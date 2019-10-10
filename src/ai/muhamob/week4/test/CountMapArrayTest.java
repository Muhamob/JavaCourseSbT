package tests;

import ai.muhamob.CountMapArray;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;


class CountMapArrayTest {

    CountMapArray<String> createCountMap() {
        CountMapArray<String> counter = new CountMapArray<>();

        counter.add("a");
        counter.add("a");
        counter.add("a");

        counter.add("b");
        counter.add("b");

        counter.add("c");

        return counter;
    }

    @Test
    void testAdd() {
        CountMapArray<String> counter = new CountMapArray<>();

        counter.add("a");
        counter.add("a");
        counter.add("a");
    }

    @Test
    void testGetCount() {
        CountMapArray<String> counter = createCountMap();

        assertEquals(counter.getCount("a"), 3);
        assertEquals(counter.getCount("b"), 2);
        assertEquals(counter.getCount("c"), 1);
    }

    @Test
    void testRemove() {
        CountMapArray<String> counter = createCountMap();

        int count = counter.remove("a");

        assertEquals(count, 3);
        assertEquals(counter.getCount("a"), 0);
    }

    @Test
    void testSize() {
        CountMapArray<String> counter = createCountMap();

        assertEquals(counter.size(), 3);
    }

    @Test
    void testAddAll() {
        CountMapArray<String> counter = createCountMap();
        CountMapArray<String> counter2 = createCountMap();

        counter2.add("d");
        counter2.add("d");
        counter2.add("d");
        counter2.add("d");

        counter.addAll(counter2);

        assertEquals(counter.getCount("a"), 6);
        assertEquals(counter.getCount("b"), 4);
        assertEquals(counter.getCount("c"), 2);
        assertEquals(counter.getCount("d"), 4);
    }

    @Test
    void testToMap() {
        CountMapArray<String> counter = createCountMap();

        Map<String, Integer> map_ = counter.toMap();
    }

    @Test
    void testVoidToMap() {
        CountMapArray<String> counter = createCountMap();
        Map<String, Integer> map1 = new HashMap<>();
        Map<String, Integer> map2 = counter.toMap();

        counter.toMap(map1);

        assertEquals(map2, map1);

    }
}