public class RecursiveLinkedList<T> extends LinkedList<T> {


    @Override
    public void insertEnd(T data) {
        Node<T> newNode = new Node<>(data);

        if (root == null) root = newNode;
        insertEnd(root, newNode);
    }

    // O(N)
    private void insertEnd(Node<T> curNode, Node<T> newNode) {
        if (curNode.getNextNode() == null) curNode.setNextNode(newNode);   
        else insertEnd(curNode.getNextNode(), newNode);
    }

    // Worst-case: O(N)
    @Override
    public void remove(T data) {
        // If list is empty, then return.
        if (root == null) return;

        // Remove from the beginning.
        if (root.getData().equals(data)) root = root.getNextNode();
        
        else remove(data, root, root.getNextNode());
    }

    private void remove(T data, Node<T> prev, Node<T> cur) {
        // If there are no more elements in the list, then return.
        if (cur == null) return;
        
        // If it's found, then remove.
        if (cur.getData().equals(data)) {
            prev.setNextNode(cur.getNextNode());
            cur = null;
        }
    
        else remove(data, cur, cur.getNextNode());
    }

    // O(N)
    @Override
    public void traverse() {
        traverse(root);
    }

    private void traverse(Node<T> curNode) {
        if (curNode != null) {
            System.out.println(curNode);
            traverse(curNode.getNextNode());
        }
    }
    
}
