public class KthSmallest {
    public static <T> int numOfNodesInBST(Node<T> curNode) {
        if (curNode == null) return 0;
        int res1 = numOfNodesInBST(curNode.getLeftChild());
        int res2 = numOfNodesInBST(curNode.getRightChild());
        return res1 + res2 + 1;
    }

    // Returns null if there is no Kth smallest element in the BST.
    public static <T> T getKthSmallestInBST(Node<T> curNode, int k) {
        if (curNode == null) return null;
        
        int n = numOfNodesInBST(curNode.getLeftChild()) + 1;
        if (n == k) return curNode.getData();
        if (k < n) return getKthSmallestInBST(curNode.getLeftChild(), k);
        return getKthSmallestInBST(curNode.getRightChild(), k-n);
    }
}
