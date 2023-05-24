package lecturenotes;

public class AVLTreeRec<E extends Comparable<E>> extends BST<E> {
    
    public static class AVLNode<E extends Comparable<E>> extends Node<E> {
        private int height = 0;
        
        public AVLNode(E data) {
            super(data);
        }
    }

    @Override
    public boolean add(E data) {
        if (data == null) {                 // Check if coming data is null.
            throw new RuntimeException();
        }
        if (root == null) {                 // Check if the tree is null.
            root = new AVLNode<>(data);
            return true;
        }
        root = addRec((AVLNode<E>) root, data);    // Add the data recursively, each time return new tree node.
        return true;
    }

    private AVLNode<E> addRec(AVLNode<E> node, E data) {
        if (node == null) {
            return new AVLNode<>(data);
        }
        if (data.compareTo(node.data) < 0) {
            node.left = addRec((AVLNode<E>) node.left, data);
        }
        else if (data.compareTo(node.data) > 0) {
            node.right = addRec((AVLNode<E>) node.right, data);
        }
        updateHeight(node); // We should update height after insertion.
        return checkAndMakeRotation(node);
    }

    @Override
    public E delete(E data) {
        root = deleteRec((AVLNode<E>) root, data);
        return deleteReturned;
    }

    private AVLNode<E> deleteRec(AVLNode<E> node, E data) {
        if (node == null) {
            deleteReturned = null;
            return null;
        }
        int compareResult = data.compareTo(node.data);
        if (compareResult < 0) {
            node.left = deleteRec((AVLNode<E>) node.left, data);
        }
        else if (compareResult > 0) {
            node.right = deleteRec((AVLNode<E>) node.right, data);
        }
        else {
            if (node.left == null) return (AVLNode<E>) node.right;
            if (node.right == null) return (AVLNode<E>) node.left;
            deletePredecessor((AVLNode<E>) node.left);
            node.data = deleteReturned;
            deleteReturned = data;
        }
        updateHeight(node);
        return checkAndMakeRotation(node);
    }

    private AVLNode<E> deletePredecessor(AVLNode<E> node) {
        if (node.right == null) {
            deleteReturned = node.data;
            return (AVLNode<E>) node.left;
        }
        node.right = deletePredecessor((AVLNode<E>) node.right);
        updateHeight(node);
        return checkAndMakeRotation(node);
    }

    private void updateHeight(AVLNode<E> node) {
        node.height = Math.max(height((AVLNode<E>) node.left), height((AVLNode<E>) node.right)) + 1;
    }

    private int height(AVLNode<E> node) {
        return node != null ? node.height : 0;
    }

    private AVLNode<E> checkAndMakeRotation(AVLNode<E> node) {
        int balance = balance(node);
        if (balance > 1) {
            if (balance((AVLNode<E>) node.left) < 0) {
                node.left = rotateLeft((AVLNode<E>) node.left);
            }

            return rotateRight(node);
        }
        else if (balance < 1) {
            if (balance((AVLNode<E>) node.right) > 0) {
                node.right = rotateRight((AVLNode<E>) node.right);
            }

            return rotateLeft(node);
        }
        return node;
    }

    private int balance(AVLNode<E> node) {
        return node != null ? height((AVLNode<E>) node.left) - height((AVLNode<E>) node.right) : 0;
    }

    private AVLNode<E> rotateRight(AVLNode<E> node) {
        AVLNode<E> leftNode = (AVLNode<E>) node.left;
        AVLNode<E> grandNode = (AVLNode<E>) node.left.right;

        leftNode.right = node;
        node.left = grandNode;
        updateHeight(node);
        updateHeight(leftNode);
        return leftNode;
    }

    private AVLNode<E> rotateLeft(AVLNode<E> node) {
        AVLNode<E> rightNode = (AVLNode<E>) node.right;
        AVLNode<E> grandNode = (AVLNode<E>) node.right.left;

        rightNode.left = node;
        node.right = grandNode;
        updateHeight(node);
        updateHeight(rightNode);
        return rightNode;
    }

}
