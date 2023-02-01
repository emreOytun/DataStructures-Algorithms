import java.util.Stack;

public class QueueWithStack<T> {

    private Stack<T> enqueue = new Stack<>();
    private Stack<T> dequeue = new Stack<>();

    // O(1)
    public void push(T newData) {
        enqueue.push(newData);
    }

    // O(1) or O(N)
    public T pop() {
        if (dequeue.size() != 0) {
            return dequeue.pop();
        }

        while (!enqueue.isEmpty()) {
            dequeue.push(enqueue.pop());
        }
    
        if (dequeue.size() == 0) return null;
        return dequeue.pop();
    }

    // O(1) or O(N)
    public T peek() {
        if (dequeue.size() != 0) {
            return dequeue.peek();
        }

        while (!enqueue.isEmpty()) {
            dequeue.push(enqueue.pop());
        }
    
        if (dequeue.size() == 0) return null;
        return dequeue.pop();
    }
}