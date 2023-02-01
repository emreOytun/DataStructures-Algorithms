public class Node<T> {

    private T data;
    // Doubly-linked list needs more memory than singly-linked list.
    private Node<T> nextNode;
    private Node<T> prevNode;

    public Node(T data) {
        this.data = data;
        nextNode = null;
        prevNode = null;
    }

    public T getData() {
        return data;
    }
    public void setData(T data) {
        this.data = data;
    }
    public Node<T> getNextNode() {
        return nextNode;
    }
    public void setNextNode(Node<T> nextNode) {
        this.nextNode = nextNode;
    }
    public Node<T> getPrevNode() {
        return prevNode;
    }
    public void setPrevNode(Node<T> prevNode) {
        this.prevNode = prevNode;
    }

    @Override
    public String toString() {
        return "Data: " + data;
    }
}