package ai.muhamob.week3.src;

public class HashMapArray<K, V> implements Map<K, V> {

    private final int tableSize;
    private LinkedNode<K, V>[] table;

    public HashMapArray(int tableSize) {
        this.tableSize = tableSize;
        table = (LinkedNode<K,V>[]) new LinkedNode[this.tableSize];
    }

    private int getIndex(int hash) {
        return Math.abs(hash % tableSize);
    }

    @Override
    public void put(K key, V value) {
        int hash = key.hashCode();
        int index = getIndex(hash);

        LinkedNode<K, V> linkedNode;
        LinkedNode<K, V> newLinkedNode = new LinkedNode<>(key, value, null);

        if ((linkedNode =table[index]) == null) {
            table[index] = newLinkedNode;
        } else {
            if (linkedNode.getNext() == null) {
                if (linkedNode.getKey().equals(key)) {
                    table[index] = newLinkedNode;
                } else {
                    linkedNode.setNext(newLinkedNode);
                }
            } else {
                LinkedNode<K,V> prevLinkedNode = linkedNode;
                while(linkedNode.getNext() != null && !linkedNode.getKey().equals(key)) {
                    prevLinkedNode = linkedNode;
                    linkedNode = linkedNode.getNext();
                }

                if (linkedNode.getNext() == null && !linkedNode.getKey().equals(key)) {
                    linkedNode.setNext(newLinkedNode);
                } else if (prevLinkedNode == linkedNode && linkedNode.getKey().equals(key)) {
                    // situation when node is first node in chain
                    newLinkedNode.setNext(linkedNode.getNext());
                    table[index] = newLinkedNode;
                } else if (linkedNode.getKey().equals(key)) {
                    newLinkedNode.setNext(linkedNode.getNext());
                    prevLinkedNode.setNext(newLinkedNode);
                }
            }
        }
    }

    @Override
    public V get(K key) {
        int hash = key.hashCode();
        int index = getIndex(hash);

        LinkedNode<K, V> linkedNode = table[index];

        if (linkedNode == null) {
            return null;
        } else {
            do {
                if (linkedNode.getKey().equals(key)) {
                    return linkedNode.getValue();
                }
            } while ((linkedNode = linkedNode.getNext()) != null);
        }

        return null;
    }

    @Override
    public void remove(K key) {
        int hash = key.hashCode();
        int index = getIndex(hash);

        LinkedNode<K, V> firstLinkedNode = table[index];
        if (firstLinkedNode != null) {
            if (firstLinkedNode.getNext() != null) {
                if (firstLinkedNode.getKey().equals(key)) {
                    table[index] = firstLinkedNode.getNext();
                } else {
                    LinkedNode<K, V> nextLinkedNode = firstLinkedNode.getNext();
                    LinkedNode<K, V> prevLinkedNode = firstLinkedNode;

                    // iterate until next Node is null or till nextNode key and key aren't the same
                    while (nextLinkedNode.getNext() != null && !nextLinkedNode.getKey().equals(key)) {
                        prevLinkedNode = nextLinkedNode;
                        nextLinkedNode = nextLinkedNode.getNext();
                    }

                    // If key is present in hash map, then delete it
                    if (nextLinkedNode.getKey().equals(key)) {
                        // Check if nextNode is the last Node
                        // link prevNode and node after nextNode if it isn't
                        // otherwise delete link from prevNode to nextNode
                        if (nextLinkedNode.getNext() != null) {
                            prevLinkedNode.setNext(nextLinkedNode.getNext());
                        } else {
                            prevLinkedNode.setNext(null);
                        }
                    }
                }
            } else {
                if (firstLinkedNode.getKey().equals(key)) {
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

// K type stands for keys type
// V type stands for value type
class LinkedNode<K, V> {
    private final K key;
    private final V value;
    private LinkedNode<K, V> next;

    LinkedNode(K key, V value, LinkedNode<K, V> next) {
        this.key = key;
        this.value = value;
        this.next = next;
    }

    void setNext(LinkedNode<K, V> next) {
        this.next = next;
    }

    K getKey() {
        return key;
    }

    V getValue() {
        return value;
    }

    LinkedNode<K, V> getNext() {
        return next;
    }
}
