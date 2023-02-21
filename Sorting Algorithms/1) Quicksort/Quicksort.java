public class Quicksort {

    private int[] arr;

    public Quicksort(int[] arr) {
        this.arr = arr;
    }

    private void swap(int idx1, int idx2) {
        int temp = arr[idx1];
        arr[idx1] = arr[idx2];
        arr[idx2] = temp;
    }

    // It arranges the given array such that;
    // 1) There is a random pivot index (mid index here)
    // 2) Elements in the left side of the pivot are smaller than the pivot value,
    //    Elements in the right side of the pivot are greater than the pivot value
    // 3) Returns the pivot index
    private int partition(int first_idx, int last_idx) {
        int pivot = (first_idx + last_idx) / 2;
        swap(pivot, last_idx);

        // newPivotIndex keeps where the pivot value will be inserted.
        int newPivotIndex = first_idx;
        for (int i = first_idx; i < last_idx; ++i) {
            if (arr[i] <= arr[last_idx]) {
                swap(i, newPivotIndex);
                ++newPivotIndex; // Increment the pivot value because we added a new smaller item into the left part.
            }
        }

        // Put the pivot value which is in the last_idx into the newPivotIndex.
        swap(last_idx, newPivotIndex);
        return newPivotIndex;
    }

    public int[] sort() {
        quicksort(0, arr.length - 1);
        return arr;
    }

    // Compare-based algorithm
    // NOT Stable
    // O(NlogN) average-case
    // O(N^2) worst-case: Selecting pivot as greatest or lowest everytime so that it cannot divide the array into two equal sub-arrays.
    private void quicksort(int first_idx, int last_idx) {
        if (first_idx >= last_idx) return;

        int pivotIndex = partition(first_idx, last_idx);
        quicksort(first_idx, pivotIndex - 1);
        quicksort(pivotIndex + 1, last_idx);
    }

}