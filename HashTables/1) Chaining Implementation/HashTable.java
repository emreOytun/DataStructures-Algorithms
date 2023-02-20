// Chaining Solution to Collision Problem: It İS NOT guaranteed for operations to work in O(1) because all the items could be inside
//                                          the same bucket.
public class HashTable {
    
    private static final int TABLE_SIZE = 10;
    private HashItem[] hashTable;

    public HashTable() {
        this.hashTable = new HashItem[TABLE_SIZE];
    }

    // Worst-case: O(N) ----- Best-case(No collision): O(1)
    // Get the index from hash function, and search the key inside the linked list. 
    public int get(int key) throws RuntimeException {
        // Get the index of the key.
        int idx = hash(key);

        // Check if this bucket is null or not. If bucket is null throw exception.
        if (hashTable[idx] == null) throw new RuntimeException("There is no such key");
       
        // Try to find the key inside the bucket. 
        HashItem curNode = hashTable[idx];
        while (curNode != null && curNode.getKey() != key) {
            curNode = curNode.getNext();
        }
    
        // If bucket is not found throw exception, otherwise throw the value.
        if (curNode == null) throw new RuntimeException("There is no such key");
        return curNode.getValue();
    }

    // Worst-case: O(N)
    // Get the index from the hash function, iterate over the linked listed inside the bucket ...
    // if the key is found then put the value there, or add it to the last.
    public void put(int key, int value) {
        // Hash the key and get the index.
        int idx = hash(key);

        // Create the new item that will be added inside the bucket.
        HashItem newItem = new HashItem(key, value);

        // Check if the bucket is null.
        // Change the root if it is null; put it to the first otherwise. 
        if (hashTable[idx] == null) hashTable[idx] = newItem;
        else {
            HashItem curItem = hashTable[idx];
            boolean isPlaced = false;
            while (!isPlaced) {
                if (curItem.getKey() == key) {
                    curItem.setValue(value);
                    isPlaced = true;
                }
                else if (curItem.getNext() == null) {
                    curItem.setNext(newItem);
                    isPlaced = true;
                }
            }
        }
    }

    // it transforms the key into an index of array
    private int hash(int key) {
        return key % TABLE_SIZE;
    }
}
