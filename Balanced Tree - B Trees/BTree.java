public class BTree<E extends Comparable<E>> {

    private static class Node<E> {
        private int size = 0;
        private E[] data;
        private Node<E>[] child;

        public Node(int order) {
            data = (E[]) new Comparable[order - 1];
            child = (Node<E>[]) new Node[order];
            size = 0;
        }
    }

    private Node<E> root = null;
    private int order;

    public BTree(int order) {
        this.order = order;
    }

    private E newParent = null;
    private Node<E> newChild = null;

    public boolean insert(E item) {
        if (root == null) {
            root = new Node<E>(order);
            root.data[0] = item;
            ++root.size;
            return true;
        }
        boolean result = insert(root, item);
        // After insertion, root can be splitted so root must be changed.
        if (newChild != null) {
            Node<E> newParentNode = new Node<>(order);
            newParentNode.data[0] = newParent;
            newParentNode.child[0] = root;
            newParentNode.child[1] = newChild;
            root = newParentNode;
        }
        return result;
    }


    private boolean insert(Node<E> root, E item) {
        // If the item is found, index points the found item. Otherwise, index points where the item should be inserted (child node).
        int index = binarySearch(root.data, item, 0, root.size-1);
        
        // Check if item is found
        if (index != root.size && root.data[index].compareTo(item) == 0) {
            return false;
        }
        // Check if the node is leaf node for the insertion position
        if (root.child[index] == null) {
            if (root.size < order-1) {
                insertIntoNode(root, index, item, null);
                newChild = null;
            }
            else {
                splitNode(root, index, item, null);
            }
            return true;
        }
        // If the node is not the leaf node
        else {
            boolean result = insert(root.child[index], item);
            if (newChild != null) {
                if (root.size < order-1) {
                    insertIntoNode(root, index, newParent, newChild);
                }
                else {
                    splitNode(root, index, newParent, newChild);
                }
            }
            return result;
        }
    }

    private int binarySearch(E[] data, E item, int fi, int li) {
        if (fi > li) {
            return Math.max(fi, li); // If the item is not found, then return the max of fi and li.
        }
        int mid = (fi + li) / 2;
        if (data[mid].compareTo(item) == 0) return mid;
        if (data[mid].compareTo(item) < 0) return binarySearch(data, item, fi, mid-1);
        return binarySearch(data, item, mid+1, li);
    }

    private void insertIntoNode(Node<E> node, int index, E item, Node<E> child) {
        for (int i = node.size; i > index; --i) {
            node.data[i] = node.data[i-1];
            node.child[i+1] = node.child[i];
        }
        node.data[index] = item;
        node.child[index+1] = child;
        ++node.size; 
    }

    private void splitNode(Node<E> node, int index, E item, Node<E> child) {
        // Create newChild(new node)
        newChild = new Node<E>(order);

        // Determine number of items to move
        // order-1 = maximum key number inside a node
        int numToMove = (order-1) / 2;
        
        // If insertion point is in the right half, move one less item
        if (index > (order-1) / 2) {
            --numToMove;
        }

        // node.data'dan
        // (order-1) - numToMove -> baslangic index'inden (ikinci yari)
        // newChild.data'ya
        // 0'dan baslayarak
        // numToMove kadar tasi
        System.arraycopy(node.data, (order-1)-numToMove, newChild.data, 0, numToMove);

        // Burdaki tek fark, tasima islemi 1 ileri pozisyondan baslaycak ve 1.indeksten itibaren konulacak.
        // Cunku amac kopyaladigimiz datalarin sagindaki cocuklari kopyalamak.
        System.arraycopy(node.child, order-numToMove, newChild.child, 1, numToMove);
        
        // Set sizes
        node.size = order - 1 - numToMove;
        newChild.size = numToMove;

        // Insert new item
        if (index == (order-1)/2) { // Insert as middle item
            newParent = item;
            newChild.child[0] = child;
        }
        else {
            if (index < (order-1)/2) {
                insertIntoNode(node, index, item, child);
            }
            else {
                insertIntoNode(newChild, index - (order - 1) / 2 - 1, item, child);
            }
            // The rightmost item of the node will be newParent.
            newParent = node.data[node.size-1];
            newChild.child[0] = node.child[node.size];
            --node.size;
        }

         // Remove references at the end.
         for (int i = node.size; i < node.data.length; ++i) {
            node.data[i] = null;
            node.child[i+1] = null;
        }
        
    }


}