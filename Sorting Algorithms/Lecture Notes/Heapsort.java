import java.util.Arrays;

public class Heapsort implements SortAlgorithm {
    public <T extends Comparable<T>> void sort(T[] arr) {
        buildMaxHeap(arr); // If sorting is ascending sort, then first build max-heap to reverse.
        shrinkHeap(arr); // Shrink heap step-by-step by putting the root(max-element,idx=0) at the end of heap each time.
    }

    // O(logN) for each N-1 elements => O(NlogN)
    private <T extends Comparable<T>> void buildMaxHeap(T[] arr) {
        int i = 1; // Start from the second index assuming the first element(idx=0) is the root of the current heap.
        // Add each element of the array and heapify-up for each one.
        while (i < arr.length) {
            int child = i;
            int parent = (child - 1) / 2;
            while (parent >= 0 && arr[parent].compareTo(arr[child]) < 0) {
                swap(arr, parent, child);
                child = parent;
                parent = (child - 1) / 2;
            }
            ++i; // Go for next element.
        }
    }

    // Removes root of the heap step-by-step, putting the root at the end of the heap each time.
    private <T extends Comparable<T>> void shrinkHeap(T[] arr) {
        int lastIndex = arr.length-1; //Last index of the last node in the heap.

        // Swap the root(idx=0) and the lastNode(lastIndex), then heapifyDown.
        while (lastIndex > 0) {
            swap(arr, 0, lastIndex);
            --lastIndex; // The last element is considered as removed right now, we have to decrement the lastIndex of the heap.

            // Heapify-down
            int curIdx = 0;
            boolean isDone = false;
            while (curIdx < lastIndex && !isDone) {
                int leftIdx = 2*curIdx + 1;
                int rightIdx = leftIdx + 1;
                int maxIdx = curIdx;
                if (leftIdx <= lastIndex && arr[leftIdx].compareTo(arr[maxIdx]) > 0) {
                    maxIdx = leftIdx;
                }
                if (rightIdx <= lastIndex && arr[rightIdx].compareTo(arr[maxIdx]) > 0) {
                    maxIdx = rightIdx;
                }
                if (maxIdx != curIdx) {
                    swap(arr, curIdx, maxIdx);
                    curIdx = maxIdx;
                }
            }
        }
    }

    private <T extends Comparable<T>> void swap(T[] arr, int fi, int li) {
        T temp = arr[fi];
        arr[fi] = arr[li];
        arr[li] = temp;
    }

    public static void main(String[] args) {
        Integer[] arr = new Integer[] {1, 3, 5, -2};
        Heapsort heapsort = new Heapsort();
        heapsort.sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
