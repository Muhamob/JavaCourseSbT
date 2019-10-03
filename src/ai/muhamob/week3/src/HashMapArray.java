package ai.muhamob.week3.src;

public class HashMapArray<K, V> implements Map<K, V> {

    private final int tableSize;
    private Node<K, V>[] table;

    public HashMapArray(int tableSize) {
        this.tableSize = tableSize;
        table = (Node<K,V>[]) new Node[this.tableSize];
    }

    private int getIndex(int hash) {
        return Math.abs(hash % tableSize);
    }

    @Override
    public void put(K key, V value) {
        int hash = key.hashCode();
        int index = getIndex(hash);

        Node<K, V> node;
        Node<K, V> newNode = new Node<>(key, value, null);

        if ((node=table[index]) == null) {
            table[index] = newNode;
        } else {
            if (node.getNext() == null) {
                if (node.getKey().equals(key)) {
                    table[index] = newNode;
                } else {
                    node.setNext(newNode);
                }
            } else {
                Node<K,V> prevNode = node;
                while(node.getNext() != null && !node.getKey().equals(key)) {
                    prevNode = node;
                    node = node.getNext();
                }

                if (node.getNext() == null && !node.getKey().equals(key)) {
                    node.setNext(newNode);
                } else if (prevNode == node && node.getKey().equals(key)) {
                    // situation when node is first node in chain
                    newNode.setNext(node.getNext());
                    table[index] = newNode;
                } else if (node.getKey().equals(key)) {
                    newNode.setNext(node.getNext());
                    prevNode.setNext(newNode);
                }
            }
        }
    }

    @Override
    public V get(K key) {
        int hash = key.hashCode();
        int index = getIndex(hash);

        Node<K, V> node = table[index];

        if (node == null) {
            return null;
        } else {
            do {
                if (node.getKey().equals(key)) {
                    return node.getValue();
                }
            } while ((node=node.getNext()) != null);
        }

        return null;
    }

    @Override
    public void remove(K key) {
        int hash = key.hashCode();
        int index = getIndex(hash);

        Node<K, V> firstNode = table[index];
        if (firstNode != null) {
            if (firstNode.getNext() != null) {
                if (firstNode.getKey().equals(key)) {
                    table[index] = firstNode.getNext();
                } else {
                    Node<K, V> nextNode = firstNode.getNext();
                    Node<K, V> prevNode = firstNode;

                    // iterate until next Node is null or till nextNode key and key aren't the same
                    while (nextNode.getNext() != null && !nextNode.getKey().equals(key)) {
                        prevNode = nextNode;
                        nextNode = nextNode.getNext();
                    }

                    // If key is present in hash map, then delete it
                    if (nextNode.getKey().equals(key)) {
                        // Check if nextNode is the last Node
                        // link prevNode and node after nextNode if it isn't
                        // otherwise delete link from prevNode to nextNode
                        if (nextNode.getNext() != null) {
                            prevNode.setNext(nextNode.getNext());
                        } else {
                            prevNode.setNext(null);
                        }
                    }
                }
            } else {
                if (firstNode.getKey().equals(key)) {
                    table[index] = null;
                }
            }

        }
    }

    @Override
    public boolean contains(K key) {
        return get(key) != null;
    }

    @Override
    public int size() {
        return 0;
    }
}
