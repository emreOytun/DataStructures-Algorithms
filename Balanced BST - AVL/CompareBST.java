public class CompareBST {
    
    // Compares the binary search trees such that;
    // Returns true if trees have same values and same topology.
    public static <T extends Comparable<T>> boolean compareBST(Node<T> node1, Node<T> node2) {
        // Case 1) If at least one of the nodes is null, then return true if both them are null.
        if (node1 == null || node2 == null) return node1 == node2;
    
        // Case 2) Check if their data is equal or not.
        if (node1.getData().compareTo(node2.getData()) != 0) return false;

        // Case 3) Both the left subtree and right subtree must be equal to return true.
        return compareBST(node1.getLeftChild(), node2.getLeftChild()) && compareBST(node1.getRightChild(), node2.getRightChild());
    }
    
}
