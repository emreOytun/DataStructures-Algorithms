package List;

public class KWArrayList<E> {

    private static final int INITIAL_CAPACITY = 10;

    private E[] data = null;
    private int size = 0;
    private int capacity = 0;

    public KWArrayList() {
        this(INITIAL_CAPACITY);
    }

    // Initializes the array list with the given capacity.
    public KWArrayList(int capacity) {
        this.capacity = capacity;
        data = (E[]) new Object[capacity];
    }

    // Always return true.
    // O(1) 
    // Bottleneck: O(N)
    public boolean add(E element) {
        if (size == capacity) {
            setCapacity(capacity * 2);
        }

        data[size] = element;
        ++size;
        return true;
    }

    // O(N)
    public void add(int index, E element) {
        // Index-check
        // NOTE**: An element can be added at the end of the array, therefore index == size is possible.
        if (index < 0 || index > size) {
            throw new ArrayIndexOutOfBoundsException(index);
        } 

        if (size == capacity) {
            setCapacity(capacity * 2);
        }        

        for (int i = size-1; i > index; --i) {
            data[i+1] = data[i];
        }        

        data[index] = element;
        ++size;
    }

    // O(1)
    public E get(int index) {
        // Index-check
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException(index);
        }

        return data[index];
    }

    // O(1)
    // Set method is used to update an element in the list.
    // Returns the old value.
    public E set(int index, E newValue) {
        // Index-check
        // NOTE**: Index == size is not possible, since there is no elements there.
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException(index);
        }

        E oldValue = data[index];
        data[index] = newValue;
        return oldValue;
    }

    // O(N)
    public E remove(int index) {
        // Index-check
        // NOTE**: Index == size is not possible, since there is no elements there.
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException(index);
        }

        E removedValue = data[index];
        for (int i = index+1; i < size; ++i) {
            data[i-1] = data[i];
        }
        --size;
        return removedValue;
    
    }

    // O(N)
    public boolean remove(E element) {
        boolean isFound = false;
        int idx = -1;
        for (int i = 0; i < size && !isFound; ++i) {
            if (data[i].equals(element)) {
                isFound = true;
                idx = i;
            }
        }

        // Shift elements toward the found idx.
        for (int i = idx+1; i < size; ++i) {
            data[i] = data[i+1];
        }
        --size;
        return isFound;
    }

    // O(N)
    public int indexOf(E element) {
        int idx = -1;
        boolean isFound = false;
        for (int i = 0; i < size && !isFound; ++i) {
            if (data[i].equals(element)) {
                isFound = true;
                idx = i;
            }
        } 

        return idx;
    }

    private void setCapacity(int newCapacity) {
        E[] newData = (E[]) new Object[newCapacity];
        for (int i = 0; i < size; ++i) {
            newData[i] = data[i];
        }
        data = null;
        data = newData;
        capacity = newCapacity;
    }

    private class ArrayListIter<E> extends ListIterator<E> {
        private int nextIndex;
        private int lastIndexReturned;
    }

    public static void main(String[] args) {
        KWArrayList<Integer> list = new KWArrayList<>();

        list.add(1);
        list.add(2);
        list.add(0, -1);

        System.out.println(list.get(0));
    }

}