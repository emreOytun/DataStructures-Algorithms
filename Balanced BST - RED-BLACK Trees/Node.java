public class Node<T> {
    
    private Node<T> leftChild;
    private Node<T> rightChild;
    private Node<T> parentNode; // IMPORTANT**: When impleneting the remove method, having parent node is crucial.
    private T data;
    private int height; // AVL Trees rely heavily on the height parameter.
    
    public Node(T data) {
        this.leftChild = null;
        this.rightChild = null;
        this.parentNode = null;
        this.data = data;
        this.height = 0;
    }

    public Node(T data, Node<T> parentNode) {
        this(data);
        this.parentNode = parentNode;
    }

    public Node<T> getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(Node<T> leftChild) {
        this.leftChild = leftChild;
    }

    public Node<T> getRightChild() {
        return rightChild;
    }

    public void setRightChild(Node<T> rightChild) {
        this.rightChild = rightChild;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Node<T> getParentNode() {
        return parentNode;
    }

    public void setParentNode(Node<T> parentNode) {
        this.parentNode = parentNode;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return "" + data;
    }

}
