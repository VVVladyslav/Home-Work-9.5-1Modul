import javax.xml.soap.Node;
import java.util.Objects;

public class MyHashMap {

    private static final int DEFAULT_CAPACITY = 16;
    private Node[] table;
    private int size;

    public MyHashMap() {
        table = new Node[DEFAULT_CAPACITY];
        size = 0;
    }

    private static class Node {
        Object key;
        Object value;
        Node next;

        Node(Object key, Object value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }
    }

    public void put(Object key, Object value) {
        int hashCode = Objects.hashCode(key);
        int index = hashCode % table.length;

        Node newNode = new Node(key, value);
        if (table[index] == null) {
            table[index] = newNode;
        } else {
            Node current = table[index];
            Node prev = null;

            while (current != null) {
                if (current.key.equals(key)) {
                    current.value = value;
                    return;
                }
                prev = current;
                current = current.next;
            }

            prev.next = newNode;
        }
        size++;
    }
    public void remove(Object key) {
        int hashCode = Objects.hashCode(key);
        int index = hashCode % table.length;

        Node current = table[index];
        Node prev = null;

        while (current != null) {
            if (current.key.equals(key)) {
                if (prev == null) {
                    table[index] = current.next;
                } else {
                    prev.next = current.next;
                }
                size--;
                return;
            }
            prev = current;
            current = current.next;
        }
    }

    public void clear() {
        table = new Node[DEFAULT_CAPACITY];
        size = 0;
    }

    public int size() {
        return size;
    }

    public Object get(Object key) {
        int hashCode = Objects.hashCode(key);
        int index = hashCode % table.length;

        Node current = table[index];

        while (current != null) {
            if (current.key.equals(key)) {
                return current.value;
            }
            current = current.next;
        }

        return null;
    }

}