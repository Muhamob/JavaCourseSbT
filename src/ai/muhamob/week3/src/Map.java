package ai.muhamob.week3.src;

public interface Map<K, V> {
    public void put(K key, V value);
    public Object get(K key);
    public void remove(K key);
    public boolean contains(K key);
    public int size();
}
