import java.util.Iterator;
import java.util.Stack;

public class BST<E extends Comparable<E>> extends BinaryTree<E> implements SearchTree<E> {

    private E deleteReturned = null;

    public BST() {
        super();
    }

    public BST(E data, BinaryTree<E> leftTree, BinaryTree<E> rightTree) {
        super(data, leftTree, rightTree);
    }

    public BST(Node<E> root) {
        super(root);
    }
     
    @Override
    public boolean add(E data) throws NullPointerException {
        if (data == null) {
            throw new NullPointerException();
        }
        if (root == null) {
            Node<E> newNode = new Node<>(data);
            root = newNode;
            return true;
        }
        add(data, root);
        ++size;
        return true;
    }

    private void add(E data, Node<E> curNode) {
        if (data.compareTo(curNode.data) < 0) {
            if (curNode.left == null) curNode.left = new Node<>(data);
            else add(data, curNode.left);
        }
        else if (data.compareTo(curNode.data) > 0) {
            if (curNode.right == null) curNode.right = new Node<>(data);
            else add(data, curNode.right);
        }
    }

    @Override
    public E delete(E target) {
        root = delete(target, root);
        return deleteReturned;
    }

    private Node<E> delete(E target, Node<E> curNode) {
        if (curNode == null) {
            deleteReturned = null;
            return curNode;
        }
        
        int compareResult = target.compareTo(curNode.data);
        if (compareResult < 0) {
            curNode.left = delete(target, curNode.left);
            return curNode;
        }
        else if (compareResult > 0) {
            curNode.right = delete(target, curNode.right);
            return curNode;
        }
        else {
            deleteReturned = curNode.data;
            return deleteNode(curNode);
        }
    }

    // Return the new subtree after deleting the curNode.
    private Node<E> deleteNode(Node<E> curNode) {
        if (curNode.left == null) return curNode.right;
        if (curNode.right == null) return curNode.left;
        if (curNode.left.right == null) {
            curNode.data = curNode.left.data;
            curNode.left = curNode.left.left;
            return curNode;
        }
        E predecessorElement = findAndDeletePredecessor(curNode.left);
        curNode.data = predecessorElement;
        return curNode;
    }

    // To delete a node:
    // 1) If we keep parent inside node, it's easy.
    // 2) Otherwise, we can return the new tree back to assign the parent.
    // 3) Or we can copy the node that is replacing this node to this node, and in that way the node that we copy will be deleted.
    // 4) We can check in every iteration if we will delete the next item not this one.
    private E findAndDeletePredecessor(Node<E> curNode) {
        if (curNode.right.right == null) {
            E data = curNode.right.data;
            curNode.right = curNode.right.left;
            return data;
        }
        return findAndDeletePredecessor(curNode.right);
    }

    @Override
    public boolean remove(E target) {
        E item = delete(target);
        return item != null;
    }

    @Override
    public boolean contains(E item) {
        return find(item) != null;
    }

    @Override
    public E find(E target) {
        if (target == null) return null;
        return find(target, root);
    }

    private E find(E target, Node<E> root) {
        if (root == null) return null;
        int compare = root.data.compareTo(target);
        if (compare > 0) return find(target, root.left);
        if (compare < 0) return find(target, root.right);
        return root.data;
    }

    public Iterator<E> iterator() {
        return new BSTIterator();
    }

    private class BSTIterator implements Iterator<E> {
        private Node<E> nextItem;
        private Node<E> lastItemReturned = null;
        private Stack<Node<E>> pathStack = new Stack<>();

        public BSTIterator() {
            if (root == null) nextItem = null;
            else {
                Node<E> curNode = root;
                while (curNode.left != null) {
                    pathStack.push(curNode);
                    curNode = curNode.left;
                }
                nextItem = curNode;
            }
        }

        public boolean hasNext() {
            return nextItem != null;
        }

        // RECURSIVE FONKSIYONUMUZ 2 STATE'LI:
        // 1) Ilk fonksiyon cagrisi yapildiginda sol tarafi check ederek olabildigince sola gidecek sekilde recursive cagrilari yap, kendini stack'e pushla.
        // 2) next() methodu cagrildiginda state-1 atlanmis haldedir. Sag tarafin olup olmadigini check et, sag taraf varsa yeni bir fonksiyon cagrisi yapilacak ancak suanki fonksiyon stack'e bir daha pushlanmayacak.
        public E next() {
            if (!hasNext()) {
                throw new RuntimeException();
            }
            lastItemReturned = nextItem;
            if (nextItem.right != null) { // State-2: State-1 bitti, sag tarafi check et.
                nextItem = nextItem.right; // Once diger fonksiyon icin nextItem.right'a git.
                while (nextItem.left != null) { // Bu yeni fonksiyon sol tarafa ilerleyecekse ilerlet.
                    pathStack.push(nextItem);
                    nextItem = nextItem.left;
                }
            }
            else { // State-2 Devami: State-2'den sonra baska bir stack olmadigindan bu fonksiyon bir daha stack'e pushlanmiyor. Stack'ten oncekini cek.
                if (pathStack.isEmpty()) nextItem = null;
                else nextItem = pathStack.pop();
            }
            return lastItemReturned.data; 
        }

        public void remove() {
            if (lastItemReturned == null) {
                throw new IllegalStateException();
            }
        }

    }

    public static void main(String[] args) {
        BST<Integer> bst = new BST<>();
        bst.add(10);
        bst.add(15);
        bst.add(7);
        bst.add(5);
        System.out.println(bst);
    
        Iterator<Integer> it = bst.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }
    
}