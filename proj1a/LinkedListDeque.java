
public class LinkedListDeque<T> {
    private Node sentinel;
    private int size;

    public class Node {
        public T item;
        public Node next;
        public Node prev;

        public Node() {
            item = null;
            prev = this;
            next = prev;
        }

        public Node(T val, Node pre, Node nex) {
            item = val;
            prev = pre;
            next = nex;
        }

    }

    public LinkedListDeque() {
        sentinel = new Node();
        size = 0;
    }

    /**
     * deep copy
     */
    public LinkedListDeque(LinkedListDeque<T> other) {
        sentinel = new Node();
        Node it = other.sentinel.next;
        while (it != other.sentinel) {
            addLast(it.item);
        }
    }

    public void addFirst(T item) {
        sentinel.next.prev = new Node(item, sentinel, sentinel.next);
        sentinel.next = sentinel.next.prev;
        size++;
    }

    public void addLast(T item) {
        Node last = sentinel.prev;
        last.next = new Node(item, last, sentinel);
        sentinel.prev = last.next;
        size++;
    }
    public boolean isEmpty() { return size == 0; }
    public int size() { return size; }
    public void removeFirst() {
        if (!isEmpty()) {
            Node next = sentinel.next.next;
            sentinel.next = next;
            next.prev = sentinel;
            size--;
        }
    }
    public void removeLast() {
        if (!isEmpty()) {
            Node prev = sentinel.prev.prev;
            sentinel.prev = prev;
            prev.next = sentinel;
            size--;
        }
    }
    public T get(int index) {
        Node it = sentinel;
        while ((index--) >= 0 && index < size) {
            it = it.next;
        }
        return it.item;
    }
    public T getRecursiveAt(Node cur, int index) {
        if (index == 0) return cur.item;
        else if (index < size) {
            return getRecursiveAt(cur.next, index-1);
        }
    }
    public T getRecursive(int index) {
        if (index < size) {
            return getRecursiveAt(sentinel, index);
        }
    }
}
