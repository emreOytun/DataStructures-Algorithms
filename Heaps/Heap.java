// Max-heap implementation
public class Heap {
     
    private int[] heap; // one-dimensional array represention of heap
    private int heapSize; // number of items in the heap

    public Heap() {
        heap = new int[Constants.CAPACITY];
        this.heapSize = 0;
    }

    // O(logN)
    public void insert(int data) {

        if (heapSize == heap.length) throw new RuntimeException("Heap is full...");

        heap[heapSize] = data;
        ++this.heapSize;
        fixHeapUp(heapSize-1);
    }

    // Check if the current one is bigger than parent, if it's swap them and continue.
    private void fixHeapUp(int curIndex) {
        int parentIndex = (curIndex - 1) / 2;
        if (curIndex > 0 && heap[curIndex] > heap[parentIndex]) {
            swap(curIndex, parentIndex);
            fixHeapUp(parentIndex);
        }
    } 

    // O(1)
    private void swap(int idx1, int idx2) {
        int temp = heap[idx1];
        heap[idx1] = heap[idx2];
        heap[idx2] = temp;
    }

    // O(logN)
    public int poll() {
        if (heapSize == 0) {
            throw new RuntimeException("There is no element to poll");
        }
        
        int root = heap[0];

        // Change the root with the last element in the heap.
        heap[0] = heap[heapSize-1];
        --heapSize;
        fixHeapDown(0);

        return root;
    }

    // Check if the current one is less than children, if it's swap it with the greatest child.
    private void fixHeapDown(int curIndex) {
        int leftChildIndex = curIndex * 2 + 1;
        int rightChildIndex = leftChildIndex + 1;
        
        // Find the largest index; first assigning it as current index; then comparing it with the children if there exists.
        int largestIndex = curIndex;

        if (leftChildIndex < heapSize && heap[leftChildIndex] > heap[largestIndex]) {
            largestIndex = leftChildIndex;
        }

        if (rightChildIndex < heapSize && heap[rightChildIndex] > heap[largestIndex]) {
            largestIndex = rightChildIndex;
        }

        // If a child is greater than the parent, then we must swap the values and continue to fix heap.
        if (curIndex != largestIndex) {
            swap(curIndex, largestIndex);
            fixHeapDown(largestIndex);
        }
    }

    // O(1)
    public int getMax() throws RuntimeException {
        if (heapSize == 0) throw new RuntimeException("There is no element in the heap");
        return heap[0];
    }

    public void heapSort() {
        while (heapSize != 0) {
            int max = poll();
            System.out.println(max);
        }
    }

}
