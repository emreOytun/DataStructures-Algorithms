public class Main {
    public static void main(String[] args) {
        BinarySearchTree<Integer> t1 = new BinarySearchTree<>();
        t1.insert(3);
        t1.insert(5);
        t1.insert(6);
        t1.insert(7);

        System.out.println("--------------");
        t1.traversal();

        
        System.out.println("--------------");
        System.out.println(t1.getMax());
        System.out.println(t1.getMin());
    
        
        System.out.println("--------------");
        t1.remove(5);
        t1.traversal();
    
        System.out.println("--------------");
        System.out.println(KthSmallest.getKthSmallestInBST(t1.getRoot(), 2));
        
        System.out.println(KthSmallest.getKthSmallestInBST(t1.getRoot(), 5));
    }
    
}
