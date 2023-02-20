public class Main {
    public static void main(String[] args) {
        Heap h1 = new Heap();
        h1.insert(3);
        h1.insert(0);
        h1.insert(-3);
        h1.insert(5);
        h1.insert(2);

     //   h1.heapSort();

        h1.poll();
        h1.heapSort();
    
        System.out.println("Checking min-heap validity");
        System.out.println(CheckHeapValidity.checkHeapValidity(new int[]{7, 5, 23, 210, 100}, 5));
    }
}