package ai.muhamob;

import java.util.*;

public class CountMapArray<K> implements CountMap<K> {

    private HashMap<K, Integer> map = new HashMap<>();

    @Override
    public void add(K key) {
        if (map.containsKey(key)) {
            Integer count = map.get(key);
            map.put(key, count + 1);
        } else {
            map.put(key, 1);
        }
    }

    @Override
    public int getCount(K key) {
        Integer count = map.get(key);
        return Objects.requireNonNullElse(count, 0);
    }

    @Override
    public int remove(K key) {
        int count = getCount(key);
        map.remove(key);

        return count;
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public void addAll(CountMap<K> source) {
        for (K key : source.toMap().keySet()) {
            Integer sourceCount = source.getCount(key);

            if (this.map.containsKey(key)) {
                this.map.put(key, getCount(key) + sourceCount);
            } else {
                this.map.put(key, sourceCount);
            }
        }
    }

    @Override
    public Map<K, Integer> toMap() {
        return map;
    }

    @Override
    public void toMap(Map<K, Integer> destination) {
        for (K key : map.keySet()) {
            destination.put(key, map.get(key));
        }
    }
}
