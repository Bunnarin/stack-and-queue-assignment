/**
 * Generic array-based Queue (FIFO) using a circular array.
 * Resizes automatically when full.
 */
public class Queue<T> {
    private T[] items;
    private int front; // index of the front element
    private int size;  // number of elements

    // Time complexity: O(n) for allocating the array
    @SuppressWarnings("unchecked")
    public Queue(int arraySize) {
        if (arraySize < 1) {
            arraySize = 1;
        }
        items = (T[]) new Object[arraySize];
        front = 0;
        size = 0;
    }

    // Time complexity: O(1) amortized (O(n) only when the array is resized)
    public void insert(T newItem) {
        if (size == items.length) {
            resize();
        }
        int rear = (front + size) % items.length;
        items[rear] = newItem;
        size++;
    }

    // Time complexity: O(1)
    public T remove() {
        if (size == 0) {
            return null;
        }
        T item = items[front];
        items[front] = null;
        front = (front + 1) % items.length;
        size--;
        return item;
    }

    // Time complexity: O(1)
    public T peekFront() {
        if (size == 0) {
            return null;
        }
        return items[front];
    }

    // Time complexity: O(1)
    public T peekRear() {
        if (size == 0) {
            return null;
        }
        return items[(front + size - 1) % items.length];
    }

    // Time complexity: O(n)
    // Format: [front, ..., rear]
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            sb.append(items[(front + i) % items.length]);
            if (i < size - 1) {
                sb.append(", ");
            }
        }
        return sb.append("]").toString();
    }

    // Time complexity: O(n)
    public void display() {
        System.out.println(toString());
    }

    // Time complexity: O(n) - doubles the capacity and unrolls the circular layout
    @SuppressWarnings("unchecked")
    private void resize() {
        T[] bigger = (T[]) new Object[items.length * 2];
        for (int i = 0; i < size; i++) {
            bigger[i] = items[(front + i) % items.length];
        }
        items = bigger;
        front = 0;
    }
}
