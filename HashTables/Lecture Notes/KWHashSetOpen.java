import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Set;

/**
 * AbstractSet'ten cikinca implement edilmesi gerekenler:
 * int size()
 * boolean add()
 * Iterator<E> iterator() -> remove'u implement etmis olacak.
 */
public class KWHashSetOpen<E> extends AbstractSet<E> implements Set<E> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double LOAD_THRESHOLD = 0.75;
    
    private E[] table = (E[]) new Object[DEFAULT_CAPACITY];
    private int numKeys = 0;

    public int size() {
        return numKeys;
    }

    private int hash(E key) {
        int index = key.hashCode() % table.length;
        if (index < 0) index += table.length;
        return index;
    }

    private int findBucket(E key) {
        int index = hash(key);
        while (table[index] != null && !key.equals(table[index])) { 
            index = (index+1) % table.length;
        }
        return index;
    }

    public boolean add(E key) {
        int index = findBucket(key);
        if (table[index] != null) return false;
        table[index] = key;
        ++numKeys;
        return true;
    }

    public Iterator<E> iterator() {
        return new SetIterator();
    }

    private class SetIterator implements Iterator<E> {
        private int nextIndex;
        private int lastIndex = -1;

        public SetIterator() {
            nextIndex = 0;
            if (table[0] == null) nextIndex = findNext(nextIndex);
        }

        public boolean hasNext() {
            return nextIndex < table.length;
        }

        public E next() {
            if (!hasNext()) {
                throw new RuntimeException();
            }
            lastIndex = nextIndex;
            nextIndex = findNext(nextIndex);
            return table[lastIndex];
        }

        public void remove() {
            if (lastIndex == -1) {
                throw new IllegalStateException();
            }
            table[lastIndex] = null;
            lastIndex = -1;

            // rehash until encountering a null slot/bucket.
            int curIndex = (lastIndex + 1) % table.length;
            while (table[curIndex] != null) {
                E key = table[curIndex];
                table[curIndex] = null;
                --numKeys;
                add(key);
            }
        }

        private int findNext(int index) {
            do {
                ++index;
            } while (index < table.length && table[index] == null);
            return index;
        }
    }
}
