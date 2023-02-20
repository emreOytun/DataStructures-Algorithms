public class MaxtoMinHeap {
    private int[] heap;

    public void setHeap(int[] heap) {
        if (heap != null) {
            this.heap = heap;
        }
    }

    public int[] transform() {

        for (int i = (heap.length-2)/2; i >= 0; --i) {
            fixDownHeap(i);
        }

        return this.heap;
    }

    private void fixDownHeap(int index) {
        int leftChildIndex = 2 * index + 1;
        int rightChildIndex = leftChildIndex + 1;
        int smallestIndex = index;

        if (leftChildIndex < heap.length && heap[leftChildIndex] < heap[index]) {
            smallestIndex = leftChildIndex;
        }

        if (rightChildIndex < heap.length && heap[rightChildIndex] < heap[smallestIndex]) {
            smallestIndex = rightChildIndex;
        }

        if (smallestIndex != index) {
            int temp = heap[smallestIndex];
            heap[smallestIndex] = heap[index];
            heap[index] = temp;

            fixDownHeap(smallestIndex);
        }

    }
}
