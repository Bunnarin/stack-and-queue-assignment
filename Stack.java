public class Stack<T> {
    private T[] items;
    private int top; // number of elements; next free index

    // Time complexity: O(n)
    @SuppressWarnings("unchecked")
    public Stack(int arraySize) {
        if (arraySize < 1) {
            arraySize = 1;
        }
        items = (T[]) new Object[arraySize];
        top = 0;
    }

    // Time complexity: O(1) amortized (O(n) only when the array is resized)
    public void push(T newItem) {
        if (top == items.length) {
            resize();
        }
        items[top++] = newItem;
    }

    // Time complexity: O(1)
    public T pop() {
        if (top == 0) {
            return null;
        }
        T item = items[--top];
        items[top] = null; // avoid holding stale reference
        return item;
    }

    // Time complexity: O(1)
    public T peek() {
        if (top == 0) {
            return null;
        }
        return items[top - 1];
    }

    // Time complexity: O(n)
    // Format: [bottom, ..., top]
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < top; i++) {
            sb.append(items[i]);
            if (i < top - 1) {
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
        T[] bigger = (T[]) new Object[items.length * 2];
        for (int i = 0; i < top; i++) {
            bigger[i] = items[i];
        }
        items = bigger;
    }
}
