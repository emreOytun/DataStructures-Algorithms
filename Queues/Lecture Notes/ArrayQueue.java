import java.util.Iterator;
import java.util.NoSuchElementException;

@SuppressWarnings("unchecked")
public class ArrayQueue<E> implements IQueue<E> {
    
    private static final int DEFAULT_CAPACITY = 10;

    private E[] data;
    private int size;
    private int capacity;
    private int front;
    private int back;


    public ArrayQueue(int initialCapacity) {
        data = (E[]) new Object[initialCapacity];
        capacity = initialCapacity;
        size = 0;
        front = 0;
        back = -1;
    }

    public ArrayQueue() {
        this(DEFAULT_CAPACITY);
    }

    // Stack'te push, Queue'da offer
    public boolean offer(E newItem) {
        if (size == capacity) reallocate(capacity * 2);
        back = (back + 1) % capacity;
        data[back] = newItem;
        ++size;
        return true;
    }

    // Stack'te pop, Queue'da poll
    // Stack'te EmptyStackException, Queue'da null doner.
    public E poll() {
        if (size == 0) {
            return null;
        }
        E result = data[front];
        data[front] = null; // ONEMLI**: Release etmemiz lazim...
        front = (front + 1) % capacity;
        --size;
        return result;
    }

    
    // Stack'te pop, Queue'da poll
    // Stack'te EmptyStackException, Queue'da null doner.
    public E peek() {
        if (size == 0) {
            return null;
        }
        return data[front];
    }

    // remove ve element fonksiyonlari NoSuchElementException firlat
    public E remove() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        return poll();
    }

    public E element() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        return data[front];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void reallocate(int newCapacity) {
        E[] newData = (E[]) new Object[newCapacity];
        for (int i = 0; i < size; ++i) {
            newData[i] = data[(front+i) % capacity];
        }
        data = null;
        data = newData;
        capacity = newCapacity;
    }

    private class QueueIter implements Iterator<E> {
        private int curIndex;
        private int count; // Counts how many elements iterator passed.

        public QueueIter() {
            curIndex = front;
            count = 0;
        }

        public boolean hasNext() {
            return count < size;
        }

        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            E returnValue = data[curIndex];
            curIndex = (curIndex + 1) % capacity;
            ++count;
            return returnValue;
        }
    }

}
