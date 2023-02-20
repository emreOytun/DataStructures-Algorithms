public class Main {
    public static void main(String[] args) {
        AVLTree<Integer> t1 = new AVLTree<>();
        t1.insert(12);
        t1.insert(4);
        t1.insert(20);
        t1.insert(23);
        t1.insert(1);
        t1.insert(5);

        System.out.println("--------------");
        t1.traversal();
        
        System.out.println("--------------");
        t1.printLefts();
        
        System.out.println("--------------");
        System.out.println(t1.getMax());
        System.out.println(t1.getMin());
    
        
        System.out.println("--------------");
        t1.remove(23);
        t1.remove(20);
        t1.traversal();
    
        System.out.println("--------------");
        System.out.println(KthSmallest.getKthSmallestInBST(t1.getRoot(), 2));
        
        System.out.println(KthSmallest.getKthSmallestInBST(t1.getRoot(), 5));
    
        System.out.println("--------------");
        AVLTree<Integer> t2 = new AVLTree<>();
        t2.insert(3);
        t2.insert(1);
        t2.insert(2);
        System.out.println("Height: " + t2.height());
        System.out.println("--------------");
    }
    
}
