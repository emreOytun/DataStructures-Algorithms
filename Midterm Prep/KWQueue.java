import java.util.AbstractQueue;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Queue;

/* 
 * offer    add
 * poll     remove -> NoSuchElementException
 * peek     element -> NoSuchElementException
 * 
 * AbstractQueue abstract class'i:
 * add, remove, element fonksiyonlarini digerlerini kullanarak implement ediyor.
 * 
 * AbstractQueue extend edildiginde asagidakiler minimum implement edilmeli:
 * int size
 * boolean offer
 * E poll
 * E peek
 * Iterator<E> iterator -> Unsupported denilebilir.
 * 
*/
public class KWQueue<E> extends AbstractQueue<E> implements Queue<E> {
    private static final int DEFAULT_CAPACITY = 10;
    private E[] data;
    private int size = 0;
    private int capacity;
    private int head = 0;
    private int tail = -1;

    public KWQueue(int capacity) {
        data = (E[]) new Object[capacity];
        this.capacity = capacity;
    }
    
    public KWQueue() {
        this(DEFAULT_CAPACITY);
    }

    public int size() {
        return size;
    }

    private void reallocate(int newCapacity) {
        E[] newData = (E[]) new Object[newCapacity];
        for (int i = 0; i < size; ++i) {
            newData[i] = data[head];
            head = (head + 1) % capacity;
        }
        tail = size - 1;
        head = 0;
        data = newData;
        capacity = newCapacity;
    }

    public boolean offer(E item) {
        if (size == capacity) reallocate(capacity * 2);
        tail = (tail + 1) % capacity;
        data[tail] = item;
        ++size;
        return true;
    }

    public E peek() {
        if (size == 0) return null;
        E result = data[head];
        head = (head + 1) % capacity;
        --size;
        return result;
    }

    public E poll() {
        if (size == 0) return null;
        return data[head];
    }

    // Asagidaki fonksiyonlar, yukaridakiler kullanilarak AbstractQueue<E> abstract classinda implement edilmis halde.
    // boolean add
    // E remove
    // E element
    public boolean add(E item) {
        return offer(item);
    }

    public E remove() {
        E result = peek();
        if (result == null) {
            throw new NoSuchElementException();
        }
        return result;
    }

    public Iterator iterator() {
        throw new UnsupportedOperationException();
    }

}
