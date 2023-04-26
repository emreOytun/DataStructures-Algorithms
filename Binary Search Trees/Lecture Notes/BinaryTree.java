import java.util.LinkedList;
import java.util.Scanner;


public class BinaryTree<E> {
    
    protected static class Node<E> {
        protected E data;
        protected Node<E> left = null;
        protected Node<E> right = null;
        
        public Node(E data) {
            this.data = data;
        }

        public Node(E data, Node<E> left, Node<E> right) {
            this(data);
            this.left = left;
            this.right = right;
        }
        
        @Override
        public String toString() {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(data.toString());
            return stringBuilder.toString();
        }
    }

    protected Node<E> root = null;
    protected int size = 0;

    public BinaryTree() {}
    public BinaryTree(Node<E> root) {
        this.root = root;
    }
    public BinaryTree(E data, BinaryTree<E> leftTree, BinaryTree<E> rigthTree) {
        root = new Node<>(data, leftTree.root, rigthTree.root);
        size = 1;
        if (leftTree != null) size += leftTree.size;
        if (rigthTree != null) size += rigthTree.size;
    }

    public E getData() {
        return root.data;
    }

    public BinaryTree<E> getLeftSubtree() {
        return new BinaryTree<>(root.left);
    }

    public BinaryTree<E> getRightSubtree() {
        return new BinaryTree<>(root.right);
    }

    public int height() {
        return height(root);
    }

    private int height(Node<E> curNode) {
        if (curNode == null) return 0;  // If node is null return 0.
        return 1 + height(curNode.left) + height(curNode.right); // Otherwise return 1 + height left + height right
    }

    public boolean isBalanced() {
        if (isBalanced(root) != -1) return true;
        return false;
    }

    private int isBalanced(Node<E> curNode) {
        if (curNode == null) return 0;
        int leftNum = isBalanced(curNode.left);
        int rightNum = isBalanced(curNode.right);
        if (leftNum >= 0 && rightNum >= 0 && leftNum == rightNum) {
            return 1 + leftNum + rightNum;
        }
        return -1;
    }


    public boolean isLeaf() {
        return root.left == null && root.right == null;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        preOrderTraverse(root, 1, stringBuilder);
        return stringBuilder.toString();
    }

    public void preOrderTraverse(Node<E> node, int depth, StringBuilder sb) {
        for (int i = 1; i < depth; ++i) {
            sb.append(" ");
        }
        if (node == null) {
            sb.append("null\n");
        }
        else {
            sb.append(node.toString());
            sb.append("\n");
            preOrderTraverse(node.left, depth+1, sb);
            preOrderTraverse(node.right, depth+1, sb);
        }
    }

    // Traverse the tree level-by-level each time printing one level and continuing to the next level.
    public void breadthFirstTraverse() {
        // Use LinkedList as Queue
        LinkedList<Node<E>> list = new LinkedList<>();
        list.addFirst(root);
        while (!list.isEmpty()) {
            Node<E> curNode = list.getFirst();
            System.out.println(curNode);
            list.addLast(root.left);  // Add left and right children to the queue
            list.addLast(root.right);
            list.removeFirst();
        }
    }

    public static BinaryTree<String> readBinaryTree(Scanner scan) {
        String data = scan.next();
        data = data.trim();
        if (data.equals("null")) {
            return null;
        }
        BinaryTree<String> left = readBinaryTree(scan);
        BinaryTree<String> right = readBinaryTree(scan);
        return new BinaryTree<String>(data, left, right);
    }

}
