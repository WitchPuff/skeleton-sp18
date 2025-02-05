package src;

public class ArrayDeque<T> {
    private T[] items;
    private int size;
    private int rear;
    private int head;
    private static final int DEFAULT_CAPACITY = 16;


    /**Creates an empty list. */
    public ArrayDeque() {
        items = (T[]) new Object[DEFAULT_CAPACITY];
        size = 0;
        head = DEFAULT_CAPACITY / 2;
        rear = DEFAULT_CAPACITY / 2;
    }

    /** Deep copy. */
    // public ArrayDeque(ArrayDeque<T> other) {
    //     System.arraycopy(other.items, 0, items, 0, size);
    // }
    
    /** Resizes the underlying array to the target capacity. */
    private void resize(int capacity) {
        T[] a = (T[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            a[i] = get(i);
        }
        items = a;
        head = 0;
        rear = prev(size);
    }
    /** Check the size and resize it to an appropriate size. */
    private void checkSize() {
        if (size == items.length) {
            resize(size * 2);
        } else if ((double) size / items.length < 0.25) {
            resize(1 + items.length / 2);
        }
    }
    /**
     * Gets index + 1 within the array capacity limit
     * @param index
     * @return
     */
    private int next(int index) {
        return (index + 1) % items.length;
    }
    /**
     * Gets index - 1 within th array capacity limit
     * @param index
     * @return
     */
    private int prev(int index) {
        return index - 1 >= 0 ? index - 1 : items.length - 1;
    }
    /**
     * Inserts val into items[size++].
     * @param val 
     */
    public void addLast(T val) {
        checkSize();
        size++;
        rear = next(rear);
        items[rear] = val;
    }
    /**
     * Inserts val into items[0]
     * @param val
     */
    public void addFirst(T val) {
        checkSize();
        size++;
        head = prev(head);
        items[head] = val;
    }
    /**
     * Removes the last item from items[--size]
     * @return
     */
    public T removeLast() {
        if (!isEmpty()) {    
            T last = items[rear];
            rear = prev(rear);
            size--;
            checkSize();
            return last;
        } else {
            return null;
        }
    }
    /**
     * Removes the first item from items[0]
     * @return
     */
    public T removeFirst() {
        if (!isEmpty()) {
            T front = items[head];
            head = next(head);
            size--;
            checkSize();
            return front;
        } else {
            return null;
        }
    }
    /**
     * Gets the item from items[index]
     * @param index
     * @return
     */
    public T get(int index) {
        if (index < size) {
            return items[(head + index) % items.length];
        } else {
            return null;
        }
    }
    /** Gets the size of the array. */
    public int size() {
        return size;
    }
    /** Returns if the array is empty. */
    public boolean isEmpty() {
        return size == 0;
    }

    /** Prints the deque. */
    public void printDeque() {
        for (int i = 0; i < size; i++) {
            System.out.println(get(i) + " ");
        }
        System.out.println();
    }

}
