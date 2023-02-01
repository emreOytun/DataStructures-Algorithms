import javax.swing.text.TabExpander;

public class Queue<T> {
    
    private Node<T> firstNode;
    private Node<T> lastNode;
    private int count;

    public boolean isEmpty() {
        return count == 0;
    }

    public int size() {
        return count;
    }

    // O(1)
    public void enqueue(T newData) {
        Node<T> newNode = new Node<>(newData);
        newNode.setNextNode(null);

        if (firstNode == null) firstNode = newNode;
        else lastNode.setNextNode(newNode);
        lastNode = newNode;
        ++count;
    }

    // O(1)
    public T dequeue() {
        T data = null;
        if (firstNode != null) {
            data = firstNode.getData();
            firstNode = firstNode.getNextNode();
            if (firstNode == null) lastNode = null; // IMPORTANT**: Do not forget to check if the list had 1 element and the lastNode was pointing to the first element also. 
            --count;
        } 

        return data;
    }

}
