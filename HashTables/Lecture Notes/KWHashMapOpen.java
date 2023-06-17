import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Map;
import java.util.Set;
import java.util.Iterator;

/**
 * Entry -> Map.Entry interface'inden olmasi lazim. AbstractMap'in SimpleEntry class'i kullanilir.
 * 
 * AbstractMap, entrySet() methodunu kullanarak gerekli tum fonksiyonlari implement ediyor.
 * 
 * Implement edilmesi gerekenler:
 * V get(Object key);
 * V put(K key, V value);
 * V remove(Object key);
 * Set<Entry<K,V>> entrySet();
 */
public class KWHashMapOpen<K, V> extends AbstractMap<K, V> implements Map<K, V> {
    
    private static final int START_CAPACITY = 101;
    private static final double LOAD_THRESHOLD = 0.75;
    
    private Entry<K,V>[] table = new SimpleEntry[START_CAPACITY];
    private int numKeys = 0;
    private int numDeletes = 0;
    private final Entry<K,V> DELETED = new SimpleEntry(null, null);


    // Precondition: Table is not full.
    private int find(Object key) {
        int index = key.hashCode() % table.length; // Get the index using the hashCode
        if (index < 0) index += table.length; // IMPORTANT**: Check if the index is negative !!!!!

        while (table[index] != null && (!key.equals(table[index].getKey()))) {
            index = (index + 1) % table.length;
        }
        return index;
    }

    public V get(Object key) {
        if (key == null) return null; // IMPORTANT**: Check if the key is null or not !!!
        
        int index = find(key);
        if (table[index] == null) return null;
        return table[index].getValue();
    }

    public V put(K key, V value) {
        if (key == null) return null; // IMPORTANT**: Check if the key is null or not !!

        int index = find(key);
        if (table[index] == null) {
            table[index] = new SimpleEntry<K,V>(key, value);
            ++numKeys;
            
            double loadFactor = (numKeys + numDeletes) / table.length;
            if (loadFactor >= LOAD_THRESHOLD) {
                rehash();
            }
            return null;
        }
        V oldVal = table[index].getValue();
        table[index].setValue(value);
        return oldVal;
    }

    public V remove(Object key) {
        if (key == null) return null;

        int index = find(key);
        if (table[index] == null) return null;
        V oldValue = table[index].getValue();
        table[index] = DELETED;
        ++numDeletes;
        --numKeys;
        return oldValue;
    }

    private void rehash() {
        Entry<K,V>[] oldTable = table;
        Entry<K,V>[] table = new SimpleEntry[2 * oldTable.length + 1]; // Set this table as a new table.
        numKeys = 0; // Clear the numbers.
        numDeletes = 0;

        for (int i = 0; i < oldTable.length; ++i) {
            if (oldTable[i] != null && oldTable[i] != DELETED) {
                put(oldTable[i].getKey(), oldTable[i].getValue());
            }
        }
    }    

    @Override
    public Set<Entry<K, V>> entrySet() {
        return new EntrySet();
    }

    // The purpose of creating new class called EntrySet is to use the reference to the outer class KWHashMapOpen and 
    // we don't need to copy all of the entries to this set in that way.
    private class EntrySet extends AbstractSet<Entry<K,V>> {
        public int size() {
            return numKeys;
        }

        public Iterator<Entry<K,V>> iterator() {
            return new EntrySetIter();
        }

        private class EntrySetIter implements Iterator<Entry<K,V>> {
            private int nextIndex;
            private int lastIndex = -1;
            
            public EntrySetIter() {
                nextIndex = 0;
                if (table[0] == null) nextIndex = findNext(0);
            }

            public boolean hasNext() {
                return nextIndex < table.length;
            }

            public Entry<K,V> next() {
                if (!hasNext()) throw new RuntimeException();
                lastIndex = nextIndex;
                nextIndex = findNext(nextIndex);
                return table[lastIndex];
            }

            // IMPORTANT**: nextIndex aynen devam edecek cunku fiziksel bir silme olmadi. !!!!
            public void remove() {
                if (lastIndex == -1) throw new IllegalStateException();
                table[lastIndex] = DELETED;
                lastIndex = -1;
                --numKeys;
                ++numDeletes;
            }

            private int findNext(int index) {
                ++index;
                while (index < table.length && table[index] == null) {
                    ++index;
                }
                return index;
            }
        }
    }
    
}
