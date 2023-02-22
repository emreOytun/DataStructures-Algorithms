package QueueWithArray;

@SuppressWarnings("unchecked") // Add it to suppress the warning of weak type while creating generic array T[]
public class QueueWithArray<E> {
    
    private static int DEFAULT_CAPACITY = 10;

    private E[] arr; // Array that keeps data
    private int front; // Front index to be removed next
    private int back; // Back index to be added next
    private int len; // Length of the queue
    private int capacity;

    public QueueWithArray() {
        arr = (E[]) new Object[DEFAULT_CAPACITY];
        front = 0;
        back = -1;
        len = 0;
        capacity = DEFAULT_CAPACITY;
    }

    public void push(E data) {
        if (len == capacity) resize(capacity * 2);

        back = (back + 1) % capacity;
        arr[back] = data;
        ++len;
    }

    public E pop() {
        if (len == 0) return null;
        
        E element = arr[front];
        front = (front + 1) % capacity;
        --len;
        return element;
    }

    public boolean isEmpty() {
        return len == 0;
    }

    private void resize(int newCapacity) {
        E[] newArr = (E[]) new Object[newCapacity];
        for (int i = 0; i < len; ++i) {
            newArr[i] = arr[front];
            front = (front + 1) % capacity;
        }

        arr = newArr;
        capacity = newCapacity;
        front = 0;
        back = len - 1;
    }

}