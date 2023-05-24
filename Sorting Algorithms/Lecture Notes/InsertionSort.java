public class InsertionSort implements SortAlgorithm {

    // Worst-case: O(n^2) -> O(n^2) comparisions + O(n^2) exchanges
    // Best-case: O(n) -> O(n) comparision + O(n) exchanges
    public <T extends Comparable<T>> void sort(T[] arr) {
        for (int i = 1; i < arr.length; ++i) {
            T temp = arr[i]; // Keep the current element as temp.
            int j = i-1; // Start from the previous index.
            for (; j >= 0 && temp.compareTo(arr[j]) < 0; --j) {
                arr[j+1] = arr[j];
            }
            arr[j+1] = temp;
        }
    }
}
