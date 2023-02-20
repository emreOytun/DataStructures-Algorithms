public class AVLTree<T extends Comparable<T>> implements Tree<T> {

    private Node<T> root;
    
    public AVLTree() {
        this.root = null;
    }

    public Node<T> getRoot() {
        return root;
    }

    // O(logN)
    @Override
    public void insert(T data) {
        // If the tree is empty, then put the newNode to the root; changing the root.
        if (root == null) {
            root = new Node<>(data, null);
            return;
        }
    
        insertRec(data, root);
    }

    // O(logN)
    // It's the recursive version of the above function.
    private void insertRec(T data, Node<T> curNode) {
        if (data.compareTo(curNode.getData()) < 0) {
            if (curNode.getLeftChild() == null) {
                curNode.setLeftChild(new Node<>(data, curNode));
                        
                // SETTLE THE VIOLATION AFTER INSERTION
                settleViolations(curNode);
            }
            else {
                insertRec(data, curNode.getLeftChild());
            }
        }
    
        else {
            if (curNode.getRightChild() == null) {
                curNode.setRightChild(new Node<>(data, curNode));
        
                // SETTLE THE VIOLATION AFTER INSERTION
                settleViolations(curNode);
            }
            else {
                insertRec(data, curNode.getRightChild());
            }
        }

    }

    @Override
    // O(logN)
    public void remove(T data) {
        if (root != null) {
            remove(data, root);
        }
    }

    // O(logN)
    private void remove(T data, Node<T> node) {
        // If data is not found, then node will be null at the end, just return.
        if (node == null) return;

        if (data.compareTo(node.getData()) < 0) {
            remove(data, node.getLeftChild());
        }
        else if (data.compareTo(node.getData()) > 0) {
            remove(data, node.getRightChild());
        }
        
        // We have found the node we want to delete.
        else {
            // Case 1) Node does not have any child. (Leaf node situation)
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

                // SETTLE VIOLATIONS AFTER REMOVAL
                settleViolations(parent);
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
                
                // SETTLE VIOLATIONS AFTER REMOVAL
                settleViolations(parent);
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
                
                // SETTLE VIOLATIONS AFTER REMOVAL
                settleViolations(parent);
            }

            // Case 3) Node has 2 childs.
            else {
                Node<T> predecessor = getMaxNode(node.getLeftChild());
                
                // Swap the values
                T temp = predecessor.getData();
                predecessor.setData(node.getData());
                node.setData(temp);

                remove(data, predecessor); // NO NEED TO SETTLE VIOLATIONS IN THIS PART, BECAUSE WHILE REMOVIONG PREDECESSOR NODE; IT'LL BE CHECKED.
            }
        
        }

        // CHECK THE BALANCE FOR EVERY NODE WE PASSED THROUGH REMOVAL
        updateHeight(node); // When we update the height of the curNode, after this recursive call closed; the one that calls this one updates its node's height truely, and checks balance.
        int balanceFactor = getBalanceFactor(node);
        if (balanceFactor > 1) rightRotation(node);
        else if (balanceFactor < -1) leftRotation(node);    
    }

    // Finds the node with the given data.
    // O(logN)
    private Node<T> find(T data, Node<T> curNode) {
        if (curNode == null) return null;
        if (curNode.getData().compareTo(data) == 0) return curNode;
        if (data.compareTo(curNode.getData()) < 0) return find(data, curNode.getLeftChild());
        return find(data, curNode.getRightChild());
    }

    // Finds the node that carries max element.
    // O(logN)
    private Node<T> getMaxNode(Node<T> curNode) {
        if (curNode.getRightChild() == null) return curNode;
        return getMaxNode(curNode.getRightChild());
    }

    public int height() {
        return height(root);
    }

    // Returns the height parameter for a given node
    // O(1)
    private int height(Node<T> node) {
        if (node == null) return -1;
        return node.getHeight();
    }

    // Updates the height of a given node
    // O(1)
    private void updateHeight(Node<T> node) {
        if (node != null) {
            node.setHeight(Math.max(height(node.getLeftChild()), height(node.getRightChild())) + 1);
        }
    }

    private int getBalanceFactor(Node<T> node) {
        if (node == null) return 0;
        return height(node.getLeftChild()) - height(node.getRightChild());
    }

    // O(1)
    private void rightRotation(Node<T> node) {
        System.out.println("Right rotation on node" + node);

        Node<T> nodeParent = node.getParentNode();
        Node<T> leftChild = node.getLeftChild();
        Node<T> grandChild = leftChild.getRightChild();
        

        // 1) Update the parent node of the node; so that one of its childs is the left child.
        leftChild.setParentNode(nodeParent);
        // If the parent of the node is null, then it means it's the root. Change the root.
        if (nodeParent == null) {
            root = leftChild;
        }
        // Otherwise find which child of the parent is the node, and change it to the leftChild.
        else {
            if (nodeParent.getRightChild() == node) {
                nodeParent.setRightChild(leftChild);
            }
            else {
                nodeParent.setLeftChild(leftChild);
            }
        }

        // 2) Change left child's right child to the node, and update the node's parent.
        leftChild.setRightChild(node);
        node.setParentNode(leftChild); // We have to handle the parents for node

        node.setLeftChild(grandChild);
        // We have to handle the parents
        if (grandChild != null) {
            grandChild.setParentNode(node);
        }

        // Update the height parameters of the nodes after rotation; node and leftChild's heigth can be changed.
        updateHeight(node);
        updateHeight(leftChild);
    }

    // O(1)
    private void leftRotation(Node<T> node) {
        System.out.println("Left rotation on node" + node);

        Node<T> nodeParent = node.getParentNode();
        Node<T> rightChild = node.getRightChild();
        Node<T> grandChild = rightChild.getLeftChild();
        

        // 1) Update the parent node of the node; so that one of its childs is the left child.
        rightChild.setParentNode(nodeParent);
        // If the parent of the node is null, then it means it's the root. Change the root.
        if (nodeParent == null) {
            root = rightChild;
        }
        // Otherwise find which child of the parent is the node, and change it to the leftChild.
        else {
            if (nodeParent.getRightChild() == node) {
                nodeParent.setRightChild(rightChild);
            }
            else {
                nodeParent.setLeftChild(rightChild);
            }
        }

        // 2) Change left child's right child to the node, and update the node's parent.
        rightChild.setLeftChild(node);
        node.setParentNode(rightChild); // We have to handle the parents for node

        node.setRightChild(grandChild);
        // We have to handle the parents
        if (grandChild != null) {
            grandChild.setParentNode(node);
        }

        // Update the height parameters of the nodes after rotation; node and leftChild's heigth can be changed.
        updateHeight(node);
        updateHeight(rightChild);
    }

    // After every insertion or removal, we have to check videolations from this node up to the root node.
    // O(logN) in worst-case
    private void settleViolations(Node<T> node) {
        // We have to to this for the root as well, therefore we have to check until the node is null (since after root, it'll be null)
        while (node != null) {
            updateHeight(node);
            settleViolationsHelper(node);
            node = node.getParentNode();
        }
    }

    private void settleViolationsHelper(Node<T> node) {
        int balance = getBalanceFactor(node);

        // If a node is imbalanced, then there must be at least a node in the left or right child of this node.
        // If it's left-heavy situation, then there must be left-child of this node.

        // Left-heavy situation BUT it can be left-right heavy or left-left heavy
        if (balance > 1) {
            // left-right heavy situation: Left-child has a balance factor such that it's right-heavy.
            if (getBalanceFactor(node.getLeftChild()) < 0) {
                leftRotation(node.getLeftChild());
            }

            // in left-left heavy situation, then just a single right rotation is needed.
            // but in a left-right heavy situation, firstly a leftRotation is needed in the left child.
            rightRotation(node);
        }

        // right-heavy situation BUT it can be right-left heavy or right-right heavy
        else if (balance < -1) {
            if (getBalanceFactor(node.getRightChild()) > 0) {
                rightRotation(node.getRightChild());
            }
            leftRotation(node);
        }
     

    }
 
    public void printLefts() {
        printLefts(root);
    }

    private void printLefts(Node<T> curNode) {
        if (curNode == null) return;
        System.out.println(curNode.getData());
        printLefts(curNode.getLeftChild()); 
    }

    // In-order(sorted) traverse
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

}