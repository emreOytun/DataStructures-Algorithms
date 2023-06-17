@SuppressWarnings("unchecked")
public class HashTableOpen<K, V> {
    
    private static class Entry<K, V> {
        private K key;
        private V value;
    
        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    
        public K getKey() { return key; }
        public V getValue() { return value; }
        public V setValue(V val) {
            V temp = value;
            value = val; 
            return temp;
        }
    }
    
    // START_CAPACITY should better be a prime number.
    private static final int START_CAPACITY = 101;
    private static final double LOAD_THRESHOLD = 0.75;
    
    private Entry<K,V>[] table = new Entry[START_CAPACITY];
    private int numKeys = 0;
    private int numDeletes = 0;
    private final Entry<K,V> DELETED = new Entry<>(null, null); // Silinecek olan elemanlari DELETED referansiyla isaretliyoruz. Rehash'lerken silinmis oluyor.


    // Precondition: Table is not full. This conditions holds because we use load factor, table is always empty.
    private int find(Object key) {
        int index = key.hashCode() % table.length; // Get the index using the hashCode
        if (index < 0) index += table.length; // IMPORTANT**: Check if the index is negative !!!!!

        while (table[index] != null && (!key.equals(table[index].key))) {
            index = (index + 1) % table.length;
        }
        return index;
    }

    public V get(Object key) {
        if (key == null) return null; // IMPORTANT**: Check if the key is null or not !!!
        
        int index = find(key);
        if (table[index] == null) return null;
        return table[index].value;
    }

    public V put(K key, V value) {
        if (key == null) return null; // IMPORTANT**: Check if the key is null or not !!

        int index = find(key);
        if (table[index] == null) {
            table[index] = new Entry<K,V>(key, value);
            ++numKeys;
            
            double loadFactor = (numKeys + numDeletes) / table.length;
            if (loadFactor >= LOAD_THRESHOLD) {
                rehash();
            }
            return null;
        }
        V oldVal = table[index].value;
        table[index].value = value;
        return oldVal;
    }

    public V remove(Object key) {
        if (key == null) return null;

        int index = find(key);
        if (table[index] == null) return null;
        V oldValue = table[index].value;
        table[index] = DELETED;
        ++numDeletes;
        --numKeys;
        return oldValue;
    }

    // Rehash'lemek icin once oldTable'i tut, daha sonra bu class icin hash table'i ve counter'lari sifirla
    // cunku herbir eleman yeni capacity icin bastan rehash edilecek.
    private void rehash() {
        Entry<K,V>[] oldTable = table;
        Entry<K,V>[] table = new Entry[2 * oldTable.length + 1];
        numKeys = 0;
        numDeletes = 0;

        for (int i = 0; i < oldTable.length; ++i) {
            if (oldTable[i] != null && oldTable[i] != DELETED) {
                put(oldTable[i].key, oldTable[i].value);
            }
        }
    }    
}
