public class BubbleSort implements SortAlgorithm {
    
    // Worst-case: O(n^2) -> O(n^2) comparisions + O(n^2) exchanges
    // Best-case with adaptive exchanges boolean: O(n) comparisions + O(1) exhange  
    // Worse than selection sort
    // Bubble sort works best with nearly-sorted arrays, but works worst with reversed arrays.
    public <T extends Comparable<T>> void sort(T[] arr) {
        boolean exchanges = true;
        for (int i = 0; i < arr.length && exchanges; ++i) {
            exchanges = false;
            for (int j = 0; j < arr.length - 1; ++j) {
                if (arr[j+1].compareTo(arr[j]) < 0) {
                    T temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                    exchanges = true;
                }
            }
        }
    }
}
