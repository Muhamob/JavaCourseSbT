package ai.muhamob.week3.src;

// K type stands for keys type
// V type stands for value type
class Node<K, V> {
    private final K key;
    private final V value;
    private Node<K, V> next;

    Node(K key, V value, Node<K, V> next) {
        this.key = key;
        this.value = value;
        this.next = next;
    }

    void setNext(Node<K, V> next) {
        this.next = next;
    }

    K getKey() {
        return key;
    }

    V getValue() {
        return value;
    }

    Node<K, V> getNext() {
        return next;
    }
}
