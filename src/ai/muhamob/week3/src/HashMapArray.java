package ai.muhamob.week3.src;

public class HashMapArray<K, V> implements Map<K, V> {

    private int tableSize;
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

        if ((node=getFirstNode(index)) == null) {
            table[index] = newNode;
        } else {
            setAfterFirstNode(node, newNode);
        }
    }

    private Node<K, V> getFirstNode(int index) {
        return table[index];
    }

    private Node<K, V> getLastNode(Node<K, V> firstNode) {
        Node<K, V> node;

        if (firstNode == null || firstNode.getNext() == null) {
            return firstNode;
        }

        node = firstNode;
        do {
            node = node.getNext();
        } while (node.getNext() != null);

        return node;
    }

    // first node has to be not null
    private void setAfterFirstNode(Node<K, V> firstNode, Node<K, V> newNode) {
        Node<K, V> node;

        if (firstNode.getNext() == null) {
            firstNode.setNext(newNode);
        } else {
            node = getLastNode(firstNode);
            node.setNext(newNode);
        }
    }

    @Override
    public Object get(K key) {
        int hash = key.hashCode();
        int index = getIndex(hash);

        Node firstNode = getFirstNode(index);
        Node node;

        if (firstNode == null) {
            return null;
        } else {
            node = firstNode;
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

        Node<K, V> firstNode = getFirstNode(index);
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
        int hash = key.hashCode();
        int index = getIndex(hash);

        Node<K, V> firstNode = getFirstNode(index);
        if (firstNode == null) {
            return false;
        } else {
            Node<K, V> node = firstNode;
            while (node != null && !node.getKey().equals(key)) {
                node = node.getNext();
            }

            return node != null;
        }
    }

    @Override
    public int size() {
        return 0;
    }
}
