public class BinarySearchTree<T extends Comparable<T>> implements Tree<T> {

    private Node<T> root;
    
    public BinarySearchTree() {
        this.root = null;
    }

    public Node<T> getRoot() {
        return root;
    }

    public boolean isBalanced() {
        if (isBalanced(root) == -1) return false;
        return true;
    }

    // BALANCED BST: It means number of nodes in subtrees are equal. (NOT HEIGHTS)
    // ISBALANCED FUNCTION: O(N)
    // 1) Return number of nodes upto the current node if subtrees are balanced.
    // 2) Return -1 -1 meaning subtrees are inbalanced
    // 3) Return values except -1 means the total number of nodes upto the current node. 
    private int isBalanced(Node<T> curNode) {
        if (curNode == null) return 0; // 0 nodes upto this node. Tree is balanced.
    
        int leftNum = isBalanced(curNode.getLeftChild());
        int rightNum = isBalanced(curNode.getRightChild());
    
        if (leftNum >= 0 && rightNum >= 0 && leftNum == rightNum) {
            return leftNum + rightNum + 1;
        } 
        else return -1;
    
    }

    @Override
    public void insert(T data) {
        // If the tree is empty, then put the newNode to the root; changing the root.
        if (root == null) {
            root = new Node<>(data, null);
            return;
        }
    
        insertLoop(data, root);
    }

    // Makes the insertion with loop.
    private void insertLoop(T data, Node<T> curNode) {
        boolean isDone = false;
        while(!isDone) {     
            // When the data is SMALLER than the data in the node
            // GO TO THE LEFT SUBTREE
            if (data.compareTo(root.getData()) < 0) {
                // The left child is null, so we can insert the new node
                if (curNode.getLeftChild() == null) {
                    curNode.setLeftChild(new Node<>(data, curNode));
                    isDone = true;
                }
                // If it's not null, then go to the left child
                else {
                    curNode = curNode.getLeftChild();
                }
            }

            // When the data is GREATER than the data in the node
            // GO TO THE RIGHT SUBTREE
            else {
                // The right child is null, so wen can insert the new node
                if (curNode.getRightChild() == null) {
                    curNode.setRightChild(new Node<>(data, curNode));
                    isDone = true;
                }
                // If it's not null, then go to the right child
                else {
                    curNode = curNode.getRightChild();
                }
            }
        }
    }

    // It's the recursive version of the above function.
    private void insertRec(T data, Node<T> curNode) {
        if (data.compareTo(curNode.getData()) < 0) {
            if (curNode.getLeftChild() == null) {
                curNode.setLeftChild(new Node<>(data, curNode));
            }
            else {
                insertRec(data, curNode.getLeftChild());
            }
        }
    
        else {
            if (curNode.getRightChild() == null) {
                curNode.setRightChild(new Node<>(data, curNode));
            }
            else {
                insertRec(data, curNode.getRightChild());
            }
        }
    }

    @Override
    public void traversal() {
        traverseRec(root);
    }

    private void traverseRec(Node<T> curNode) {
        if (curNode == null) return;

        traverseRec(curNode.getLeftChild());
        System.out.println(curNode);
        traverseRec(curNode.getRightChild());
    }

    @Override
    public T getMax() {
        // If the tree is empty, then return null.
        if (root == null) return null;
        return getMaxLoop(root);
    }

    private T getMaxLoop(Node<T> curNode) {
        T max = null;
        boolean isDone = false;
        while (!isDone) {
            if (curNode.getRightChild() == null) {
                max = curNode.getData();
                isDone = true;
            }
            else {
                curNode = curNode.getRightChild();
            }
        }
    
        return max;
    }

    // Recursive version of the function above.
    private T getMaxRec(Node<T> curNode) {
        if (curNode.getRightChild() == null) {
            return curNode.getData();
        }
        return getMaxRec(curNode.getRightChild());
    }

    @Override
    public T getMin() {
        if (root == null) return null;
        return getMinLoop(root);
    }

    private T getMinLoop(Node<T> curNode) {
        T min = null;
        boolean isDone = false;
        while (!isDone) {
            if (curNode.getLeftChild() == null) {
                min = curNode.getData();
                isDone = true;
            }
            else {
                curNode = curNode.getLeftChild();
            }
        }
    
        return min;
    }

    private T getMinRec(Node<T> curNode) {
        if (curNode.getLeftChild() == null) {
            return curNode.getData();
        }
        return getMaxRec(curNode.getLeftChild());
    }

    @Override
    public void remove(T data) {
        Node<T> node = find(data, root);
        removeNode(node);
    }

    private void removeNode(Node<T> node) {
        // If the given node is null, then just return.
        if (node == null) return;

        // Case 1) Node does not have any child.
        if (node.getLeftChild() == null && node.getRightChild() == null) {
            Node<T> parent = node.getParentNode();

            // If curNode is the rootNode then parentNode is null, so we are deleting the root node.
            if (parent == null) {
                root = null;
            }

            // Set the parent child's reference to the null.
            // The node is the right child.
            else if (parent.getRightChild() == node) {
                node.getParentNode().setRightChild(null);
            }
            // The node is the left child.
            else {
                node.getParentNode().setLeftChild(null);
            }

            // Release the reference for Garbace Collector.
            node = null;
        }

        // Case 2) Node has 1 single child.
        else if (node.getLeftChild() == null && node.getRightChild() != null) {
            Node<T> parent = node.getParentNode();

            // The node that is being deleted can be the root node.
            if (parent == null) {
                root = node.getRightChild();
            }

            else if (parent.getRightChild() == node) {
                parent.setRightChild(node.getRightChild());
            }
            else {
                parent.setLeftChild(node.getRightChild());
            }

            // Change the child's parent to the parent of this node.
            node.getRightChild().setParentNode(parent);
            node = null;
        }
        else if (node.getLeftChild() != null && node.getRightChild() == null) {
            Node<T> parent = node.getParentNode();

            // The node that is being deleted can be the root node.
            if (parent == null) {
                root = node.getLeftChild();
            }

            else if (parent.getRightChild() == node) {
                parent.setRightChild(node.getLeftChild());
            }
            else {
                parent.setLeftChild(node.getLeftChild());
            }

            node.getLeftChild().setParentNode(parent);
            node = null;
        }

        // Case 3) Node has 2 childs.
        else {
            Node<T> predecessor = getMaxNode(node.getLeftChild());
            
            // Swap the values
            T temp = predecessor.getData();
            predecessor.setData(node.getData());
            node.setData(temp);

            removeNode(node);
        }
    }

    private Node<T> find(T data, Node<T> curNode) {
        if (curNode == null) return null;
        if (curNode.getData().compareTo(data) == 0) return curNode;
        if (data.compareTo(curNode.getData()) < 0) return find(data, curNode.getLeftChild());
        return find(data, curNode.getRightChild());
    }

    private Node<T> getMaxNode(Node<T> curNode) {
        if (curNode.getRightChild() == null) return curNode;
        return getMaxNode(curNode.getRightChild());
    }
}