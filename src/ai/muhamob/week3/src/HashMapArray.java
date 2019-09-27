package ai.muhamob.week3.src;

public class HashMapArray<K, V> implements Map<K, V> {

    private int tableSize;
    private Node<K, V>[] table;

    public <T> HashMapArray(int tableSize) {
        this.tableSize = tableSize;
        table = (Node<K,V>[])new Node[this.tableSize];
    }

    private int getIndex(int hash) {
        return Math.abs(hash % tableSize);
    }

    @Override
    public void put(K key, V value) {
        int hash = key.hashCode();
        int index = getIndex(hash);
        System.out.println("index " + index);
        System.out.println("table size " + tableSize);

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

    private Node getLastNode(Node firstNode) {
        Node node;

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
    private void setAfterFirstNode(Node firstNode, Node newNode) {
        Node node;

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

    }

    @Override
    public boolean contains(K key) {
        return false;
    }

    @Override
    public int size() {
        return 0;
    }
}
