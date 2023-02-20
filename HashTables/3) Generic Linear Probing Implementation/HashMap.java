@SuppressWarnings("unchecked")
public class HashMap<Key, Value>  {

    private Key[] keys;
    private Value[] values;
    private int len;
    private int capacity;

    public HashMap() {
        this.keys = (Key[]) new Object[Constants.TABLE_SIZE];
        this.values = (Value[]) new Object[Constants.TABLE_SIZE];
        this.capacity = Constants.TABLE_SIZE;
        this.len = 0;        
    }

    public HashMap(int capacity) {
        this.keys = (Key[]) new Object[capacity];
        this.values = (Value[]) new Object[capacity];
        this.capacity = capacity;
        this.len = 0;
    }

    public int size() {
        return len;
    }

    public boolean isEmpty() {
        return len == 0;
    }

    public Value get(Key key) {
        // Error-checking
        if (key == null) return null;

        // O(1)
        int index = hash(key);

        // Search for the cluster until encountering an empty bucket.
        while (keys[index] != null) {
            if (keys[index].equals(key)) {
                return values[index];
            }
            index = (index+1) % capacity; // IMPORTANT**: It is important to use mod operator here to avoid out of boundary exception.
        }
        return null;
    }

    public void put(Key key, Value value) {
        // Error-check
        if (key == null || value == null) return;

        // Check the load factor of the Hash Table.
        // If length is bigger than 75 percent of capacity, resize.
        // Why? 
        // When it is nearly empty ---> we waste a lot of memory for no reason
        // When it is nearly full ---> There is a big chance of collision
        if (len >= capacity * 0.75) {
            resize(2 * capacity);
        }

        int index = hash(key);
        while (keys[index] != null) {
            // Update if key is registered before
            if (keys[index].equals(key)) {
                values[index] = value;
                return;
            }
            index = (index + 1) % capacity;
        }

        keys[index] = key;
        values[index] = value;
        ++len;
    }

    public void remove(Key key) {
        // Error-check
        if (key == null) return;

        // Search for the given key
        int index = hash(key);
        boolean isFound = false;
        while (keys[index] != null && !isFound) {
            if (keys[index].equals(key)) {
                isFound = true;
            }
            else {
                index = (index + 1) % capacity;
            }
        }

        // If the given key is not found, return.
        if (!isFound) return;
    
        // We delete the item from keys and values tables.
        // IMPORTANT**: Setting pointers to null is important for GC.
        keys[index] = null;
        values[index] = null;

        // There is a hole since we delete an item. We have to reconstruct the table, otherwise the get method and 
        // other implementations based on this will not work.
        index = (index + 1) % capacity;
        while (keys[index] != null) {
            Key tempKey = keys[index];
            Value tempValue = values[index];
        
            // IMPORANT**: Setting pointers to null is important for GC and for the implementation logic since we will call put method.
            keys[index] =  null;
            values[index] = null;
            
            --len;
            put(tempKey, tempValue);

            index = (index + 1) % capacity;
        }
        
        --len;
        if (len <= capacity * 0.33) {
            resize(capacity / 2);
        }
    }

    // Sometimes hashCode method return -1, therefore it's better to use Math.abs method.
    // IMPORTANT**: By using the mod operator, it is guaranteed that the resulting index is valid.
    private int hash(Key key) {
        return Math.abs(key.hashCode()) % capacity; 
    }

    private void resize(int newCapacity) {
        HashMap<Key, Value> newMap = new HashMap<>(newCapacity);

        for (int i = 0; i < len; ++i) {
            newMap.put(keys[i], values[i]);
        }

        keys = newMap.keys;
        values = newMap.values;
        capacity = newCapacity;
    }
}
