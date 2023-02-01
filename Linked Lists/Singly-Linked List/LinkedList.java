public class LinkedList<T> implements List<T> {

    protected Node<T> root;
    protected int len;

    // O(1) since we insert new node directly at the beginning.
    @Override
    public void insertBeginning(T data) {
        
        // If list is empty, then put the new node to the root.
        if (root == null) {
            root = new Node<T>(data);
        }

        // If list is not empty, then insert new element at the beginning since it's O(1) time than inserting at the end.
        // Note: Insertion order is not kept in this manner.
        else {
            Node<T> newNode = new Node<T>(data);
            newNode.setNextNode(root);
            root = newNode;    
        }

    }

    // It'll take O(N) since we start with the root node.
    public void insertEnd(T data) {
        Node<T> newNode = new Node<>(data);

        // If list is empty, then put the new node to the root.
        if (root == null) {
            root = newNode;
        }

        // If list is not empty, find the last element.
        else {
            Node<T> curNode = root;
            while (curNode.getNextNode() != null) {
                curNode = curNode.getNextNode();
            }
            curNode.setNextNode(newNode);
        }
    }

    @Override
    public void remove(T data) {
        // If list is empty, then return.
        if (root == null) return;
        
        // If it'll be removed from the beginning, remove.
        if (root.getData().equals(data)) root = root.getNextNode();

        // Try to find it in the list and remove.
        else {
            Node<T> curNode = root.getNextNode();
            Node<T> previousNode = root;
            boolean isFound = false;
            
            while (curNode != null && !isFound) {
                if (curNode.getData().equals(data)) {
                    previousNode.setNextNode(curNode.getNextNode());
                    curNode = null; // Notify the garbage collector, it's better.
                    --len;
                    isFound = true;
                }
                else {
                    previousNode = previousNode.getNextNode();
                    curNode = curNode.getNextNode();
                }
            }
        }
    }

    // Sending 2 pointers being one of them is 2 times faster than other solves this problem.
    // O(N)
    public Node<T> getMiddleNode() {
        Node<T> slow = root;
        Node<T> fast = root;

        while (fast != null && fast.getNextNode() != null) {
            if (fast.getNextNode().getNextNode() != null) {
                fast = fast.getNextNode().getNextNode();
                slow = slow.getNextNode();
            }
            else fast = null;
        }
    
        return slow;
    }

    // O(N)
    public void reverseList() {
        if (root == null) return;

        Node<T> prev = null;
        Node<T> cur = root;
        Node<T> next = root.getNextNode();

        while (cur != null) {
            cur.setNextNode(prev);
            
            prev = cur;
            cur = next;
            if (next != null) next = next.getNextNode();
        }

        // Change the root to the last element.
        root = prev;
    }

    // O(N)
    @Override
    public void traverse() {
        Node<T> curNode = root;
        while (curNode != null) {
            System.out.println(curNode);
            curNode = curNode.getNextNode();
        }
    }

    @Override
    public int size() {
        return len;
    }
    
}
