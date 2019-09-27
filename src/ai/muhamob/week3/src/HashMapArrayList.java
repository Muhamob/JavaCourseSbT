package ai.muhamob.week3.src;

import java.util.ArrayList;

public class HashMapArrayList implements Map {
    private static final int TABLE_INITIAL_CAPACITY = 10;
    private static final float loadFactor = .75f;
    private int numKeys;

    private ArrayList<Node> table;
    private int tableSize;

    public HashMapArrayList() {
        tableSize = TABLE_INITIAL_CAPACITY;
        table = new ArrayList<>(tableSize);
        numKeys = 0;
    }

    @Override
    public void put(Object key, Object value) {
        // get index where to put
        int hash = key.hashCode();
        int index = hash % tableSize;

        // get node from table by index
        Node node = table.get(index);

        // create new node
        Node newNode = new Node(key, value, null);

        // Whether table[index] is present in table put node or iterate until last node to put new node
        if (node == null) {
            table.add(index, newNode);
        } else {
            while(node.getNext() != null) {
                node = node.getNext();
            }
            node.setNext(newNode);
        }

        // check if table changed size
        // if true, then rebuild table
        if (table.size() != tableSize) {
            refactorTable();
        }
    }

    // If table has grown, algorithm has to rebuild all the hashes
    private void refactorTable() {
        ArrayList<Node> oldTable = table;

        for (int i=0; i<tableSize; i++) {
            Node node = oldTable.get(i);
            if (node != null) {
                put(node.getKey(), node.getValue());
                while ((node=node.getNext()) != null) {
                    put(node.getKey(), node.getValue());
                }
            }
        }

    }

    @Override
    public Object get(Object key) {
        int hash = key.hashCode();
        Node firstNode = getFirstNode(hash);

        Node node = findNodeByKey(firstNode, key);
        if (node != null) {
            return node.getValue();
        } else {
            return null;
        }
    }

    private Node getFirstNode(int hash) {
        int index = hash % tableSize;

        return table.get(index);
    }

    // iteratively finds node by key
    private Node findNodeByKey(Node firstNode, Object key) {
        Node nextNode = firstNode.getNext();

        if (firstNode.getKey().equals(key)) {
            return firstNode;
        } else if (nextNode != null) {
            while(!nextNode.getKey().equals(key)) {
                nextNode = nextNode.getNext();
                if (nextNode.getKey().equals(key)) {
                    return nextNode;
                }
                if (nextNode == null) {
                    return null;
                }
            }
        }

        return null;
    }

    @Override
    public void remove(Object key) {

    }

    @Override
    public boolean contains(Object key) {
        return false;
    }

    @Override
    public int size() {
        return 0;
    }
}