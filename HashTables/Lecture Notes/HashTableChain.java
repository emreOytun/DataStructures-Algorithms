import java.util.Iterator;
import java.util.LinkedList;


public class HashTableChain<K, V> {

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

    private static final int DEFAULT_CAPACITY = 101;
    private static final double LOAD_THRESHOLD = 0.75;

    private LinkedList<Entry<K,V>>[] table = new LinkedList[DEFAULT_CAPACITY]; 
    private int size = 0;

    private int hash(Object key) {
        int index = key.hashCode() % table.length;
        if (index < 0) index += table.length;
        return index;
    }

    public V get(Object key) {
        if (key == null) return null; // !!!

        int index = hash(key);
        if (table[index] == null) return null; // If the bucket is null, then return null;
        for (Entry<K,V> item : table[index]) { // Otherwise, look for the given index inside the bucket.
            if (item.key.equals(key)) return item.value;
        }
        return null; // If key is not found, then return null.
    }

    public V put(K key, V value) {
        if (key == null) return null; // !!!

        int index = hash(key);
        if (table[index] == null) {
            table[index] = new LinkedList<>();
        }

        for (Entry<K,V> item : table[index]) {
            if (item.key.equals(key)) {
                V oldValue = item.value;
                item.value = value;
                return oldValue;
            }
        }

        // Yeni bir slot eklencekse load factor'u kontrol et.
        if (size >= LOAD_THRESHOLD * table.length) {
            rehash();
        }
        Entry<K,V> newItem = new Entry<K,V>(key, value);
        table[index].addFirst(newItem);
        ++size;
        return null;
    }

    public V remove(Object key) {
        if (key == null) return null;

        int index = hash(key);
        if (table[index] == null) return null;
        Iterator<Entry<K,V>> it = table[index].iterator();
        while (it.hasNext()) {
            Entry<K,V> entry = it.next();
            if (key.equals(entry.key)) {
                it.remove();
                if (table[index].size() == 0) table[index] = null;
                --size;
                return entry.value;
            }
        }
        return null;
    }

    private void rehash() {
        LinkedList<Entry<K,V>>[] oldTable = table;
        int newCapacity = oldTable.length * 2;
        table = new LinkedList[newCapacity];    

        for (LinkedList<Entry<K,V>> item : oldTable) {
            if (item != null && item.size() != 0) {
                K key = item.getFirst().key;
                int index = hash(key);
                table[index] = item;
            }
        }
    }

    public static void main(String[] args) {
        HashTableChain<String, Integer> table = new HashTableChain<>();
        table.put("emre", 1);
        System.out.println(table.get("emre"));    
    }

}
