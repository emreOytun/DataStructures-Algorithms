public class CheckHeapValidity {
    // Check if given heap is a valid min-heap.
    public static boolean checkHeapValidity(int[] heap, int heapSize) {
        return checkHeapValidity(heap, heapSize, 0);
    }

    private static boolean checkHeapValidity(int[] heap, int heapSize, int curIdx) {
        if (curIdx >= heapSize) return true;

        int leftChildIndex = curIdx * 2 + 1;
        int rightChildIndex = leftChildIndex + 1;

        if (leftChildIndex < heapSize && heap[curIdx] > heap[leftChildIndex]) return false;
        if (rightChildIndex < heapSize && heap[curIdx] > heap[rightChildIndex]) return false;
        return checkHeapValidity(heap, heapSize, leftChildIndex) && checkHeapValidity(heap, heapSize, rightChildIndex);
    }
}
