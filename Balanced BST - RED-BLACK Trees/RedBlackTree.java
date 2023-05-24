public class RedBlackTree<E extends Comparable<E>> {

    private static class Node<E> {
        private E data;
        private Node<E> left = null;
        private Node<E> right = null;
        private Node<E> parent = null;
        private Color color = Color.RED;

        public Node(E data) {
            this.data = data;
        }
    
        public boolean isLeftChild() {
            if (parent == null) return false;
            return this == parent.left;
        }

        public void flipColor() {
            color = color == Color.RED ? Color.BLACK : Color.RED;   
        }
    }

    private Node<E> root = null;

    private boolean addReturned;
    public boolean add(E data) {
        Node<E> newNode = new Node<>(data);
        if (root == null) {
            root = newNode;
            root.color = Color.BLACK;
            return true;
        }
        root = add(root, newNode);
        recolorAndRotate(newNode);
        root.color = Color.BLACK; // Her zaman root color BLACK olamli.
        return addReturned;
    }

    private Node<E> add(Node<E> node, Node<E> newNode) {
        if (node == null) {
            addReturned = true;
            return newNode;
        }
        if (newNode.data.compareTo(node.data) < 0) {
            node.left = add(node.left, newNode);
            if (addReturned) node.left.parent = node;
        }
        else if (newNode.data.compareTo(node.data) > 0) {
            node.right = add(node.right, newNode);
            if (addReturned) node.right.parent = node;
        }
        else { // Esitse eklenmeyecek.
            addReturned = false;
        }
        return node;
    }

    private void recolorAndRotate(Node<E> node) {
        Node<E> parent = node.parent;
        if (node != root && parent.color == Color.RED) {
            Node<E> grandParent = parent.parent;
            Node<E> uncle = parent.isLeftChild() ? grandParent.right : grandParent.left;
            // Uncle is red, only recoloring
            if (uncle != null && uncle.color == Color.RED) {
                uncle.flipColor();
                parent.flipColor();
                grandParent.flipColor();
                recolorAndRotate(grandParent);
            }
            // Uncle is black and parent is left-child of the grandparent.
            // Left-left, left-right
            else if (parent.isLeftChild()) {
                // Node is the right-child of the parent.
                // Left-right
                boolean leftRight = false;
                if (!node.isLeftChild()) {
                    rotateLeft(parent);
                    leftRight = true;
                }

                parent.flipColor();
                grandParent.flipColor();
                rotateRight(grandParent);
                recolorAndRotate(leftRight ? grandParent : parent);
            }
            // Uncle is balck and parent is right-child of the grandparent.
            // Right-right, right-left
            else {
                boolean rightLeft = false;
                if (node.isLeftChild()) {
                    rotateRight(parent);
                    rightLeft = true;
                }

                parent.flipColor();
                grandParent.flipColor();
                rotateLeft(grandParent);
                recolorAndRotate(rightLeft ? grandParent : parent);
            }
        }
    }








}