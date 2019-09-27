package ai.muhamob.week3.src;

// K type stands for keys type
// V type stands for value type
class Node<K, V> {
    private final K key;
    private final V value;
    private Node next;

    Node(K key, V value, Node next) {
        this.key = key;
        this.value = value;
        this.next = next;
    }

    void setNext(Node next) {
        this.next = next;
    }

    Object getKey() {
        return key;
    }

    Object getValue() {
        return value;
    }

    Node getNext() {
        return next;
    }
}
