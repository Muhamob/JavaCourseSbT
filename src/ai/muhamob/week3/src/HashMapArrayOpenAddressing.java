package ai.muhamob.week3.src;

/*
Implementation of hash map with fixed size table, open addressing and linear probing
 */
public class HashMapArrayOpenAddressing<K, V> implements Map<K, V> {

    private int tableSize;
    private int size = 0;
    private Node<K, V>[] table;

    public HashMapArrayOpenAddressing(int tableSize) {
        this.tableSize = tableSize;
        table = (Node<K, V>[]) new Node[this.tableSize];
    }

    private int getIndex(int hash) {
        return Math.abs(hash % tableSize);
    }

    @Override
    public void put(K key, V value) {
        int hash = key.hashCode();
        int firstIndex = getIndex(hash);
        int index;

        Node<K, V> node;
        Node<K, V> newNode = new Node<>(key, value);

        for (int i = 0; i < tableSize; i++) {
            index = (firstIndex + i) % tableSize;
            node = table[index];

            // put new node if this node is null or deleted
            if (node == null || node.isDeleted()) {
                table[index] = newNode;
                size += 1;
                break;
            }

            if (node.getKey().equals(key)) {
                table[index] = newNode;
            }

            // figure out what to do if all cells are filled
        }
    }

    @Override
    public V get(K key) {
        int hash = key.hashCode();
        int firstIndex = getIndex(hash);
        int index;

        Node<K, V> node = table[firstIndex];

        if (node == null) {
            return null;
        }

        for (int i = 0; i < tableSize; i++) {
            index = (firstIndex + i) % tableSize;
            node = table[index];

            if (node == null) {
                return null;
            }

            if (node.getKey().equals(key) && !node.isDeleted()) {
                return node.getValue();
            }
        }

        // in case if after loop there was no matches
        return null;
    }

    @Override
    public void remove(K key) {
        int hash = key.hashCode();
        int firstIndex = getIndex(hash);
        int index;

        Node<K, V> node;

        for (int i = 0; i < tableSize; i++) {
            index = (firstIndex + i) % tableSize;
            node = table[index];

            if (node == null) {
                break;
            } else if (node.getKey().equals(key)) {
                node.deleteNode();
                size -= 1;
            }
        }
    }

    @Override
    public boolean contains(K key) {
        return get(key) != null;
    }

    @Override
    public int size() {
        return size;
    }
}

// K type stands for keys type
// V type stands for value type
class Node<K, V> {
    private final K key;
    private final V value;
    private boolean isDeleted;

    Node(K key, V value) {
        this.key = key;
        this.value = value;
        isDeleted = false;
    }

    void deleteNode() {
        isDeleted = true;
    }

    boolean isDeleted() {
        return isDeleted;
    }

    K getKey() {
        return key;
    }

    V getValue() {
        return value;
    }
}
