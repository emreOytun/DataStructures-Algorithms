// Since Arrays are static data structures, sometimes we need to resize it and this is the Bottleneck of this application.
// Without resizing it works in O(1) complexity, but when resizing it takes O(N) complexity.
// In this manner, linked list implementation seems to be better than array implementation of stack.
public class StackArray<T> {
    private T[] arr;
    private int len;

    public StackArray() {
        arr = (T[]) new Object[1]; // IMPORTANT**: An array with generic type<T> cannot be directly created; therefore first create it as Object[] then cast it.
    }

    // O(1)
    public void push(T data) {
        
        // Bottleneck: O(N)
        if (len == arr.length) {
            resize(2*len);
        }

        arr[len] = data;
        ++len;
    }

    // O(1)
    public T pop() {
        // If there is no element, return null.
        if (len == 0) return null; // IMPORTANT**: Don't forget to check if the array is empty or not.

        T item = arr[len-1];
        arr[len-1] = null; // IMPORTANT**: Obsolete references problem -> We have a reference that points to an object, but we don't use it and also we don't set it to null.
                           //When we don't use a reference anymore, then we should set it to null to avoid memory leaks.
        --len;

        // O(N)
        if (len > 0 && len == arr.length / 4) { // IMPORTANT**: len>0 must be checked. 
            resize(len/2);
        }

        return item;
    }

    // O(1)
    public T peek() {
        if (len == 0) return null; // IMPORTANT**: Don't forget to check if the array is empty or not.
        return arr[len-1];
    }

    // O(1)
    public int size() {
        return len;
    }

    // O(1)
    public boolean isEmpty() {
        return len == 0;
    }

    // O(N) - Bottleneck of the application
    private void resize(int newCapacity) {
        T[] newArr = (T[]) new Object[newCapacity];
        
        for (int i = 0; i < arr.length; ++i) {
            newArr[i] = arr[i];
        }

        arr = newArr;
    }

}
