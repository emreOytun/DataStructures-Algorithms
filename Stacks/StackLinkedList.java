public class StackLinkedList<T> {
    
    private Node<T> head = null;
    private int len = 0;

    // O(1)
    public void push(T data) {
        Node<T> newNode = new Node<>(data);

        // Since we push the newNode to the beginning, head is certainly changed;
        // so there is no need to check if the list is empty.
        newNode.setNextNode(head);
        head = newNode;
        ++len;
    }

    // O(1)
    public T pop() {
        if (head == null) return null;

        T item = head.getData();
        head = head.getNextNode();
        --len;
        return item;
    }

    public T peek() {
        if (head == null) return null;
        return head.getData();
    }

    // O(1)
    public int size() {
        return len;
    }

    // O(1)
    public boolean isEmpty() {
        return len == 0;
    }

}
