public class Heapsort {

    // Heapsort:
    // 1) Turn the given array into a max or min heap. O(NlogN)
    // 2) Swap the top node with the bottom node everytime and heapify the new top node O(NlogN) 
    public static <T extends Comparable<T>> void sort(T[] arr) {
        Heapsort.createMaxHeapFromArray(arr);
        
        // O(NlogN)
        int remainingLength = arr.length;
        while (remainingLength != 0) {
            swap(arr, 0, remainingLength-1); // Swap the max value which is at the top of the array/heap with the last value in the array/heap.
            --remainingLength; // Decrease the remaining length.
            heapify(arr, remainingLength, 0);
        }
    }

    // Turns the given array into a max-heap
    // 1) Find the last non-leaf node's index
    // 2) Heapify the nodes starting with the last non-leaf node's index to the root
    private static <T extends Comparable<T>> void createMaxHeapFromArray(T[] arr) {
        int leafNodesNum = (int) Math.ceil((double)arr.length / 2);
        int nonLeafNodesNum = arr.length - leafNodesNum;
        int lastNonLeafNodeIndex = nonLeafNodesNum - 1;
        
        // O(NlogN)
        for (int i = lastNonLeafNodeIndex; i >= 0; --i) {
            heapify(arr, arr.length, i);
        }
        
    }

    // Heapify the given node until max-heap conditions are met.
    private static <T extends Comparable<T>> void heapify(T[] arr, int len, int idx) {
        if (idx < 0 || idx >= arr.length) return;

        // Find the max index between 3 nodes (this node, left child, right child)
        int maxIndex = idx;
        int leftIndex = idx * 2 + 1;
        int rightIndex = leftIndex + 1;

        if (leftIndex < len && arr[leftIndex] != null && arr[leftIndex].compareTo(arr[maxIndex]) > 0) {
            maxIndex = leftIndex;       
        }
        if (rightIndex < len && arr[rightIndex] != null && arr[rightIndex].compareTo(arr[maxIndex]) > 0) {
            maxIndex = rightIndex;
        }

        // If one of the childs is greater than this node then swap the values and continue to heapify.
        if (maxIndex != idx) {
            // swap the values
            swap(arr, idx, maxIndex);
            heapify(arr, len, maxIndex);
        }
    
    }

    // Swap the given indexes
    private static <T> void swap(T[] arr, int idx1, int idx2) {
        T temp = arr[idx1];
        arr[idx1] = arr[idx2];
        arr[idx2] = temp;
    }

}