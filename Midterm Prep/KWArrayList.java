import java.util.List;
import java.util.AbstractList;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class KWArrayList<E> extends AbstractList<E> implements List<E> {

	private final static int DEFAULT_CAPACITY = 10;
	private E[] data;
	private int size = 0;
	private int capacity;
	
	private class KWArrayListIter implements ListIterator<E> {
		private int nextIndex;
		private int lastIndexReturned = -1;
		
		public KWArrayListIter(int index) {
			if (index < 0 || index > size) {
                throw new IndexOutOfBoundsException();
            }
            nextIndex = index;
		}
		
		@Override
		public boolean hasNext() {
			return nextIndex < size;
		}
		
		@Override
		public E next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			lastIndexReturned = nextIndex;
            ++nextIndex;
			return data[lastIndexReturned];
		}
		
		@Override
		public boolean hasPrevious() {
			return (nextIndex - 1) >= 0;
		}
		
		@Override
		public E previous() {
			if (!hasPrevious()) {
				throw new NoSuchElementException();
			}
			--nextIndex;
			lastIndexReturned = nextIndex;
			return data[lastIndexReturned];
		}
		
		@Override
		public int nextIndex() {
			return nextIndex;
		}
		
		@Override
		public int previousIndex() {
			return nextIndex - 1;
		}
		
		@Override
		public void add(E item) {
			if (size == capacity) reallocate(capacity * 2);
			for (int i = size; i >= nextIndex; --i) {
				data[i] = data[i-1];
			}
			data[nextIndex] = item;
            ++size;
            ++nextIndex;
            lastIndexReturned = -1;
		}

        @Override
        public void remove() {
            if (lastIndexReturned == -1) {
                throw new IllegalStateException();
            }
            for (int i = lastIndexReturned; i < size-1; ++i) {
                data[i] = data[i+1];
            }
            --size;
            if (nextIndex == lastIndexReturned) {
                ++nextIndex;
            }
            lastIndexReturned = -1;
        }

        @Override
        public void set(E newItem) {
            if (lastIndexReturned == -1) {
                throw new IllegalStateException();
            }
            data[lastIndexReturned]  = newItem;
        }
	}
	
    private void reallocate(int newCapacity) {
        E[] newData = (E[]) new Object[newCapacity];
        for (int i = 0; i < size; ++i) {
            newData[i] = data[i];
        }
        data = newData;
        capacity = newCapacity;
    }

	public KWArrayList(int capacity) {
		data = (E[]) new Object[capacity];
		this.capacity = capacity;
	} 
	
	public KWArrayList() {
		this(DEFAULT_CAPACITY);
	}
	
    @Override
    public boolean add(E item) {
        if (size == capacity) reallocate(capacity*2);
        data[size-1] = item;
        ++size;
        return true;
    }

    @Override
    public void add(int index, E item) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        if (size == capacity) reallocate(capacity * 2);
        for (int i = size; i > index; --i) {
            data[i] = data[i-1];
        }
        data[index] = item;
        ++size;
    }

    @Override
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        E item = data[index];
        for (int i = index; i < size-1; ++i) {
            data[i] = data[i+1];
        }
        --size;
        return item;
    }
    
    @Override
    public boolean remove(Object obj) {
        ListIterator<E> iterator = new KWArrayListIter(0);
        boolean isFound = false;
        while (iterator.hasNext() && !isFound) {
            if (obj.equals(iterator.next())) {
                iterator.remove();
                isFound = true;
            }
        }
        return isFound;
    }

    @Override
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        return data[index];
    }

    @Override
    public int size() {
        return size;
    }
}