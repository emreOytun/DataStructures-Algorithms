import java.sql.RowId;
import java.util.Random;

public class BinaryHeap<E extends Comparable<E>> extends BinaryTree<E> implements SearchTree<E> {

    private static class HeapNode<E> {
        private HeapNode<E> parent = null;
        private HeapNode<E> left = null;
        private HeapNode<E> right = null;
        private E data = null;

        public HeapNode() {}
        public HeapNode(E data) {
            this.data = data;
        }
        public HeapNode(E data, HeapNode<E> left, HeapNode<E> right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }

    private HeapNode<E> root = null;

    /* // O(N) - worst-case
    private HeapNode<E> findInbalancedNode(HeapNode<E> curNode) {
        if (curNode.left == null && curNode.right == null) return null;
        if (curNode.left == null && curNode.right != null) return curNode;
        HeapNode<E> leftResult = findInbalancedNode(curNode.left);
        if (leftResult != null) return leftResult;
        HeapNode<E> rightResult = findInbalancedNode(curNode.right);
        if (rightResult != null) return rightResult;
        return null;
    }

    // O(logN)
    private HeapNode<E> findLeftMostNode(HeapNode<E> curNode) {
        if (curNode.left == null) return curNode;
        return findLeftMostNode(curNode.left);
    } */

    /* 
    @Override
    public boolean add(E item) {
        if (item == null) {
            throw new NullPointerException();
        }

        HeapNode<E> newNode = new HeapNode<>(item);
        // Liste bossa
        if (root == null) {
            root = newNode;
        }
        else {
            // Last inbalanced node'u bul.
            HeapNode<E> inbalancedNode = findInbalancedNode(root);
            // Inbalanced node yoksa en soldaki node'u bul.
            if (inbalancedNode == null) {
                HeapNode<E> leftMostNode = findLeftMostNode(root);
                leftMostNode.left = newNode;
                newNode.parent = leftMostNode;
            }
            // Inbalanced node varsa
            else {
                inbalancedNode.right = newNode;
                newNode.parent = inbalancedNode;  
            }
        }
        heapifyUp(newNode);
        return true;
    }
    */

    @Override
    public boolean add(E item) {
        if (item == null) {
            throw new NullPointerException();
        }

        HeapNode<E> newNode = new HeapNode<>(item);
        if (root == null) {
            root = newNode;
        }
        else if (root.left == null) {
            root.left = newNode;
            newNode.parent = root;
            heapifyUp(root.left);
        }
        else if (root.right == null) {
            root.right = newNode;
            newNode.parent = root;
            heapifyUp(root.right);
        }

        else {
            // targetIndex
            // bottomStartIndex
            // bottomHalfElements
            // curNode

        
            HeapNode<E> parent = findParentOfIndex(size);
            // Set parent's pointer to left or right and new node's parent.
            newNode.parent = parent;
            if (parent.left == null) parent.left = newNode;
            else parent.right = newNode;
        }

        heapifyUp(newNode);
        ++size;
        return true;
    }

    private HeapNode<E> findParentOfIndex(int targetIndex) {
        // Calculate the bottom counts.
        int bottomTotalElements = bottomRowTotalElements(targetIndex);
        int bottomStartIndex = bottomTotalElements - 1;
        return findParentOfIndex(targetIndex, bottomStartIndex, bottomTotalElements/2, root);
    }

    private int height(int targetIndex) {
        int result = (int)Math.floor(log2(targetIndex)); 
        return result + 1;
    }

    private int bottomRowTotalElements(int targetIndex) {
        return (int) Math.pow(2, height(targetIndex+1)-1);
    }

    private static double log2(double num) {
        return Math.log(num) / Math.log(2);
    }

    private HeapNode<E> findParentOfIndex(int targetIndex, int bottomStartIndex, int bottomHalfElements, HeapNode<E> curNode) {
        // If there is 1 element in each half, then the current node is the last parent node.
        if (bottomHalfElements == 1) return curNode;
        int lastIndexHalf = bottomStartIndex + bottomHalfElements - 1;
        if (targetIndex <= lastIndexHalf) {
            return findParentOfIndex(targetIndex, bottomStartIndex, bottomHalfElements/2, curNode.left);
        }
        return findParentOfIndex(targetIndex, bottomStartIndex + bottomHalfElements, bottomHalfElements/2, curNode.right);
    } 


    private void heapifyUp(HeapNode<E> curNode) {
        if (curNode == null || curNode.parent == null) return;
        if (curNode.data.compareTo(curNode.parent.data) < 0) {
            swapNodes(curNode, curNode.parent);
            heapifyUp(curNode.parent);
        } 
    }

    private void swapNodes(HeapNode<E> first, HeapNode<E> second) {
        E temp = first.data;
        first.data = second.data;
        second.data = temp;
    }

    @Override
    public boolean contains(E item) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'contains'");
    }

    @Override
    public E find(E target) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'find'");
    }

    @Override
    public E delete(E target) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public boolean remove(E target) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'remove'");
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public E delete() {
        if (root == null) return null;
        
        // Silinecek eleman
        E data = root.data;
        if (size == 1) {
            root = null;
        }
        else {
            HeapNode<E> lastParent = findParentOfIndex(size-1);
            HeapNode<E> lastNode = lastParent.left;
            if (lastParent.right != null) lastNode = lastParent.right;
            
            swapValues(root, lastNode);
            if (lastParent.right != null) lastParent.right = null;
            else lastParent.left = null;
            heapifyDown(root);
        }
        --size;
        return data;
    }

    private void heapifyDown(HeapNode<E> curNode) {
        if (curNode == null || curNode.left == null && curNode.right == null) return;
       
        if (curNode.left != null && curNode.left.data.compareTo(curNode.data) < 0) {
            if (curNode.right != null && curNode.right.data.compareTo(curNode.data) < 0) {
                if (curNode.right.data.compareTo(curNode.left.data) < 0) {
                    swapValues(curNode, curNode.right);
                    heapifyDown(curNode.right);
                }
                else {
                    swapValues(curNode, curNode.left);
                    heapifyDown(curNode.left);
                }
            }
            else {
                swapValues(curNode, curNode.left);
                heapifyDown(curNode.left);
            }
        }
        else if (curNode.right != null && curNode.right.data.compareTo(curNode.data) < 0) {
            swapValues(curNode, curNode.right);
            heapifyDown(curNode.right);
        }

    }

    private void swapValues(HeapNode<E> first, HeapNode<E> second) {
        E temp = first.data;
        first.data = second.data;
        second.data = temp;
    }

    public static void main(String[] args) {
        BinaryHeap<Integer> heap = new BinaryHeap<>();
        
        // Test adding elements to the heap
        System.out.println("Adding elements to heap:");
        heap.add(22);
        heap.add(88);
        heap.add(35);
        System.out.println("here");
        heap.add(48);
        heap.add(43);
        heap.add(53);
        heap.add(8);
        heap.add(70);
        heap.add(9);
        
        // Test deleting elements from the heap
        System.out.println("Deleting elements from heap:");
        while (!heap.isEmpty()) {
            System.out.println("Deleted " + heap.delete() + " from heap");
        }

    }

}
