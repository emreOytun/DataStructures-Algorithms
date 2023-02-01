public class Node<T> {

    // T can also be a type that extends Cloneable interface so that when we set data, there is no confusion.
    private T data;
    private Node<T> nextNode;

    public Node(T data) {
        this.data = data;
        nextNode = null;
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

    @Override
    public String toString() {
        return "Data=" + data;
    }

}