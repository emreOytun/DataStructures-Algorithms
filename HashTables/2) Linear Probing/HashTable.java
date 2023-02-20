// Chaining Solution to Collision Problem: It İS NOT guaranteed for operations to work in O(1) because all the items could be inside
//                                          the same bucket.
public class HashTable {
    
    private static final int TABLE_SIZE = 10;
    private HashItem[] hashTable;
    private int len;

    public HashTable() {
        this.hashTable = new HashItem[TABLE_SIZE];
        this.len = 0;
    }

    public int get(int key) {
        int index = hash(key);

        boolean isFound = false;
        boolean isDone = false;
        int startIndex = index;
        while (!isDone) {
            if (hashTable[index] != null && hashTable[index].getKey() == key) {
                isFound = true;
                isDone = true;
            }
            else {
                ++index;
                if (index == startIndex) isDone = true;
            }
        }

        if (!isFound) throw new RuntimeException("There is no such key");
        return hashTable[index].getValue();
    }

    public void put(int key, int value) {
        // We do not implement load factor and resizing into this hashtable, therefore we should check if the table is full.
        if (len == TABLE_SIZE) return;

        int index = hash(key);
        while (hashTable[index] != null) ++index;
        hashTable[index] = new HashItem(key, value);
    }

    // it transforms the key into an index of array
    private int hash(int key) {
        return key % TABLE_SIZE;
    }
}
