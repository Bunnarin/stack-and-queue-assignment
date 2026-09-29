/**
 * Generic array-based PriorityQueue. A smaller priorityValue means higher priority.
 * Items with equal priority are removed in insertion (FIFO) order.
 *
 * Internal layout: the array is kept sorted by priorityValue in DESCENDING order,
 * so the highest-priority item (smallest value) is always at the END of the array.
 * This makes remove() and peekFront() O(1); insert() pays the O(n) cost of shifting.
 */
public class PriorityQueue<T> {
    private T[] items;
    private int[] priorities;
    private int size;

    // Time complexity: O(n) for allocating the arrays
    @SuppressWarnings("unchecked")
    public PriorityQueue(int arraySize) {
        if (arraySize < 1) {
            arraySize = 1;
        }
        items = (T[]) new Object[arraySize];
        priorities = new int[arraySize];
        size = 0;
    }

    // Time complexity: O(n) - shifts elements to keep the array sorted
    public void insert(T newItem, int priorityValue) {
        if (size == items.length) {
            resize();
        }
        int i = size - 1;
        // Shift items with priority value <= new value to the right,
        // so the new item is placed before equal-priority items (FIFO among ties).
        while (i >= 0 && priorities[i] <= priorityValue) {
            items[i + 1] = items[i];
            priorities[i + 1] = priorities[i];
            i--;
        }
        items[i + 1] = newItem;
        priorities[i + 1] = priorityValue;
        size++;
    }

    // Time complexity: O(1)
    public T remove() {
        if (size == 0) {
            return null;
        }
        T item = items[--size];
        items[size] = null;
        return item;
    }

    // Time complexity: O(1) - highest priority (smallest value)
    public T peekFront() {
        if (size == 0) {
            return null;
        }
        return items[size - 1];
    }

    // Time complexity: O(1) - lowest priority (largest value)
    public T peekRear() {
        if (size == 0) {
            return null;
        }
        return items[0];
    }

    // Time complexity: O(n)
    // Format: [item(priority), ...] listed in removal order (front to rear)
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = size - 1; i >= 0; i--) {
            sb.append(items[i]).append("(").append(priorities[i]).append(")");
            if (i > 0) {
                sb.append(", ");
            }
        }
        return sb.append("]").toString();
    }

    // Time complexity: O(n)
    public void display() {
        System.out.println(toString());
    }

    // Time complexity: O(n) - doubles the capacity and copies elements
    @SuppressWarnings("unchecked")
    private void resize() {
        T[] biggerItems = (T[]) new Object[items.length * 2];
        int[] biggerPriorities = new int[priorities.length * 2];
        for (int i = 0; i < size; i++) {
            biggerItems[i] = items[i];
            biggerPriorities[i] = priorities[i];
        }
        items = biggerItems;
        priorities = biggerPriorities;
    }
}
