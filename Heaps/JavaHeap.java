import java.util.Collections;
import java.util.PriorityQueue;

public class JavaHeap {
    public static void main(String[] args) {
        // By-default it's MIN-HEAP
        // By providing Collections.reverseOrder(); it's MAX-HEAP
        PriorityQueue<Integer> heap = new PriorityQueue<>(Collections.reverseOrder());

        // O(logN)
        heap.add(3);
        heap.add(5);
        heap.add(2);

        // O(N)
        heap.contains(0);

        // For all N items in O(logN) = O(NlogN)
        while (!heap.isEmpty()) {
            System.out.println(heap.poll());
        }
    }
}
