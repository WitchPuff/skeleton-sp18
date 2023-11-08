package src;

public class LinkedListDeque<T> {
    private Node sentinel;
    private int size;

    private class Node {
        private T item;
        private Node next;
        private Node prev;

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
    // public LinkedListDeque(LinkedListDeque<T> other) {
    //     sentinel = new Node();
    //     Node it = other.sentinel.next;
    //     while (it != other.sentinel) {
    //         addLast(it.item);
    //     }
    // }

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
    public boolean isEmpty() { 
        return size == 0; 
    }
    public int size() { 
        return size; 
    }

    public T removeFirst() {
        if (!isEmpty()) {
            Node first = sentinel.next;
            sentinel.next = first.next;
            first.next.prev = sentinel;
            size--;
            return first.item;
        } else {
            return null;
        }
    }

    public T removeLast() {
        if (!isEmpty()) {
            Node last = sentinel.prev;
            sentinel.prev = last.prev;
            last.prev.next = sentinel;
            size--;
            return last.item;
        } else {
            return null;
        }
    }
    public T get(int index) {
        Node it = sentinel;
        while (index >= 0 && index < size) {
            it = it.next;
            index--;
        }
        return it.item;
    }
    private T getRecursiveAt(Node cur, int index) {
        if (index == 0) {
            return cur.item;
        } else if (index < size) {
            return getRecursiveAt(cur.next, index - 1);
        } else { 
            return null;
        }
    }
    public T getRecursive(int index) {
        if (index < size) {
            return getRecursiveAt(sentinel.next, index);
        } else {
            return null;
        }
    }
    public void printDeque() {
        Node it = sentinel.next;
        while (it != sentinel) {
            System.out.println(it.item + " ");
            it = it.next;
        }
    }
}
