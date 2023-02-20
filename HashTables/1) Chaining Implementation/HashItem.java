// key-value pair we want to store in the HashTable
public class HashItem {

    private int key;
    private int value;

    // in case of a collision, we use a LinkedList inside the same bucket (Chaining)
    private HashItem next;

    public HashItem(int key, int value) {
        this.key = key;
        this.value = value;
        this.next = null;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public HashItem getNext() {
        return next;
    }

    public void setNext(HashItem next) {
        this.next = next;
    }

    
}